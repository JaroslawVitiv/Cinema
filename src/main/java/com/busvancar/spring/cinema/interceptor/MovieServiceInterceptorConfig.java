package com.busvancar.spring.cinema.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer ;

@Component
public class MovieServiceInterceptorConfig implements WebMvcConfigurer  {
   

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(new MovieInterceptor());
   }
}