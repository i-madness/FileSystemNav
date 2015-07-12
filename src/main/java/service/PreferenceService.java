package service;

import models.Preferences;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// encodes/decodes users preferences to/from xml file
public class PreferenceService {


    private static Preferences currentPreferences;
    static {
        try {
            currentPreferences = getPreferencesFromXML();
        } catch (FileNotFoundException e) {
            currentPreferences = new Preferences();
        }
    }

    public static Preferences getPreferences() {
        return currentPreferences;
    }

    public static Preferences getPreferencesFromXML() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("preferences.xml");
        //String path = new File("preferences.xml").getAbsolutePath();
        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
        Preferences preferences = (Preferences) xmlDecoder.readObject();
        xmlDecoder.close();
        return preferences;
    }

    public static void setPreferences(Preferences prefs) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("preferences.xml");
        XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
        xmlEncoder.writeObject(prefs);
        xmlEncoder.flush();
        xmlEncoder.close();
        currentPreferences = prefs;
    }
}
