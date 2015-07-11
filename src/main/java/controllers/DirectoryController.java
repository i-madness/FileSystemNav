package controllers;

import app.DocumentReader;
import models.Directory;
import models.Preferences;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.DirectoryService;
import service.PreferenceService;

import java.io.FileNotFoundException;

@Controller
@RequestMapping("/")
@EnableWebMvc
public class DirectoryController {

	@RequestMapping("/")
	public String printWelcome(@RequestParam(required = false) String str, ModelMap model) {
		//model.addAttribute("smth", str);
		try {
			PreferenceService.setPreferences(new Preferences("C:/",15,true,false,false));
		}catch (Exception e) {}
		return "index";
	}

	@RequestMapping("/dir/{path}")
	@ResponseBody
	public Directory showDir(@PathVariable String path, ModelMap model) {
		Preferences preferences = null;
		try {
			preferences = PreferenceService.getPreferences();
		} catch (FileNotFoundException e) { preferences = new Preferences(); } // just using default values in case of exception
		return DirectoryService.getDirectoryByPath(path, preferences);
	}

	// test controller for .doc files view
	@RequestMapping("/docx")
	public String outputDocx(ModelMap model) {
		DocumentReader documentReader = new DocumentReader("C:/Users/Валерий/Desktop/Курсовая - блог-хостинг.docx");
		model.addAttribute("content", documentReader.contentList);
		return "docx";
	}
}