package com.honeykee.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

/**
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-28 11:08
 * @since JDK 1.8
 *  @EnableResourceServer注解，开启 Resource Server。程序需要对外暴露 获取 Token 的 API接口和验证 Token 的 API接口 ，所以该程序也是一个资源服务。
 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer //好像不加也可以啊，如果不加 /uaa/user/current 访问不了
public class AuthorizationServerConfigurerAdapterConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier( "authenticationManagerBean" )
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Bean
    public ClientDetailsService clientDetails() {
        return new JdbcClientDetailsService(dataSource);
    }
//    @Autowired
//    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier(value = "commonUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * 将用户信息放内存
     * @return
     */
//    @Bean("InMemoryUserDetailsService")
//    protected UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser( User.withUsername("user_1").password("123456").authorities("USER").build());
//        manager.createUser( User.withUsername("user_2").password("123456").authorities("USER").build());
//        return manager;
//    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore(){
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtTokenConverter());
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("springcloud123");
        return converter;
    }

    /**
     * 配置授权 Token 的节点和 Token 服务 。
     * @param endpoints
     * @throws Exception
     *    在默认情况下 ， AuthorizationServerEndpointsConfigurer 配置开启了所有的验证类型 ， 除了密码类型的验证 ，
     *    密码验证只有配置了 authenticationManager 的配置才会开启。 AuthorizationServerEndpointsConfigurer配置由 以下 5 项组成。
     * 1 authenticationManager : 只有配置了该选项，密码认证才会开启。在大多数情况下都 是密码验证 ， 所 以一般都会配置这个选项。
     * 2 userDetailsService:配置获取用户认证信息的接口，和上一章实现的 userDetailsService 类似。
     * 3 authorizationCodeServices:配置验证码服务 。
     * 4 implicitGrantService:配置管理 implict验证的状态 。
     * 5 tokenGrant曰:配置 Token Granter。
     *   另外， 简要设置 Token 的管理策略 ，目前支持以下 3 种 。
     * 1 InMemoryTokenStore: Token 存储在内存中 。
     * 2 JdbcTokenStore: Token存储在数据库中。需要引入 spring才dbc 的依赖包，并配置数 据源，以及初始化 Spring0Auth2 的数据库脚本，即上一节的数据库脚本
     * 3 JwtTokenStore: 采用 JWT 形式，这种形式没有做任何的存储，因为 JWT 本身包含了 用户验证的所有信息，不需要存储。采用这种形式 ， 需要引入 spring-jwt 的依赖 。
     */
    @Override
    public void configure( AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
        //JWT
        //endpoints.tokenStore(jwtTokenStore());
        //token 存放在数据库
        endpoints.tokenStore( tokenStore() );
        //用户的信息
        endpoints.userDetailsService( userDetailsService );
        //liz 配置失败
//        endpoints.userApprovalHandler( userApprovalHandler );
        //验证码服务
//        endpoints.authorizationCodeServices();

        // 配置TokenServices参数,如果我们没有为AuthorizationServerEndpointsConfigurer设置tokenService属性，则默认使用DefaultTokenServices
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(endpoints.getTokenStore());
//        tokenServices.setSupportRefreshToken(false);
//        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
//        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // 30天
//        endpoints.tokenServices(tokenServices);
    }

    /**
     * 配置 Token 节点的安全策略
     * AuthorizationServerSecurityConfigurer配置了获取 Token 的策略，在本案例中对获取 Token
     * 请求不进行拦截，只需要验证获取 Token 的验证信息 ，这些信息准确无误 ，就返回 Token。另 外配置了检查 Token 的策略。
     *
     * 另外如果资源服务和授权服务是在同 一个服务中，用默认的配置即可，不需要做其他任何的配 置。
     * 但是如果资源服务和授权服务不在同一个服务中，则需要做一些额外配置。
     * 如果采用 RemoteTokenServices (远程 Token 校验)，资源服务器的每次请求所携带的 Token 都需要从授 权服务做校验 。
     * 这时需要配置“/oauth/check token”校验节点的校验策略 。
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure( AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess("isAuthenticated()");
        oauthServer.tokenKeyAccess("permitAll()");
        //client用户密码加密方式
        oauthServer.passwordEncoder( passwordEncoder );
        //允许表单认证
        oauthServer.allowFormAuthenticationForClients();

//        oauthServer.tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')").checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }


    /**
     * 配置 oauth_client_details【client_id和client_secret等】信息的认证【检查ClientDetails的合法性】服务
     * 设置 认证信息的来源：数据库 (可选项：数据库和内存,使用内存一般用来作测试)
     * 自动注入：ClientDetailsService的实现类 JdbcClientDetailsService (检查 ClientDetails 对象)
     * @see org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter#configure(org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer)
     * 配置客户端信息
     * 客户端的配置信息既可以放在内存中 ，也可以放在数据库中，需要配置以下信息 。
     * 1 clientld: 客户端 Id， 需要在 Authorization Server 中是唯一 的 。
     * 2 secret:客户端的密码。
     * 3 scope:客户端的域。
     * 4 authorizedGrantTypes:认证类型。
     * 5 authorities:权限信息。
     */
    @Override
    public void configure( ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails( clientDetailsService );

        // client 客户端信息保存在数据库,两种方式一样
        clients.withClientDetails( clientDetails() );
//        clients.jdbc( dataSource );

        //直接把client信息放内存
//        clients
//            .inMemory()
////            .withClient("client")
//            .withClient("zuul_server")
//            .secret("secret")
////            .authorizedGrantTypes("authorization_code")
//            .scopes("app", "WRIGTH", "read" )
//            .autoApprove(true)
//            .authorities("WRIGTH_READ", "WRIGTH_WRITE")
//            .authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code");
//        由于有多个客户端来连接Spring OAuth2 Auth Server，需要在配置类里为inMemory生成器定义多个withClients。

        //springcoud-book-方志鹏
//        clients.inMemory()
//                .withClient("browser")
//                .authorizedGrantTypes("refresh_token", "password")
//                .scopes("ui")
//                .and()
//                .withClient("service-hi")
//                .secret("{noop}123456")
//                .authorizedGrantTypes("client_credentials", "refresh_token","password")
//                .scopes("server")
//                .resourceIds( "provider-api" );//可以不设置resourceIds
    }

}
