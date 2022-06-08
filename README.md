# fun-project


```txt
 _____                      __                    ___                               ___
/\___ \                    /\ \__               /'___\                            /'___\
\/__/\ \   __  __    ____  \ \ ,_\             /\ \__/   ___    _ __             /\ \__/  __  __    ___
   _\ \ \ /\ \/\ \  /',__\  \ \ \/             \ \ ,__\ / __`\ /\`'__\           \ \ ,__\/\ \/\ \ /' _ `\
  /\ \_\ \\ \ \_\ \/\__, `\  \ \ \_             \ \ \_//\ \L\ \\ \ \/             \ \ \_/\ \ \_\ \/\ \/\ \
  \ \____/ \ \____/\/\____/   \ \__\             \ \_\ \ \____/ \ \_\              \ \_\  \ \____/\ \_\ \_\
   \/___/   \/___/  \/___/     \/__/              \/_/  \/___/   \/_/               \/_/   \/___/  \/_/\/_/

```



RBAC permission management system based on Spring Boot, Spring-cloud and Spring-security-oauth2. 

---
### Dependencies: 

| dependency           | version  |
| -------------------- | -------- |
| Spring Boot          | 2.6.2    |
| Spring Cloud         | 2021.0.1 |
| Spring Cloud Alibaba | 2021.1   |
| nacos                | 2.0.3    |
| druid                | 1.2.8    |
| mybatis-plus         | 3.5.1    |
| log4j2               | 2.6.3    |
| anji-captcha         | 1.3.0    |
| multilevel-cache     | 0.0.7    |
| Springdoc            | 1.6.6    |
| lombok               | 1.18.22  |
| Skywalking           | 8.7.0    |
---
### Modules:

```
fun-project
|- fun-api 服务模块api(包含entity,dto,vo,feign...)
	|- fun-system-api      -- system 模块api
|- fun-auth   -- 授权服务 [10000] 
|_ fun-common   -- 公共模块  
	|- fun-common-cache    -- 多级缓存配置 (todo, 待实现)
	|- fun-common-captcha  -- 滑块验证码配置模块(适配webflux)
	|- fun-common-core     -- 基类以及utils 
	|- fun-common-log      -- log日志配置
    |- fun-common-security -- 资源服务通用配置
    |_ fun-common-web      -- spring-boot-web 通用模块配置
|- fun-config  -- 通用配置模块(包含prod,test,dev等多种环境,后期可以自行替换,也可以直接保存nacos中)
|- fun-gateway -- 网关模块 [9000]
|- fun-service -- 微服务模块
	|- fun-service-system -- 系统管理模块 [11000]
    
```
---
### Contact:

- Email:
  - whywhathow.fun@gmail.com
  - 1246389103@qq.com



