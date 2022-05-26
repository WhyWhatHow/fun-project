* basicxxxFillter -> client 认证

* `tokenEndpoint`  /oauth/token

  ​ grant()

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

## Q&A

用户名密码校验方案 

