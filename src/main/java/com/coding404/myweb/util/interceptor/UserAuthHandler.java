package com.coding404.myweb.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.coding404.myweb.command.UserVO;

public class UserAuthHandler implements HandlerInterceptor {

	//모든페이지에 적용될 인터셉터
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//System.out.println("유저 인터셉터 실행됨");
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO)session.getAttribute("userVO");
		
		if(userVO == null) { //로그인이 안된 시점
			
			response.sendRedirect(request.getContextPath() + "/user/login"); //로그인페이지로
			return false; //컨트롤러를 실행하지 않음
			
		} else { //로그인이 된 시점
			return true;
	
		}
		
		
	}

	
}
