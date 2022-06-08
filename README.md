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
|- fun-api  services-api(include entity,dto,vo,feign...)
	|- fun-system-api      -- service-system-api
|- fun-auth   -- auth-server [10000] 
|_ fun-common     
	|- fun-common-cache    
	|- fun-common-captcha  
	|- fun-common-core      
	|- fun-common-log      
    |- fun-common-security  -- resource-server 
    |_ fun-common-web     
|- fun-config  -- common-config in application.yml
|- fun-gateway --  Gatway  [9000]
|- fun-service --
	|- fun-service-system -- service-system (RBAC)  [11000]
    
```
---
### Contact:

- Email:
  - whywhathow.fun@gmail.com
  - 1246389103@qq.com



