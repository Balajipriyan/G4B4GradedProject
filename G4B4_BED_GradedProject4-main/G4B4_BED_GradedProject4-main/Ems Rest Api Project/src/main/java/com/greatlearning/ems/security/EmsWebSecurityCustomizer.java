package com.greatlearning.ems.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class EmsWebSecurityCustomizer extends WebSecurityConfigurerAdapter {

	protected void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.authenticationProvider(emsAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider emsAuthenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

		authProvider.setPasswordEncoder(passwordEncoder());
		authProvider.setUserDetailsService(userDetailService());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailService() {

		return new EmsUserDetailsService();
	}

	@Override

	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/employees/save", "/employees/showFormForAdd", "/employees/403")
				.hasAnyAuthority("USER", "ADMIN").antMatchers("/employees/showFormForUpdate", "/employees/delete")
				.hasAuthority("ADMIN").anyRequest().authenticated().and().formLogin().loginProcessingUrl("/login")
				.successForwardUrl("/employees/list").permitAll().and().logout().logoutSuccessUrl("/login").permitAll()
				.and().exceptionHandling().accessDeniedPage("/employees/403").and().cors().and().csrf().disable();

	}

}
