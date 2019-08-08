package com.cafe24.shoppingmall.frontend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.cafe24.shoppingmall.frontend.dto.JSONResult2;

import jdk.nashorn.internal.runtime.logging.Logger;


public class CustomUrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		Authentication authentication
    ) throws ServletException, IOException {
    	
    	
    	System.out.println("login success");
    	SavedRequest savedRequest = requestCache.getRequest( request, response );
        
        if ( savedRequest != null ) {
            requestCache.removeRequest( request, response );
            clearAuthenticationAttributes( request );
        }

		String accept = request.getHeader( "accept" );
    	
		SecurityUser securityUser = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null && principal instanceof UserDetails) {
				System.out.println(principal);
				securityUser = (SecurityUser) principal;
			}
		}
		System.out.println(securityUser);
		System.out.println(accept);
		
    	if( accept == null || accept.matches( ".*application/json.*" ) == false ) {
    		System.out.println("1");
    		request.getSession(true).setAttribute("loginNow", true);
    		if("ROLE_USER".equals(securityUser.getAuthorities().toString())) {
    			System.out.println("2");
	            getRedirectStrategy().sendRedirect( request, response, "/" );
    		} else {
    			System.out.println("3");
    			response.sendRedirect("/shop/manage/main");
    			//getRedirectStrategy().sendRedirect( request, response, "/manage/main" );
    		}
    		return;
    	}
    	
    	MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    	MediaType jsonMimeType = MediaType.APPLICATION_JSON;
		
    	JSONResult2 jsonResult = JSONResult2.success( securityUser );
    	if( jsonConverter.canWrite( jsonResult.getClass(), jsonMimeType ) ) {
        	jsonConverter.write( jsonResult, jsonMimeType, new ServletServerHttpResponse( response ) );
    	}
    	
    }

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }
}
