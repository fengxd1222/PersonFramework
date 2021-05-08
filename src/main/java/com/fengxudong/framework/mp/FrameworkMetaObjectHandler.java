package com.fengxudong.framework.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.fengxudong.framework.context.UserInfo;
import com.fengxudong.framework.utils.InfoUtils;
import org.apache.ibatis.reflection.MetaObject;

public class FrameworkMetaObjectHandler implements MetaObjectHandler {
    /**
     * 创建时间
     */
    private final String createTime = "createTime";
    /**
     * 更新时间
     */
    private final String updateTime = "updateTime";
    /**
     * 创建人ID
     */
    private final String createUserId = "createUserId";
    /**
     * 创建人姓名
     */
    private final String createUserName = "createUserName";
    /**
     * 更新人ID
     */
    private final String updateUserId = "updateUserId";
    /**
     * 更新人姓名
     */
    private final String updateUserName = "updateUserName";
    /**
     * 删除标志
     */
    private final String isDeleted = "isDeleted";



    @Override
    public void insertFill(MetaObject metaObject) {
        long currentTimeMillis = System.currentTimeMillis();
        UserInfo userInfo = InfoUtils.currentUserInfo();
        Long currentUserId = InfoUtils.currentUserId();
        String currentUsername = userInfo.getUsername();
        // 创建时间
        if(getFieldValByName(createTime, metaObject) == null) {
            setFieldValByName(createTime,currentTimeMillis, metaObject);
        }
        // 创建人用户ID
        if(getFieldValByName(createUserId, metaObject) == null) {
            setFieldValByName(createUserId, currentUserId, metaObject);
        }
        // 创建人名称
        if(getFieldValByName(createUserName, metaObject) == null) {
            setFieldValByName(createUserName, currentUsername, metaObject);
        }

        // 更新时间
        if(getFieldValByName(updateTime, metaObject) == null) {
            setFieldValByName(updateTime,currentTimeMillis, metaObject);
        }
        // 更新人ID
        if(getFieldValByName(updateUserId, metaObject) == null) {
            setFieldValByName(updateUserId, currentUserId, metaObject);
        }
        // 更新人名称
        if(getFieldValByName(updateUserName, metaObject) == null) {
            setFieldValByName(updateUserName, currentUsername, metaObject);
        }
        // 删除标志
        if(getFieldValByName(isDeleted, metaObject) == null) {
            setFieldValByName(isDeleted, 0, metaObject);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        long currentTimeMillis = System.currentTimeMillis();
        UserInfo userInfo = InfoUtils.currentUserInfo();
        Long currentUserId = InfoUtils.currentUserId();
        String currentUsername = userInfo.getUsername();
        // 更新时间
        //if(getFieldValByName(updateTime, metaObject) == null){
        // 更新时间 不再判断是不是传值了
        setFieldValByName(updateTime, currentTimeMillis, metaObject);
        //}
        //更新人ID
        if(getFieldValByName(updateUserId, metaObject) == null) {
            setFieldValByName(updateUserId, currentUserId, metaObject);
        }
        // 更新人名称
        if(getFieldValByName(updateUserName, metaObject) == null) {
            setFieldValByName(updateUserName, currentUsername, metaObject);
        }
    }
}
