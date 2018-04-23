package com.mzupancic.usermanagement.configuration;

import com.mzupancic.usermanagement.repository.UserRepository;
import com.mzupancic.usermanagement.security.rest.RestLogoutSuccessHandler;
import com.mzupancic.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final RestLogoutSuccessHandler restLogoutSuccessHandler;

    @Autowired
    public SecurityConfig(final UserService userService, RestLogoutSuccessHandler restLogoutSuccessHandler){
        this.userService = userService;
        this.restLogoutSuccessHandler = restLogoutSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic()
                    .and()
                .authorizeRequests()
                .antMatchers("**/api/**").authenticated()
                    .and()
                .formLogin().loginPage("/login")
                    .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(restLogoutSuccessHandler)
                .permitAll();
    }

}