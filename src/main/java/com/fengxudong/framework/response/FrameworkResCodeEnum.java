package com.fengxudong.framework.response;

public enum FrameworkResCodeEnum {
    SUCCESS(200, "成功"), SERVER_ERROR(-1, "未知异常"),
    PARAM_ERROR(1, "参数不正确"), LOGIN_FAILED(2, "登录失败"),
    LOGIN_EXCEPTION(3, "登录异常"), USER_NOT_FOUND(4, "用户不存在"),
    PASSWORD_INCORRECT(5, "账号或密码不正确"), INVALID_TOKEN(6, "无效的token"),
    TOKEN_EXPIRED(7, "过期的token"), UNAUTHORIZED(8, "没有权限"),
    DELETE_FAILED(9, "删除失败"), RESOURCE_NOT_FOUND(10, "资源不存在"),
    UPDATE_FAILED(11, "修改失败"), ADD_FAILED(12, "添加失败"),
    INPUT_FAILED(13, "导入失败"), EXPORT_FAILED(14, "导出失败"),
    USER_REPEAT(15,"账户已存在"),
    REQUEST_REPEAT(106, "请求重复,该请求已被处理.");
    //成员变量
    private String errorMsg;
    private int errorCode;
    //构造方法

    FrameworkResCodeEnum(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public int getKey() {
        return errorCode;
    }

    public String getValue() {
        return errorMsg;
    }

    //覆盖方法
    @Override
    public String toString() {
        return this.errorCode + "-" + this.errorMsg;
    }

    public String getErrormsg() {
        return errorMsg;
    }

    public Integer getErrorcode() {
        return errorCode;
    }
}
