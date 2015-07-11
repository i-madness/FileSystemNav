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
        Preferences preferences = null;
        try {
            preferences = PreferenceService.getPreferences();
        } catch (FileNotFoundException e) {
            preferences = new Preferences();
            try { PreferenceService.setPreferences(preferences); } catch (Exception ex) {}
        }
        model.addAttribute("InitialDir",preferences.getInitialDirectory());
        model.addAttribute("MaxNestingLevel",preferences.getMaxNestingLevel());
        model.addAttribute("ShowFoldersOnly",preferences.getShowFoldersOnly());
        model.addAttribute("ShowHidden",preferences.getShowHiddenFiles());
        model.addAttribute("ShowOpenableOnly",preferences.getShowOpenableOnly());
        return "preferences";
    }

    @RequestMapping(value = "/savePrefs", method = RequestMethod.POST)
    public String savePreferences(@RequestBody Preferences pref/*@RequestParam(value="initialDir", required=false)   String initialDir,
                                  @RequestParam(value="maxNesting", required=false)   int maxNesting,
                                  @RequestParam(value="showHidden", required=false)   Boolean showHidden,
                                  @RequestParam(value="showFolders", required=false)  Boolean showFolders,
                                  @RequestParam(value="showOpenable", required=false) Boolean showOpenable*/) {
        try {
            //PreferenceService.setPreferences(new Preferences(initialDir,maxNesting,showHidden,showFolders,showOpenable));
            PreferenceService.setPreferences(pref);
        } catch (FileNotFoundException e) {}
        return "redirect://";
    }

}
