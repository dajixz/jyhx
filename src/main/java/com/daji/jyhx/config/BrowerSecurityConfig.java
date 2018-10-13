package com.daji.jyhx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
    private AuthenticationSuccessHandler myAuthenticationSuccessHander;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
                .loginPage("/login")
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHander)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()// 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login",
                        "/static/**",
                        "/js*",
                        "/css/*",
                        "/fonts/*",
                        "/images/*",
                        "/lib/**").permitAll()
                .anyRequest()               // 任何请求,登录后可以访问
                .authenticated()
                .and()
                .csrf().disable(); // 关闭csrf防护
    }
}
