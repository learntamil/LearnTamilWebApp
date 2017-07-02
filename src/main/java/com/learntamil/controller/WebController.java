package com.learntamil.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController 
{
	@Value("${learntamil.welcome.page}")
	private String welcomePage;
	
	@GetMapping("/")
	public String welcome()
	{
		return welcomePage;
	}
}
