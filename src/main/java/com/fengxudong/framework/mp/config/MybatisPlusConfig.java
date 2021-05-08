package com.fengxudong.framework.mp.config;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.core.parser.ISqlParserFilter;
import com.baomidou.mybatisplus.core.parser.SqlParserHelper;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.tenant.TenantHandler;
import com.fengxudong.framework.mp.FrameworkMetaObjectHandler;
import com.fengxudong.framework.mp.FrameworkSqlInjector;
import com.fengxudong.framework.mp.FrameworkTenantSqlParser;
import com.fengxudong.framework.utils.InfoUtils;
import com.google.common.collect.Lists;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class MybatisPlusConfig {
    /**
     * 相当于顶部的：
     * {@code @MapperScan("com.baomidou.springboot.mapper*")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.fengxudong.framework.**.mapper");
        return scannerConfigurer;
    }


    /**
     * 多租户属于 SQL 解析部分，依赖 MP 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        /*
         * 【测试多租户】 SQL 解析处理拦截器<br>
         * 这里固定写成住户 1 实际情况你可以从cookie读取，因此数据看不到 【 麻花藤 】 这条记录（ 注意观察 SQL ）<br>
         */
        List<ISqlParser> sqlParserList = new ArrayList<>();
        FrameworkTenantSqlParser tenantSqlParser = new FrameworkTenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {

            @Override
            public String getTenantIdColumn() {
                return "org_id";// //多租户字段
            }

            @Override
            public boolean doTableFilter(String tableName) {
                final String filterTableName = "qrtz_blob_triggers,qrtz_calendars,qrtz_cron_triggers,qrtz_fired_triggers,"
                        + "qrtz_job_details,qrtz_locks,qrtz_paused_trigger_grps,qrtz_scheduler_state,qrtz_simple_triggers,"
                        + "qrtz_simprop_triggers,qrtz_triggers" ;
                ArrayList<String> tableNames = Lists.newArrayList(filterTableName.split(","));
                boolean isFilter = tableNames.contains(tableName);
                return isFilter;
            }

            @Override
            public Expression getTenantId() {
                return new StringValue(InfoUtils.currentOrgId()== null ? "" :InfoUtils.currentOrgId());//传入的值一般都是配置文件 静态变量或者session中取出
            }

        });

        sqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(sqlParserList);
        paginationInterceptor.setSqlParserFilter(new ISqlParserFilter() {
            @Override
            public boolean doFilter(MetaObject metaObject) {
                MappedStatement ms = SqlParserHelper.getMappedStatement(metaObject);
                // 过滤自定义查询此时无租户信息约束【 麻花藤 】出现
                String id = ms.getId();
                if (id.contains("selectById")||id.contains("updateById")||id.contains("DeleteById")||id.contains("selectMaps")) {
                    return true;
                }
                return false;
            }
        });
        return paginationInterceptor;
    }



    /**
     * 自动填充
     *
     * @return
     */
    @Bean
    public FrameworkMetaObjectHandler commonMetaObjectHandler() {
        return new FrameworkMetaObjectHandler();
    }

    /**
     * 自定义注入语句
     *
     * @return
     */
    @Bean
    public FrameworkSqlInjector mybatisPlusSqlInjector() {
        return new FrameworkSqlInjector();
    }

    /*@Bean
    public GlobalConfig globalConfiguration() {
        GlobalConfig conf = new GlobalConfig();
        return conf;
    }*/

    /**
     * 	乐观锁插件
     * 	取出记录时，获取当前version
     * 	更新时，带上这个version
     * 	执行更新时， set version = yourVersion+1 where version = yourVersion
     * 	如果version不对，就更新失败
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

}
