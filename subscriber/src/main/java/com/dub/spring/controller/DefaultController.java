package com.dub.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping("/")
	public String index(ModelMap model) {
		
		return "convexHull/convexHull";
	}
	
	@RequestMapping("/convexHull")
	public String convexHull() {
		
		return "convexHull/convexHull";
	}

}
