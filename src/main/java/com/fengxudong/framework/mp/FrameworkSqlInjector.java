package com.fengxudong.framework.mp;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.extension.injector.methods.additional.AlwaysUpdateSomeColumnById;
import com.baomidou.mybatisplus.extension.injector.methods.additional.InsertBatchSomeColumn;
import com.baomidou.mybatisplus.extension.injector.methods.additional.LogicDeleteByIdWithFill;
import com.fengxudong.framework.mp.method.SelectIdWithoutLogicDeleted;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FrameworkSqlInjector extends AbstractSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        return Stream.of(
                new Insert(),
                new Delete(),
                new DeleteByMap(),
                new DeleteById(),// 方法重写 去掉逻辑删除标志
                new DeleteBatchByIds(),
                new Update(),
                new UpdateById(),// 方法重写 去掉逻辑删除标志
                new SelectById(),// 方法重写 去掉逻辑删除标志
                new SelectBatchByIds(),
                new SelectByMap(),
                new SelectOne(),
                new SelectCount(),
                new SelectMaps(),
                new SelectMapsPage(),
                new SelectObjs(),
                new SelectList(),
                new SelectPage(),
                new SelectIdWithoutLogicDeleted(),//新增根据id查询 无视逻辑删除

                /**
                 * 以下 3 个为内置选装件
                 * 头 2 个支持字段筛选函数
                 */
                // 批量新增功能 为了追求性能 不再框架层给通用字段注入值,需要调用方给通用字段赋值, 更新的字段默认排除了
                new InsertBatchSomeColumn(t->t.getFieldFill() != FieldFill.UPDATE ),
                //根据id删除并自动填充 LogicDeleteByIdWithFill
                new LogicDeleteByIdWithFill(),
                //根据id更新固定的那几个字段不包含逻辑删除
                new AlwaysUpdateSomeColumnById()
        ).collect(Collectors.toList());
    }
}
