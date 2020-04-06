package com.altimetrik.bcp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
	         .csrf()
	        .disable()
	        .authorizeRequests()
	        //.antMatchers("/").permitAll()
	        .antMatchers(HttpMethod.GET, "/css/**", "/js/**","/img/**","/fonts/**").permitAll()
	        .anyRequest().authenticated()
	        .and().formLogin().loginPage("/index")
	         //.loginProcessingUrl("/index")
	        .usernameParameter("username")
	        .passwordParameter("password")
			.defaultSuccessUrl("/delivery")
	        .failureUrl("/index?error=true")
	        .permitAll()
	        .and()
            .logout()
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .permitAll();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		  auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
		  auth.inMemoryAuthentication().withUser("tester").password("{noop}tester").roles("USER");
		  auth.inMemoryAuthentication().withUser("karthick").password("{noop}karthick").roles("USER");
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
      web
        .ignoring()
           .antMatchers("/css/**").antMatchers("/js/**").antMatchers("/img/**").antMatchers("/fonts/**"); // #3
    }
}   
