package controllers;

import models.Preferences;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.PreferenceService;

import java.io.FileNotFoundException;

@Controller
@EnableWebMvc
public class PreferenceController {

    @RequestMapping("/preferences")
    public String showCustomizationPage(ModelMap model) {
        Preferences preferences = PreferenceService.getPreferences();
        model.addAttribute("InitialDir",preferences.getInitialDirectory());
        model.addAttribute("MaxNestingLevel",preferences.getMaxNestingLevel());
        model.addAttribute("ShowFoldersOnly",preferences.getShowFoldersOnly());
        model.addAttribute("ShowHidden",preferences.getShowHiddenFiles());
        model.addAttribute("ShowOpenableOnly",preferences.getShowOpenableOnly());
        return "preferences";
    }

    @RequestMapping(value = "/savePrefs", method = RequestMethod.POST)
    public String savePreferences(@RequestBody Preferences pref) {
        try {
            PreferenceService.setPreferences(pref);
        } catch (FileNotFoundException e) {}
        return "redirect:/preferences";
    }

}
