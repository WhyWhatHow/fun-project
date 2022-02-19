### 日志切换

springboot logback -> log4j2 

日志等级: 

```
- fatal（致命）
- error（错误）
- warn（警告）
- info (the default)（信息（默认值））
- debug（调试）
- trace（跟踪）

```



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

###  Controller层参数校验

> https://segmentfault.com/a/1190000023471742

1. `POST`、`PUT`请求，使用`requestBody`传递参数；
2. `GET`请求，使用`requestParam/PathVariable`传递参数。

####  RequestBody 参数校验

触发条件:

* DTO(**Data Transfer Object**) 对象 声明约束条件

```java
@Data
@Accessors(chain = true)
public class Student {
    @NotNull(message = "name 不能为空")
    @Length(min = 2, max = 10)
    String name;
    @NotNull(message = "age 不能为空")
    @Min(1) @Max(90)
    Integer age;
    @NotNull(message = "email 不能为空 ")
    @Email(message = "email 格式不正确")
    String email;


}
```

* **Post , put** 请求,只需要在方法 的 请求头添加 @ReqeustBody  @Valid , @Validated

```java
  @PostMapping("/test/add/")
    public R testValidate(@RequestBody@Validated Student student) {
        System.out.println(student);
        return RUtils.createSucc(student);
    }
```

* 报错 :**MethodArgumentNotValidException** extends BindException

####  requestParam/PathVariable参数校验

处理get请求

触发条件: 

* Controller 类上 添加 @validated 注解

* Controller 中method 添加 约束条件

  ```java
  @RestController
  @Validated
  public class DemoController{
         /**
       * 测试 get reqeust 请求, 参数校验
       * @param account
       * @return
       */
      @GetMapping("/test/get")
      public R testGetReqVal(@Length(min = 6,max=20)@NotNull String account){
          return RUtils.createSucc(account);
      }
  }
  ```

* 处理 ConstraintViolationException.class 异常 



#### 分组校验

user{

​	uid; // 创建user对象不一定要有, update user 时一定要有

​	name; // 创建时要有, update 可以有

​	age;// 创建时可以没有, update 可以有,也可以没有

​	//......

} 

可以根据不同的情况创建 实现不同的校验规则.
