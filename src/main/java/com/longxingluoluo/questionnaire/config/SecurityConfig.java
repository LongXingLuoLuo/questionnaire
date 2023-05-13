package com.longxingluoluo.questionnaire.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Project the_questionnaire
 * Created on 2023/5/11 下午 09:54
 *
 * @author 龙星洛洛
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
        web.ignoring().antMatchers("/questionnaire/**", "/js/**", "/css/**");
    }
}
