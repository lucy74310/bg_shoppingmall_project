package com.cafe24.shoppingmall.frontend.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cafe24.shoppingmall.frontend.security.CustomAuthenticationFailureHandler;
import com.cafe24.shoppingmall.frontend.security.CustomUrlAuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
				.regexMatchers("\\A/(u|assets|uploads)/.*\\Z")
				.regexMatchers("\\A/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		// 예외
		.exceptionHandling()
		.accessDeniedPage("/WEB-INF/view/error/exception.jsp")
		
		 // RememberMeConfigurer
        .and()
        	.rememberMe()
        		.key("shop")
        		.rememberMeParameter("remember-me")
        		
		.and().csrf().disable()
		;
	
		
	}
	
	@Bean
	public AuthenticationFailureHandler authenticationFailureHandler() {
		return new CustomAuthenticationFailureHandler();
	}

	// 사용자 세부 서비스를 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.and()
			.authenticationProvider(authProvider());
	}
	@Bean
	public static AuthenticationSuccessHandler authenticationSuccessHandler() {
	    return new CustomUrlAuthenticationSuccessHandler();
	}
	
	// Encode the Password on Authentication
	// BCrypt Password Encoder(with Random Salt)
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}
	
	@Bean
	public AuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
//	    authProvider.setPasswordEncoder(passwordEncoder());
	    
	    return authProvider;
	}	
	
	
	@Configuration
	@Order(1)
	public static class AdminConfig extends WebSecurityConfigurerAdapter {
		public AdminConfig() {
	        super();
	    }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/admin*")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("ADMIN")
	          
	          .and()
	          .formLogin()
	          .loginPage("/loginAdmin")
	          .loginProcessingUrl("/admin_login")
	          .failureUrl("/loginAdmin?error=loginError")
	          .successHandler(authenticationSuccessHandler())
	          .usernameParameter("id")
	          .passwordParameter("password")
	          
	          .and()
	          .logout()
	          .logoutUrl("/admin_logout")
	          .logoutSuccessUrl("/")
	          .deleteCookies("JSESSIONID")
	          .invalidateHttpSession(true)
	          
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/WEB-INF/view/error/exception.jsp")
	           
	          .and()
	          .csrf().disable();
		}
		
		
	}
	
	@Configuration
	@Order(2)
	public static class UserConfig extends WebSecurityConfigurerAdapter {
		public UserConfig() {
	        super();
	    }

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/user*")
	          .authorizeRequests()
	          .anyRequest()
	          .hasRole("USER")
	           
	          .and()
	          .formLogin()
	          .loginPage("/loginUser")
	          .loginProcessingUrl("/user_login")
	          .failureUrl("/loginUser?error=loginError")
	          .successHandler(authenticationSuccessHandler())
	          .usernameParameter("id")
	          .passwordParameter("password")
//	          .defaultSuccessUrl("/userPage")
	           
	          .and()
	          .logout()
	          .logoutUrl("/user_logout")
	          .logoutSuccessUrl("/")
	          .deleteCookies("JSESSIONID")
	          .invalidateHttpSession(true)
	           
	          .and()
	          .exceptionHandling()
	          .accessDeniedPage("/WEB-INF/view/error/exception.jsp")
	           
	          .and()
	          .csrf().disable();
		}
		
	}
	
}
