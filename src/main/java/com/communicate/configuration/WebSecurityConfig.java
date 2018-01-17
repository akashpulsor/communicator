package com.communicate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.communicate.dao.UserRepository;
import com.communicate.service.UserManagerImplementation;
import com.communicate.utils.Utils;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserManagerImplementation userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(Utils.getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
        .authorizeRequests()
            .antMatchers("/resources/**", "/home.html").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/home.html")
            .permitAll()
            .loginProcessingUrl("/login.html")
            .defaultSuccessUrl("/dashboard.html")
            .failureUrl("/error")
            .and()
        .logout()
            .permitAll()
            .logoutSuccessUrl("/home.html");
         ;
		
	}
	

}
