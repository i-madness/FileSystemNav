package controllers;

import models.Directory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.DirectoryService;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class DirectoryController {
	@RequestMapping("/")
	public String printWelcome(@RequestParam(required = false) String str, ModelMap model) {
		model.addAttribute("garrr", str);
		return "hello";
	}
	@RequestMapping("/dir/{path}")
	@ResponseBody
	public Directory showDir(@PathVariable String path) {
		return DirectoryService.getDirectoryByPath(path);
	}
	/*@RequestMapping("/nav")
	public String showDir(@RequestParam(value = "name", required = false) String name) {
		return "index";
	}*/
}