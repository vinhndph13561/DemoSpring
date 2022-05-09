package com.example.Demo.Configuration;


import com.example.Demo.Service.ServiceImp.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().and().formLogin() // Cho phép người dùng xác thực bằng form login
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .defaultSuccessUrl("/success")
//                .permitAll() // Tất cả đều được truy cập vào địa chỉ này
//                .and()
//                .logout() // Cho phép logout
//                .permitAll();
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/controller/login/*").permitAll()
                .antMatchers("/api/controller/users/insert/*").permitAll()
                .antMatchers("/api/controller/products/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/controller/categories/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/api/controller/products/find/*").access("hasRole('ROLE_MEMBER')")
                .antMatchers("/api/controller/categories/find/*").access("hasRole('ROLE_MEMBER')")
                .antMatchers("/api/test/**").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();


    }


}
