package io.github.imadness.fsnav.controllers;

import io.github.imadness.fsnav.models.Preferences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import io.github.imadness.fsnav.service.PreferenceService;

import java.io.FileNotFoundException;

/**
 * Controller that handles preferences mapping
 */
@Controller
@EnableWebMvc
public class PreferenceController {
    @Autowired
    private PreferenceService preferenceService;

    /**
     * Shows current preferences in appropriate page
     *
     * @param model ModelMap to add all data from preferences and to show it on the page
     * @return the name of preferences page
     */
    @RequestMapping("/preferences")
    public String showCustomizationPage(ModelMap model) {
        Preferences preferences = preferenceService.getPreferences();
        model.addAttribute("InitialDir", preferences.getInitialDirectory());
        model.addAttribute("MaxNestingLevel", preferences.getMaxNestingLevel());
        model.addAttribute("ShowFoldersOnly", preferences.getShowFoldersOnly());
        model.addAttribute("ShowHidden", preferences.getShowHiddenFiles());
        return "preferences";
    }

    /**
     * Page that saves current preferences received in JSON from client via POST request method
     *
     * @param pref received object of preferences
     */
    @RequestMapping(value = "/savePrefs", method = RequestMethod.POST)
    public String savePreferences(@RequestBody Preferences pref) {
        try {
            preferenceService.setPreferences(pref);
        } catch (FileNotFoundException e) {
            return "Не удалось сохранить файл настроек";
        }
        return "redirect:/preferences";
    }

}
