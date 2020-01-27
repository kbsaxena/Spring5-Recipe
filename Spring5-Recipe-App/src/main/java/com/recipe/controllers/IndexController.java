package com.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping({"/","","/index"})
	public String indexPage(Model model) {
		model.addAttribute("index","Welcome To Index Page");
		return "index";
	}
}
