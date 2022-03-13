package com.fun.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-03-12 22:56
 **/
@SpringBootApplication
@Profile("simple")
public class AuthServerApplicationSimple {
    public static void main(String[] args) {
        //ResourceServerConfiguration  -> SecurityConfiguration
        SpringApplication.run(AuthServerApplicationSimple.class, args);
    }
    /**
     * [pattern='/oauth/token'], Ant [pattern='/oauth/token_key'], Ant [pattern='/oauth/check_token']] with [
     * org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@56078cea,
     * org.springframework.security.web.context.SecurityContextPersistenceFilter@19b047fe,
     * org.springframework.security.web.header.HeaderWriterFilter@1639f93a,
     * org.springframework.security.web.authentication.logout.LogoutFilter@41fed14f,
     * org.springframework.security.web.authentication.www.BasicAuthenticationFilter@6ea04618,
     * org.springframework.security.web.savedrequest.RequestCacheAwareFilter@53dad875,
     * org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@118102ee,
     * org.springframework.security.web.authentication.AnonymousAuthenticationFilter@5a00eb1e,
     * org.springframework.security.web.session.SessionManagementFilter@68f32020,
     * org.springframework.security.web.access.ExceptionTranslationFilter@174e1b69,
     * org.springframework.security.web.access.intercept.FilterSecurityInterceptor@7bc6d27a
     * ]
     *
     * ---
     *
     * o.s.s.web.DefaultSecurityFilterChain     : Will secure Or [Ant [pattern='/me']] with [
     *
     * org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@11d4dbd6,
     * org.springframework.security.web.context.SecurityContextPersistenceFilter@3e5d4f6b,
     * org.springframework.security.web.header.HeaderWriterFilter@68217d41,
     * org.springframework.security.web.authentication.logout.LogoutFilter@367b22e5,
     * org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter@4b2e3e8f,
     * org.springframework.security.web.savedrequest.RequestCacheAwareFilter@39c1fe0b,
     * org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@5ae95707,
     * org.springframework.security.web.authentication.AnonymousAuthenticationFilter@6f4ade6e,
     * org.springframework.security.web.session.SessionManagementFilter@1ac45389,
     * org.springframework.security.web.access.ExceptionTranslationFilter@1bf39d06,
     * org.springframework.security.web.access.intercept.FilterSecurityInterceptor@514cd540]
     *
     * ---
     *
     * [           main] o.s.s.web.DefaultSecurityFilterChain     :Will secure any request with [
     *
     * org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@2324bfe7,
     * org.springframework.security.web.context.SecurityContextPersistenceFilter@6b0615ae,
     * org.springframework.security.web.header.HeaderWriterFilter@2f98635e,
     * org.springframework.security.web.csrf.CsrfFilter@4f453e63,
     * org.springframework.security.web.authentication.logout.LogoutFilter@2416498e,
     * org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter@6e1b9411,
     * org.springframework.security.web.authentication.ui.DefaultLoginPageGeneratingFilter@2cec704c,
     * org.springframework.security.web.authentication.ui.DefaultLogoutPageGeneratingFilter@112d1c8e,
     * org.springframework.security.web.savedrequest.RequestCacheAwareFilter@4e73b552,
     * org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@221dad51,
     * org.springframework.security.web.authentication.AnonymousAuthenticationFilter@3d49fd31,
     * org.springframework.security.web.session.SessionManagementFilter@49c8f6e8,
     * org.springframework.security.web.access.ExceptionTranslationFilter@7afbf561,
     * org.springframework.security.web.access.intercept.FilterSecurityInterceptor@73a19967
     * ]
     */
}
