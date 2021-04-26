package com.busvancar.spring.cinema.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

public class MovieInterceptor implements HandlerInterceptor {
	   
	   @Override
	   public boolean preHandle
	      (HttpServletRequest request, HttpServletResponse response, Object handler) 
	      throws Exception {
		   //response.setHeader("Access-Control-Allow-Origin", "*");
		   // response.setHeader("Access-Control-Allow-Methods",  "GET, POST, PUT, PATCH, DELETE, OPTIONS");
		   
		   
	      System.out.println("Pre Handle method is Calling");
	      return true;
	   }
	   @Override
	   public void postHandle(HttpServletRequest request, HttpServletResponse response, 
	      Object handler, ModelAndView modelAndView) throws Exception {
	      
	      System.out.println("Post Handle method is Calling");
	   }
	   @Override
	   public void afterCompletion
	      (HttpServletRequest request, HttpServletResponse response, Object 
	      handler, Exception exception) throws Exception {
	      
	      System.out.println("Request and Response is completed");
	   }
	}