- [ ] nacos + Spring Gateway 实现灰度发布

​	 主要问题:

- [ ] 什么是灰度发布?
  - [ ] 怎么实现?
  
    
  
- [ ] 登录模块

  - [ ] 邮件 通知, 以及  邮箱验证, 密码重置
  - [ ] 滑块验证码

- [ ] 多级缓存, coffine, redis ,mysql 

Security oauth2中

**access_token 什么时候进行jwt 加密, 什么时候做的jwt 解密**



- [ ] controller 层生成 + 参数校验+ 权限校验 + 自动生成. -> 参考pig-codegen
- [ ] spring-cloud swagger 配置 , maven 配置
- [ ] maven配置包整理, mysql ->  多数据源 (没有意义) 

### swagger 迁移到springdocs

- [x] springdoc
  - [ ] 自己去重写
  - [ ] ReactiveCompositeDiscoveryClient_fun-gateway', predicates=[PredicateDefinition{name='Path', args={pattern=/fun-gateway/**}}], filters=[FilterDefinition{name='RewritePath', args={regexp=/fun-gateway/?(?<remaining>.*), replacement=/${remaining}}}], uri=lb://fun-gateway, order=0, metadata={nacos.instanceId=192.168.80.1#9000#DEFAULT#DEFAULT_GROUP@@fun-gateway, nacos.weight=1.0, nacos.cluster=DEFAULT, nacos.ephemeral=true, nacos.healthy=true, preserved.register.source=SPRING_CLOUD}}
