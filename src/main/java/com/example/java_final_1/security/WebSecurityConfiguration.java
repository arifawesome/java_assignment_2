package com.example.java_final_1.security;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Deprecated
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/users")
                .permitAll()
                .antMatchers("/user/**/**")
                .permitAll()
                .antMatchers("/customer/**")
                .permitAll()
                .antMatchers("/customers")
                .permitAll()
                .antMatchers("/MoviesAndTvs/**/**")
                .permitAll()
                .anyRequest().authenticated();

    }

}
