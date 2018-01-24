package com.communicate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
		auth.authenticationProvider(authenticationProvider());
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(Utils.getPasswordEncoder());
	    return authProvider;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/web/home.html","/resources/**");
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http
		.csrf()
		.disable()
		.authorizeRequests()
			.antMatchers("/web/home.html","/web/webjars/**","/web/register.html")
			.permitAll()
			.anyRequest().authenticated()
			.antMatchers("/web/login.html","/web/dashboard.html","/web/uploadimg.html","/web/image/**").authenticated()
			.and()
		.formLogin()
			
			.loginPage("/web/home.html")
			.permitAll()
			.loginProcessingUrl("/web/login.html")
			.defaultSuccessUrl("/web/dashboard.html")
			.usernameParameter("login")
			.passwordParameter("password")
			.and()
		.logout()
		.permitAll()
		.logoutSuccessUrl("/web/home.html")
		.invalidateHttpSession(true)
		.deleteCookies("JSESSIONID")
		.clearAuthentication(true);

		
		
	
			
	}
	
	
	

}
