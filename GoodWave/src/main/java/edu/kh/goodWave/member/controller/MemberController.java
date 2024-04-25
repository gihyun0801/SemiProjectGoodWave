package edu.kh.goodWave.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberController {

	@GetMapping("login")
	public String login() {
		
		return "member/login";
	}
	
	@GetMapping("signUp")
	public String signup() {
		return "member/signUp";
	}
	
	
	
}
