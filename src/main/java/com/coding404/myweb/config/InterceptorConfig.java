package com.coding404.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coding404.myweb.util.interceptor.MenuHandler;
import com.coding404.myweb.util.interceptor.UserAuthHandler;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	//인터셉터 관련 스프링설정파일
	
	//유저핸들러
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	//메뉴핸들러
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}
		
	
	//webMvcConfigurer의 메서드를 오버라이딩
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//로그인 프리핸들러
//		registry.addInterceptor( userAuthHandler() )
//				.addPathPatterns("/main") //메인페이지
//				.addPathPatterns("/user/**") //user페이지들에 적용
//				.addPathPatterns("/product/**") //product페이지들에 적용
//				//.addPathPatterns("/xxx/**") //예시...
//				.excludePathPatterns("/user/login"); //패스에서 제외
		
		
		//메뉴 포스트핸들러
		registry.addInterceptor( menuHandler() )
				.addPathPatterns("/product/**");
				//.addPathPatterns("/xxx/**")
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
