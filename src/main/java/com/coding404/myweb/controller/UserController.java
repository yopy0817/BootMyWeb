package com.coding404.myweb.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coding404.myweb.command.UserVO;


@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/userDetail")
	public String userDetail() {
		return "user/userDetail";
	}
	
	//로그인 폼
	@PostMapping("/login")
	public String loginForm(@Valid UserVO vo, Errors errors, 
							Model model, 
							HttpSession session  ) {
		

		//유효성검사
		if(errors.hasErrors()) {
			List<FieldError> list = errors.getFieldErrors();
			for(FieldError err : list ) {
				model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
			}
			
			return "user/login"; //실패시에 다시 로그인페이지로
			
		}
		
		
		//로그인.....
		//메서드모형 public UserVO login(UserVO vo);
		//SELECT * FROM 유저테이블 WHERE ID = ~~~ AND PW = ~~~;
		//UserVO의 값이 null이라면, 로그인 실패, null이 아니라면 로그인 성공
		
		//세션에 회원정보를 저장
		UserVO userVO = UserVO.builder().id("예제세션아이디").build();
		
		session.setAttribute("userVO", userVO);
		
		
		
		return "redirect:/main"; //메인페이지로
	}
	
	
	
	
	
}
