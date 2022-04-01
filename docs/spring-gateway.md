## 网关请求处理过程

![img](https://images2018.cnblogs.com/blog/292888/201809/292888-20180903224617801-47450895.png)

![img](https://img2018.cnblogs.com/blog/292888/201810/292888-20181014112350312-1330218337.png)





ReactiveCompositeDiscoveryClient_fun-gateway',

 predicates=[PredicateDefinition{name='Path', args={pattern=/fun-gateway/**}}], *

*filters=[FilterDefinition{name='RewritePath', args={regexp=/fun-gateway/?(?<remaining>.*), replacement=/${remaining}}}], 

uri=lb://fun-gateway, order=0, metadata={nacos.instanceId=192.168.80.1

#9000#DEFAULT#DEFAULT_GROUP@@fun-gateway, nacos.weight=1.0, nacos.cluster=DEFAULT, nacos.ephemeral=true, nacos.healthy=true, preserved.register.source=SPRING_CLOUD}}

## 参考

* https://blog.fintopia.tech/60e27b0e2078082a378ec5ed/
* https://www.cnblogs.com/bjlhx/p/9588739.html