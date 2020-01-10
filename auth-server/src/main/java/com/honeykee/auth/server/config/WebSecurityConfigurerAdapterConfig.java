package com.honeykee.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Arrays;

/**
 * EnableGlobalMethodSecurity注解开启了方法级别的保护
 * @author zhang chuan sheng
 * @version 1.0
 * @date 2019-08-28 11:30
 * @since JDK 1.8
 * @EnableGlobal­ MethodSecurity 注解开启在方法上的保护功能
 * @EnableWebSecurity注解开启 Web保护功能
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) //common-security已经有了
//@EnableOAuth2Sso  //UserInfoRestTemplateFactory
public class WebSecurityConfigurerAdapterConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "commonUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 配置了如何验证用户信息
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal( AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
    }

    @Override
    public void configure( WebSecurity web) {
        web.ignoring().antMatchers(
                "/user/registry","/favor.ico", "/login.html",
                "/**/**.css", "/**/images/**", "**/**.js","/welcome" ,
                "/swagger**", "/swagger**/**", "/webjars/**", "/v2/**","/js/**", "/css/**", "/noauth/**","/login");
    }

    /**
     * 配置 SpringSecurity如何知道是 否所有的用户都需要身份验证呢?又如何知道要支持基于表单的身份验证呢? 工程的哪些资 源需要验证，哪些资源不需要验证?这时就需要配置 HttpSecurity。
     * HttpSecurity 中配置了所有的请求都需要安全验证
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure( HttpSecurity http) throws Exception {
//        super.configure(http);
//        http
//        .authorizeRequests().anyRequest().authenticated()
//            //解决静态资源被拦截的问题
//        .antMatchers("/login.html", "/**/**.css", "/images/**", "**/**.js","/index").permitAll()
//        .antMatchers( "/user/**" ).hasRole( "USER" )
//        .antMatchers( "/blogs/**" ).hasRole( "USER" )
//        .and().formLogin().loginPage("/login").failureUrl( "/login-error" ).permitAll()
//        .defaultSuccessUrl("/welcome").permitAll()
//        .and().exceptionHandling().accessDeniedPage( "/401" )
//        .and().logout().permitAll().logoutSuccessUrl( "/" );
        http.authorizeRequests() .anyRequest().authenticated().and()
                .csrf() .disable() ;
    }

    /**
     * auth-server的启动类，这里声明类admin有读写权限，guest只有读的权限；passwordEncoder()方法用于声明用户名和密码的加密方式，注：只有Spring Security5.0以后才有
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //数据库的用户信息,以及密码校验方式
        auth.userDetailsService( userDetailsService ).passwordEncoder( passwordEncoder );
        //用户密码在数据库是明文
//        auth.userDetailsService( userDetailsService ).passwordEncoder( NoOpPasswordEncoder.getInstance() );

        //inMemoryAuthentication 从内存中获取
        // IllegalArgumentException, There is no PasswordEncoder mapped for the id "null"
//        auth.inMemoryAuthentication().withUser("user1").password("123456").roles("USER");
        // 密码加密
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).
//                withUser("fzp").password(new BCryptPasswordEncoder().encode("123456")).roles("USER");

//        auth
//        .inMemoryAuthentication()
//        .withUser("guest").password("guest").authorities("WRIGTH_READ")
//        .and()
//        .withUser("admin").password("admin").authorities("WRIGTH_READ", "WRIGTH_WRITE");
    }

//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }

    /**
     * authenticationManager 需要配置 AuthenticationManager 这个 Bean，
     * 这个 Bean 来源于 WebSecurityConfigurerAdapter 中的配置 ，只有配置了这个 Bean 才会 开启密码类型的验证。
     * @return
     * @throws Exception
     */
    @Override
    @Bean(value = "authenticationManagerBean")
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//    @Autowired
//    private AuthenticationProvider authenticationProvider;
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManager(){
//        return new ProviderManager( Arrays.asList(authenticationProvider));
//    }


//    @Bean
//    @Autowired
//    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore) {
//        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
//        handler.setTokenStore(tokenStore);
//        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
//        handler.setClientDetailsService(clientDetailsService);
//        return handler;
//    }


}
