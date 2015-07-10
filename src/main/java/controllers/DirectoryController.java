package controllers;

import app.Wat;
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
		//model.addAttribute("smth", str);
		return "index";
	}

	@RequestMapping("/dir/{path}")
	@ResponseBody
	public Directory showDir(@PathVariable String path) {
		return DirectoryService.getDirectoryByPath(path);
	}

	// test controller for .doc files view
	@RequestMapping("/docx")
	public String outputDocx(ModelMap model) {
		Wat wat = new Wat("C:/Users/Валерий/Desktop/Курсовая - блог-хостинг.docx");
		model.addAttribute("content",wat.contentList);
		return "docx";
	}
}