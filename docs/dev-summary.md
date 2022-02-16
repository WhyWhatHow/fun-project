### 日志切换

springboot logback -> log4j2 
- reason:
  ![img.png](img.png)

- content: 
  
  *  排除springboot 自带的log, 添加log4j2
  
  ```xml
  <dependencies>
   <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <!--             排除掉springboot 自带的log-->
            <exclusions>
                <exclusion>
                    <artifactId>spring-boot-starter-logging</artifactId>
                    <groupId>org.springframework.boot</groupId>
                </exclusion>
                <exclusion>
                    <groupId>log4j</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.slf4j</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>org.apache.logging.log4j</groupId>
                    <artifactId>*</artifactId>
                </exclusion>
            </exclusions>
  
        </dependency>
        <!--         引入log4j2-->
        <!--         设置 log4j2日志-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
  </dependencies>
  ```
  * 配置log4j2.xml文件 ,在 application.yml中添加配置信息	
  
    补充说明, 由于springboot 版本升级导致 spring.profile.active 报错问题的解决方案
    [参考链接](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Config-Data-Migration-Guide)
```yaml
spring: # every serice needed
  config: # springboot_2.4 use different control logic in this.
    use-legacy-processing: true
```
​		题外话: 若application.yml存在中文, 可能会因为编码问题报错, 尽量使用 english注释.

* 日志文件的存放问题, 日志名称的命名问题?

  1.日志文件存放位置:  ./logs/文件夹下

  2.每个微服务一份日志文件, 根据 微服务名 创建日志文件, ./logs/services-name.log

   解决方案:

​	 按照springboot 的启动顺序, 在 **application.yml 加载完成-> xxx.xml 未加载** 过程之间 实现对 xml文件内容填充即可. 

 
