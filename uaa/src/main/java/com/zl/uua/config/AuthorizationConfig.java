package com.zl.uua.config;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;

import com.zl.uua.details.ZlPreAuthenticationChecks;
import com.zl.uua.provider.ZlAuthenticationProvider;
import com.zl.uua.service.UserSecurityServiceImpl;

/**
 * @author zhongliang
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserSecurityServiceImpl userDetailsServiceImpl;
    @Autowired
    private ZlPreAuthenticationChecks zlPreAuthenticationChecks;

    @Bean
    public TokenEnhancer jwtTokenEnhancer(){
        return new ZlJwtTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        //  Sets the JWT signing key
        jwtAccessTokenConverter.setSigningKey("cjs");
        return jwtAccessTokenConverter;
    }

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private ZlJwtTokenEnhancer zlJwtTokenEnhancer;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer security) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancerList = new ArrayList<>();
        enhancerList.add(zlJwtTokenEnhancer);
        enhancerList.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(enhancerList);


        security
                .tokenEnhancer(enhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter);
        //使用oauth2的密码模式时需要配置
        security.authenticationManager(authorizationAuthenticationManager())
                .userDetailsService(userDetailsServiceImpl);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        clients.inMemory().withClient("app")
                .secret(bCryptPasswordEncoder.encode("123456"))
                .authorizedGrantTypes("password", "refresh_token", "authorization_code").redirectUris("https://www.baidu.com/")
                .scopes("all")
                //Token有效时间
                .accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(3600)
                .and()
                .withClient("web")
                .secret(bCryptPasswordEncoder.encode("123456"))
                //获取的token里有refresh_token
                .authorizedGrantTypes("password", "refresh_token", "authorization_code", "client_credentials")
                .scopes("all")
                .accessTokenValiditySeconds(72000);
    }

    private PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider = new PreAuthenticatedAuthenticationProvider();
        preAuthenticatedAuthenticationProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<>(userDetailsServiceImpl));
        return preAuthenticatedAuthenticationProvider;
    }

    private AuthenticationManager authorizationAuthenticationManager() {
        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(daoAuthenticationProvider());
        providers.add(preAuthenticatedAuthenticationProvider());
        return new ProviderManager(providers);
    }

    private AuthenticationProvider daoAuthenticationProvider() {
        ZlAuthenticationProvider daoAuthenticationProvider = new ZlAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserSecurityServiceImpl(userDetailsServiceImpl);
        daoAuthenticationProvider.setPostAuthenticationChecks(zlPreAuthenticationChecks);
        return daoAuthenticationProvider;
    }
   
}
