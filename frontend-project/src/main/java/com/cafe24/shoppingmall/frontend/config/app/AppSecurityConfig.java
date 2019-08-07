package com.cafe24.shoppingmall.frontend.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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
				.regexMatchers("\\A/(u|assets|error)/.*\\Z")
				.regexMatchers("\\A/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("spring http security");
		//인터셉터 url에 접근 제어 (Basic ACL)
		http
			.authorizeRequests()
			
				.antMatchers("/manage/main","/manage/main/**").hasRole("ADMIN")
				
				.anyRequest().permitAll()
		
		// 관리자 로그인 설정
		.and()
			.formLogin()
				.loginPage("/manage/login")
				.loginProcessingUrl("/manage/auth")
				.failureUrl("/manage/login?result=fail")
				//.failureHandler(authenticationFailureHandler())
				.successHandler(authenticationSuccessHandler())
				.usernameParameter("id")
				.passwordParameter("password")
				
		// 사용자 로그인 설정
		.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/auth")
				.failureUrl("/login?result=fail")
				.successHandler(authenticationSuccessHandler())
				.usernameParameter("id")
				.usernameParameter("password")		
		
		//관리자 로그아웃 
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/manage/logout"))
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				
		//사용자 로그아웃 
		.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				
		// 예외
		.and()
		.exceptionHandling()
		.accessDeniedPage("/WEB-INF/view/error/exception.jsp")
		
		 // RememberMeConfigurer
        .and()
        	.rememberMe()
        		.key("mysite")
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
		System.out.println("AuthenticationManagerBuilder");
		auth
			.userDetailsService(userDetailsService)
			.and()
			.authenticationProvider(authProvider());
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
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
		System.out.println("authProvider");
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
//	    authProvider.setPasswordEncoder(passwordEncoder());
	    System.out.println(authProvider.getUserCache());
	    
	    return authProvider;
	}	
	
//	@Bean
//	public UserDetailsService DetailsService() {
//		return new UserDetailsServiceImpl();
//   }
	
	
}
