package com.daji.jyhx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author 大稽
 * @date2018/10/1115:02
 */
@Configuration
@EnableWebSecurity
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository =  new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHander;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Autowired
    private AccessDeniedHandler myAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHander)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
             .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(3600)
                .userDetailsService(userDetailsService)
                .and()
             .authorizeRequests()// 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login",
                        "/static/**",
                        "/js/*",
                        "/css/*",
                        "/fonts/*",
                        "/images/*",
                        "/lib/**",
                        "/favicon.ico").permitAll()
                .antMatchers("/*")
                .authenticated()// 匹配的请求,登录后可以访问
                .anyRequest()
                .access("@rbacService.hasPermission(request,authentication)")//必须有相应权限才可以访问
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
//                .authenticated()
                .and()
                .csrf().disable(); // 关闭csrf防护
    }
}
