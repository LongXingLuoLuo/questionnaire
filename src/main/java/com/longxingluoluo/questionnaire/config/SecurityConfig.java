package com.longxingluoluo.questionnaire.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Project the_questionnaire
 * Created on 2023/5/11 下午 09:54
 *
 * @author 龙星洛洛
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 密码加密方式
     * 选择消除密码方式加密
     *
     * @return 密码加密方式 password encoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * http 访问设置
     * @param http http 访问请求
     * @throws Exception 错误
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/js/**", "/css/**", "/images/**", "/fonts/**").permitAll()
                .antMatchers("/index", "/visit/**", "/error", "/thanks","/login", "/doLogin", "/logout").permitAll()
                .antMatchers(HttpMethod.POST, "/answer").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/grade/**", "/professional/**", "/curriculum/**", "/teacher/**", "/questionnaire/**", "/questionnaire_answer/**").hasRole("admin")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/admin/management")
                .failureUrl("/login")
                .permitAll();
        http.rememberMe()
                .alwaysRemember(true)
                .key("123123");
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login");
        http.csrf().disable();
    }

    /**
     * 账号配置
     * @param auth auth 访问
     * @throws Exception 可能的报错
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // admin 账号拥有 "admin" 权限
        auth.inMemoryAuthentication().withUser("admin").password("USTB_admin_").roles("admin");
    }
}
