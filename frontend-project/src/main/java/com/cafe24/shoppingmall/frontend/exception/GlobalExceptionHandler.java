package com.cafe24.shoppingmall.frontend.exception;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cafe24.shoppingmall.frontend.dto.JSONResult;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	
	
	@ExceptionHandler( Exception.class )
	public void handlerException( 
		HttpServletRequest request, 
		HttpServletResponse response,	
		Exception e
	) throws Exception {
		
		
		System.out.println("global error handler - frontend");
		
		
		// 1. 로깅
		// e.printStackTrace(); 
		StringWriter errors = new StringWriter(); 
		e.printStackTrace(new PrintWriter(errors));
		
		LOGGER.error(errors.toString());
		
		//요청이 json을 요청했는지 html을 요청했는지 확인
		String accept = request.getHeader("accept");
		
		
		
		
		if(accept.matches(".*application/json.*")) {
			// JSON 응답
			response.setStatus(HttpServletResponse.SC_OK); //헤더
			
			JSONResult jsonResult = JSONResult.fail(errors.toString());
			String result = new ObjectMapper().writeValueAsString(jsonResult);
			
			OutputStream os = response.getOutputStream();
			os.write(result.getBytes("UTF-8")); 
			os.flush();
			os.close();
		} else {
			// 2. 안내 페이지 가기 + 정상종료(response)
			request.setAttribute("uri", request.getRequestURI());
			request.setAttribute("exception", errors.toString());
			
			request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);
		}
		
		
	}
}
