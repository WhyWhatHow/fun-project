

![multi securityfilterchain](../../../MyInfo/BlogV2/source/images/multi-securityfilterchain.png)

**SecurityFilterChain  -> Spring Security 核心filter**

 最长前缀匹配原则 /app/hello/**  > /app/**

 

## Security Filters 加载顺序

- ChannelProcessingFilter
- WebAsyncManagerIntegrationFilter
- SecurityContextPersistenceFilter
- HeaderWriterFilter
- CorsFilter
- CsrfFilter
- LogoutFilter
- OAuth2AuthorizationRequestRedirectFilter
- Saml2WebSsoAuthenticationRequestFilter
- X509AuthenticationFilter
- AbstractPreAuthenticatedProcessingFilter
- CasAuthenticationFilter
-  
- Saml2WebSsoAuthenticationFilter
- **[`UsernamePasswordAuthenticationFilter`](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/form.html#servlet-authentication-usernamepasswordauthenticationfilter)**-> 表单登录
- OpenIDAuthenticationFilter
- DefaultLoginPageGeneratingFilter
- DefaultLogoutPageGeneratingFilter
- ConcurrentSessionFilter
- [`DigestAuthenticationFilter`](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/digest.html#servlet-authentication-digest)
- BearerTokenAuthenticationFilter
- **[`BasicAuthenticationFilter`](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html#servlet-authentication-basic)** -> http basic 登录
- RequestCacheAwareFilter
- SecurityContextHolderAwareRequestFilter
- JaasApiIntegrationFilter
- RememberMeAuthenticationFilter
- AnonymousAuthenticationFilter
- OAuth2AuthorizationCodeGrantFilter
- SessionManagementFilter
- [`ExceptionTranslationFilter`](https://docs.spring.io/spring-security/reference/servlet/architecture.html#servlet-exceptiontranslationfilter)-> 异常处理 essDeniedException和AuthenticationException 
- [`FilterSecurityInterceptor`](https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-requests.html#servlet-authorization-filtersecurityinterceptor)
- SwitchUserFilter





## SpringSecurity 异常处理

![exceptiontranslationfilter](../../../MyInfo/BlogV2/source/images/exceptiontranslationfilter.png)



- ![number 1](https://docs.spring.io/spring-security/reference/_images/icons/number_1.png) First, the `ExceptionTranslationFilter` invokes `FilterChain.doFilter(request, response)` to invoke the rest of the application.
- ![number 2](https://docs.spring.io/spring-security/reference/_images/icons/number_2.png) If the user is not authenticated or it is an `AuthenticationException`, then *Start Authentication*.
  - The [SecurityContextHolder](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-securitycontextholder) is cleared out
  - The `HttpServletRequest` is saved in the [`RequestCache`](https://docs.spring.io/spring-security/site/docs/5.6.2/api/org/springframework/security/web/savedrequest/RequestCache.html). When the user successfully authenticates, the `RequestCache` is used to replay the original request.
  - The `AuthenticationEntryPoint` is used to request credentials from the client. For example, it might redirect to a log in page or send a `WWW-Authenticate` header.
- ![number 3](../../../MyInfo/BlogV2/source/images/number_3.png) Otherwise if it is an `AccessDeniedException`, then *Access Denied*. The `AccessDeniedHandler` is invoked to handle access denied.

## Servlet Authentication 架构

`SecurityContextHolder` : 存放  用户认证的详细信息'  uses a `ThreadLocal` to store these detai

`SecurityContext` : 

`Authentication`:

- ​	 AuthenticationManager 输入, 判断 是否已认证 isAuthenticated()

- ​	 代表当前认证用户



![securitycontextholder](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/securitycontextholder.png)

`ProviderManager`: 有一系列AuthenticationProdvider (用户身份认证 )



<img src="https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/providermanager.png" alt="providermanager" style="zoom: 80%;" />

![providermanager parent](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/providermanager-parent.png)

### AbstractAuthenticationProcessingFilter

用户认证的基本流程,. 

1. `AbstractAuthenticationprocessionFilter `从HttpReqeust 中创建 Authentication(未认证的用户信息),  Authentication 类型`AbstractAuthenticationprocessionFilter ` 子类确定 

2. 利用AUthentication MAnager 去进行用户身份认证

3. 用户认证失败,

   - The [SecurityContextHolder](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-securitycontextholder) is cleared out.

   - `RememberMeServices.loginFail` is invoked. If remember me is not configured, this is a no-op.

   - `AuthenticationFailureHandler` is invoked.

4. 用户认证成功.

   - `SessionAuthenticationStrategy` is notified of a new log in. -> 日志记录
   - The [Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-authentication) is set on the [SecurityContextHolder](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-securitycontextholder). Later the `SecurityContextPersistenceFilter` saves the `SecurityContext` to the `HttpSession`.-> 将用户信息保存到SecurityContextHolder 
   - `RememberMeServices.loginSuccess` is invoked. **If remember me is not configured, this is a no-op.**
   - `ApplicationEventPublisher` publishes an `InteractiveAuthenticationSuccessEvent`.
   - `AuthenticationSuccessHandler` is invoked.

   

​		


![abstractauthenticationprocessingfilter](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/architecture/abstractauthenticationprocessingfilter.png)

### 用户名密码方式用户身份认证

#### Form Login 表单登录

未授权用户重定向到 /login 页面 流程.

![loginurlauthenticationentrypoint](../../../MyInfo/BlogV2/source/images/loginurlauthenticationentrypoint.png)

**Filter 执行具体流程**

<img src="https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/usernamepasswordauthenticationfilter.png" alt="usernamepasswordauthenticationfilter" style="zoom:150%;" />

#### Basic Authentication

HTTP Basic 认证流程

![basicauthenticationentrypoint](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/basicauthenticationentrypoint.png)

1. user 发起一条未授权的reqeust,  /private 
2. SpringSecurity 的 FilterSecurityInterceptor 拦截到用户请求,   拒绝, 跑错 AccessDeniedExcepton
3. 由于用户 没有权限, ExceptionTranslationFIlter  指向授权流程,  返回WWW-Authenticate 报文
4. 用户收到 WWW-Authenticate 报文后, user 就知道自己需要返回用户名 ,密码给 SpringSecurity ,  下图为用户名密码模式的用户认证->filter 执行 流程.

![basicauthenticationfilter](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/basicauthenticationfilter.png)



WWW-Authenticate : 401 状态码 

```
 WWW-Authenticate: Newauth realm="apps", type=1,
                       title="Login to \"apps\"", Basic realm="simple"
```

### Password Storage

UserDetails : 用户认证 信息, 即权限信息 

> [`UserDetails`](https://docs.spring.io/spring-security/site/docs/5.6.2/api/org/springframework/security/core/userdetails/UserDetails.html) is returned by the [`UserDetailsService`](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/user-details-service.html#servlet-authentication-userdetailsservice). The [`DaoAuthenticationProvider`](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/dao-authentication-provider.html#servlet-authentication-daoauthenticationprovider) validates the `UserDetails` and then returns an [`Authentication`](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-authentication) that has a principal that is the `UserDetails` returned by the configured `UserDetailsService`.

**`UserDetailsService`**    -  > DaoAuthenticationProvider

> Spring Security provides [in-memory](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/in-memory.html#servlet-authentication-inmemory) and [JDBC](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/jdbc.html#servlet-authentication-jdbc) implementations of `UserDetailsService`.

```java
@Bean
CustomUserDetailsService customUserDetailsService() {
	return new CustomUserDetailsService();
}
```

**`PasswordEncoder`**  :  加密密码, 用户密码校验 

**`DaoAuthenticationProvider`**

![daoauthenticationprovider](https://docs.spring.io/spring-security/reference/_images/servlet/authentication/unpwd/daoauthenticationprovider.png)

认证成功, 返回 `Authentication` 

## Authorization Architecture





## 个人认知

### filter-> interceptor -> advise

> 引用: [原文链接](https://blog.nowcoder.net/n/071a106a735a49cc813972a05aaa219c)

![img](../../../MyInfo/BlogV2/source/images/6097269_1587133036561_13E1273EA079E95F91C22049052258D1.png)



Filter : 

​	处于最外层，**能拿到请求（request）和响应（response)**

​		doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)

**Interceptor**:

​	SpringMVC, 能拿到 request 和 response，还能拿到 handler （Controller 的处理器）

```
   // 控制器(controller)方法处理前调用
preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) 
    // 控制器(controller)方法处理后调用
    // 但是，如果controler执行期间出现异常，postHandle将不被调用
postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView)
  // postHandle方法处理后调用
    // 无论，controler执行期间是否出现异常，afterCompletion都将被调用
afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) 

```



**切片（Aspect）**

切点, 切面, before ->aroud ->after-> afterReturn ...

### **自定义用户认证逻辑**：

- 处理用户信息获取逻辑 ->`UserDetailsService`
- 处理用户校验逻辑 

```java
package org.springframework.security.core.userdetails;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
 
import java.io.Serializable;
import java.util.Collection;
public interface UserDetails extends Serializable {
    // 权限信息
    Collection<? extends GrantedAuthority> getAuthorities();
    // 密码
    String getPassword();
    // 用户名
    String getUsername();
 
//--------------------- 下面四个布尔值可用来执行用户校验逻辑 --------------
 
    // 账户是否过期
    boolean isAccountNonExpired();
    // 账户是否被锁定
    boolean isAccountNonLocked();
    // 用户密码过期
    boolean isCredentialsNonExpired();
    // 账户是否可用（是否被删除了）
    boolean isEnabled();
}
```

- 处理密码加密解密

  接口名：PasswordEncoder

### 认证流程说明:

![img](../../../MyInfo/BlogV2/source/images/6097269_1587132752159_13E1273EA079E95F91C22049052258D1.png)



#### 认证结果如何在多个请求间共享

* 认证信息 使用ThreadLocal保存. 

`SecurityCOntextHolder`

![img](../../../MyInfo/BlogV2/source/images/6097269_1587132759693_13E1273EA079E95F91C22049052258D1.png)

### 获取认证用户信息

- `SecurityContextHolder`： 一个 **ThreadLocal** 作为当前线程 SecurityContext 的 key
- `SecurityContext`： 一个当前线程的Spring Security 上下文 可以获得当前用户信息
- `Authentication`： 认证信息

### 如何添加图形验证码?

> [参考链接](https://blog.nowcoder.net/n/64350fa54fd74c96aa3ab26f17d68fcc)

![img](../../../MyInfo/BlogV2/source/images/6097269_1587132720102_13E1273EA079E95F91C22049052258D1.png)

Q: 验证码 怎么传输的 ?   

### Spring Security + OAuth2 + JWT 基本使用

- `resource owner`: 拥有被访问资源的用户
- `user-agent`: 一般来说就是浏览器
- `client`: 第三方应用
- `Authorization server`: 认证服务器，用来进行用户认证并颁发token
- `Resource server`：资源服务器，拥有被访问资源的服务器，需要通过token来确定是否有权限访问

![image-20210519120117924](../../../MyInfo/BlogV2/source/images/nPs9DNFuUB52AOV.png)

### SecurityOauth Client 2  filter

```
 OAuth2AuthorizationRequestRedirectFilter
 
 OAuth2LoginAuthenticationFilter
 
 DefaultLoginPageGeneratingFilter
 
 OAuth2LoginAuthenticationProvider
 
```

## 尚硅谷 -Spring Security

FilterSecurityInterceptor：是一个方法级的权限过滤器, 基本位于过滤链的最底部。

ExceptionTranslationFilter

### 过滤器是如何加载的? 

* DelegatingFilterProxy -> doFilter()  -> this.initDelegate(wac) 

* FilterChainProxy :  -> List ->filters

  



## Q&&A



### Spring Security 什么时候查询数据库 ,如何确定表?



答: UserDetailsService.loadusername() 指定 用户名的校验方案. 

Spring-Boot-autoconfiguration 配置类

UserDetailServices 加载时间?

-> 用户自定义加载 

### Spring Security 什么时候注入的filter,自定义filter 要如何注入?



### There is no PasswordEncoder mapped for the id "null"

需要注册一个PasswordEncoder.

```java
@Bean 
public PasswordEncoder  passwordEncoder(){
 return new BCryptPasswordEncoder();
}

```



