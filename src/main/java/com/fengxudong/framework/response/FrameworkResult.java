package com.fengxudong.framework.response;

import io.swagger.annotations.ApiModel;
import lombok.*;

@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value="返回类")
public class FrameworkResult<T> {

    private Integer errCode;

    private String errMsg;

    private T data;

    public static <T> FrameworkResult<T> success(T object){
        return FrameworkResult.<T>builder().errCode(FrameworkResCodeEnum.SUCCESS.getErrorcode()).errMsg(FrameworkResCodeEnum.SUCCESS.getErrormsg()).data(object).build();
    }

    public static FrameworkResult<Void> success(){
        return FrameworkResult.<Void>builder().errCode(FrameworkResCodeEnum.SUCCESS.getErrorcode()).errMsg(FrameworkResCodeEnum.SUCCESS.getErrormsg()).build();
    }

    public static <T> FrameworkResult<T> failure(Exception exception){
        return FrameworkResult.<T>builder().errCode(FrameworkResCodeEnum.SERVER_ERROR.getErrorcode()).errMsg(exception.getCause().toString()).build();
    }

    public static <T> FrameworkResult<T> failure(FrameworkResCodeEnum frameworkResCodeEnum){
        return FrameworkResult.<T>builder().errCode(frameworkResCodeEnum.getErrorcode()).errMsg(frameworkResCodeEnum.getErrormsg()).build();
    }
}
