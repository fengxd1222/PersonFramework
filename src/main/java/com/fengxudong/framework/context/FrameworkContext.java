package com.fengxudong.framework.context;

/**
 * 全局上下文
 */
public final class FrameworkContext {

    private String orgId;

    private Long userId;

    private Boolean isolation;

    private String token;

    private UserInfo userInfo;

    public FrameworkContext(UserInfo userInfo,String token){
        this(userInfo.getOrgId(),userInfo.getUserId(),true,token);
        this.userInfo = userInfo;
    }

    public FrameworkContext(boolean isolation){
        this(null,null,isolation,null);
    }

    public FrameworkContext(String orgId){
        this(orgId,null,true,null);
    }

    public FrameworkContext(String orgId, Long userId, Boolean isolation,String token) {
        this.orgId = orgId;
        this.userId = userId;
        this.isolation = isolation;
        this.token = token;
    }

    public String getOrgId() {
        return orgId;
    }

    public Long getUserId() {
        return userId;
    }

    public Boolean getIsolation() {
        return isolation;
    }

    public String getToken() {
        return token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
