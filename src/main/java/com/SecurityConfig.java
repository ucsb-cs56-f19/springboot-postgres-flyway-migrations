package com;


import org.springframework.security.web.csrf.CsrfToken; //for csrf
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
                .antMatchers("/user/**", "/user").permitAll();
                //Even if you permit all, Postman will be forbidden for POST methods			
        http.csrf().disable(); //disables csrf support so you won't get 403 forbiddens
	}
}