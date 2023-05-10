# personWork

```
PersonFramework
├─ pom.xml
├─ README.md
└─ src
   ├─ main
   │  ├─ java
   │  │  └─ com
   │  │     └─ fengxudong
   │  │        └─ framework
   │  │           ├─ base
   │  │           │  ├─ config
   │  │           │  │  └─ FrameworkBaseConfig.java
   │  │           │  ├─ controller
   │  │           │  │  └─ FrameworkController.java
   │  │           │  ├─ entity
   │  │           │  │  ├─ BaseCreateEntity.java
   │  │           │  │  ├─ BaseEntity.java
   │  │           │  │  └─ BaseUpdateEntity.java
   │  │           │  ├─ mapper
   │  │           │  │  └─ FrameworkBaseMapper.java
   │  │           │  └─ service
   │  │           │     ├─ IFrameworkService.java
   │  │           │     └─ impl
   │  │           │        └─ FrameworkServiceImpl.java
   │  │           ├─ cache
   │  │           │  ├─ FrameworkCache.java
   │  │           │  ├─ ICache.java
   │  │           │  ├─ IFrameworkCache.java
   │  │           │  ├─ redis
   │  │           │  │  ├─ IRedisCache.java
   │  │           │  │  └─ RedisCache.java
   │  │           │  └─ support
   │  │           │     └─ RedisConfigureSupport.java
   │  │           ├─ context
   │  │           │  ├─ FrameworkContext.java
   │  │           │  ├─ FrameworkContextException.java
   │  │           │  ├─ FrameworkContextHolder.java
   │  │           │  └─ UserInfo.java
   │  │           ├─ FrameworkApplication.java
   │  │           ├─ init
   │  │           │  ├─ RoleMapping.java
   │  │           │  └─ scanner
   │  │           │     ├─ RoleMappingScan.java
   │  │           │     └─ RoleMappingScannerRegister.java
   │  │           ├─ logic
   │  │           │  ├─ controller
   │  │           │  │  ├─ SecurityGrantedAuthorityController.java
   │  │           │  │  └─ UserController.java
   │  │           │  ├─ entity
   │  │           │  │  └─ UserAuthorityEntity.java
   │  │           │  ├─ mapper
   │  │           │  │  ├─ SecurityGrantedAuthorityMapper.java
   │  │           │  │  ├─ SecurityUserDetailMapper.java
   │  │           │  │  └─ UserAuthorityMapper.java
   │  │           │  └─ service
   │  │           │     ├─ impl
   │  │           │     │  ├─ SecurityGrantedAuthorityServiceImpl.java
   │  │           │     │  └─ SecurityUserDetailServiceImpl.java
   │  │           │     ├─ ISecurityGrantedAuthorityService.java
   │  │           │     └─ ISecurityUserDetailService.java
   │  │           ├─ mp
   │  │           │  ├─ config
   │  │           │  │  └─ MybatisPlusConfig.java
   │  │           │  ├─ FrameworkMetaObjectHandler.java
   │  │           │  ├─ FrameworkSqlInjector.java
   │  │           │  ├─ FrameworkTenantSqlParser.java
   │  │           │  └─ method
   │  │           │     └─ SelectIdWithoutLogicDeleted.java
   │  │           ├─ response
   │  │           │  ├─ FrameworkResCodeEnum.java
   │  │           │  └─ FrameworkResult.java
   │  │           ├─ security
   │  │           │  ├─ config
   │  │           │  │  └─ WebSecurityConfig.java
   │  │           │  ├─ detail
   │  │           │  │  ├─ SecurityGrantedAuthority.java
   │  │           │  │  └─ SecurityUserDetail.java
   │  │           │  ├─ filter
   │  │           │  │  ├─ LoginFilter.java
   │  │           │  │  └─ TokenFilter.java
   │  │           │  └─ service
   │  │           │     ├─ impl
   │  │           │     │  └─ UserDetailServiceImpl.java
   │  │           │     └─ UserDetailService.java
   │  │           └─ utils
   │  │              └─ InfoUtils.java
   │  └─ resources
   │     ├─ application.properties
   │     └─ mapper
   │        ├─ 1.txt
   │        ├─ logic
   │        │  └─ UserAuthorityMapper.xml
   │        └─ security
   │           ├─ SecurityGrantedAuthorityMapper.xml
   │           └─ SecurityUserDetailMapper.xml
   └─ test
      └─ java
         └─ com
            └─ fengxudong
               └─ framework
                  └─ FrameworkApplicationTests.java

```