package com.coding404.myweb.command;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

	@NotBlank(message = "아이디는 필수입니다")
	private String id;
	
	@NotBlank(message = "비밀번호는 필수입니다")
	private String pw;
	
	//.... +
	
	
}
