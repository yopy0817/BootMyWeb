package com.coding404.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MenuHandler implements HandlerInterceptor {

	//메뉴처리를 위한 핸들러(post핸들러)
	//컨트롤러 실행 이후에 동작
	//컨트롤러에서 ModelAndView으로 반환 시키면 Post핸들에서 제어가능
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("메뉴포스트핸들러:" + request.getRequestURI()); 
		
		//포워드 방식에 request객체에 값을 저장한다.
		//컨트롤러에서 사용하는 Model과 동일
		request.setAttribute("menu", request.getRequestURI());
	}

	
	
	
	
}
