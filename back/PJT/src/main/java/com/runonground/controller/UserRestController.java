package com.runonground.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.runonground.model.dto.User;
import com.runonground.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@Tag(name = "RunOnGround 유저")
public class UserRestController {
	
	private UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입
	@PostMapping("/signup")
	@Operation(summary = "회원가입")
	public ResponseEntity<Void> signup(@RequestBody User user){
		System.out.println("여기까지 들어왔어용");
		userService.signup(user);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// 로그인
	@PostMapping("/")
	@Operation(summary = "로그인")
	public ResponseEntity<Map<String, String>> login(@RequestBody User user, HttpSession session){
		String loginId = user.getUserId();
		String loginPassword = user.getPassword();
		
		User loginUser = userService.login(loginId, loginPassword);
		
		if(loginUser != null) {
			String loginNickname = loginUser.getNickName();
			session.setAttribute("nickName", loginNickname);
			session.setAttribute("favoriteTeam", loginUser.getTeamName());
			
			Map<String, String> response = new HashMap<>();
			response.put("nickName", loginNickname);
			response.put("favoriteTeam", loginUser.getTeamName());
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	// 로그아웃
	@GetMapping("/logout")
	@Operation(summary = "로그아웃")
	public ResponseEntity<Void> logout(HttpSession session){
		session.invalidate();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
