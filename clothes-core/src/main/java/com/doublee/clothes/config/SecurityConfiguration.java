package com.doublee.clothes.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfiguration implements Filter{

	@Value("${api.key}")
	private String apiKey;
		
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String Authorization = httpRequest.getHeader("X-Authorization");
		
		if (Authorization != null && Authorization.equals(apiKey))		
			chain.doFilter(request, response);
		else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Forbidden Access to this resource.");
		}		
	}

	@Override
	public void destroy() {
	}

}
