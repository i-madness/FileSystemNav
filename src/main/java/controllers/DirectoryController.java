package controllers;

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

@Controller
@RequestMapping("/")
@EnableWebMvc
public class DirectoryController {

	@RequestMapping("/")
	public String printWelcome(@RequestParam(required = false) String str, ModelMap model) {
		model.addAttribute("initialDirectory",PreferenceService.getPreferences().getInitialDirectory());
		return "index";
	}

	@RequestMapping("/dir/{path}")
	@ResponseBody
	public Directory showDir(@PathVariable String path) throws Exception {
		Preferences preferences = PreferenceService.getPreferences();
		return DirectoryService.getDirectoryByPath(path, preferences);
	}

}