* basicxxxFillter -> client 认证

* `tokenEndpoint`  /oauth/token

  grant()

* `ResourceOwnerPasswordTokenGranter.grant()` -> 调用父类`AbstractTokenGranter.grant()`


* `ResourceOwnerPasswordTokenGranter.getOAuth2Authentication()` -> 用户身份信息认证

    *

* `AbstractTokenGranter.createAccessToken()`

  ```java
  protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {
  		return tokenServices.createAccessToken(getOAuth2Authentication(client, tokenRequest));
  	}
  ```

* `DefaultTokenServices.createAccessToken()`

### Version:

### 综述:

### /oauth/token 流程分析:

1. spring security 进 client 身份认证, ->
2. SpringMVC filter 过滤
3. Spring security oauth2 处理 /oauth/token 用户请求
    1. 进行用户身份认证
    2. 生成token并保存

## Q&A

用户名密码校验方案

### Resource-server 端如何根据access_token 获取用户信息 ?

httpRequest (header [ Authorization : "Bearer " ])-> BearerTokenExtractor-> DefaultTokenServices.loadAccessToken() ->
redis/InMemory

