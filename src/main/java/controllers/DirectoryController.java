package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class DirectoryController {
	@RequestMapping("/")
	public String printWelcome(@RequestParam String str, ModelMap model) {
		if (str == null)
			str = "damn you";

		model.addAttribute("garrr", str);
		return "hello";
	}
}