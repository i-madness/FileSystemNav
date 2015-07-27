package service;

import models.Preferences;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Stores current service preferences and provides access to it
 */
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

    /**
     * Decodes file preferences.xml to get an instance of current preferences
     * @return models.Preferences object with current
     * @throws FileNotFoundException if file not exists
     */
    public static Preferences getPreferencesFromXML() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("preferences.xml");
        //String path = new ReadableFile("preferences.xml").getAbsolutePath();
        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
        Preferences preferences = (Preferences) xmlDecoder.readObject();
        xmlDecoder.close();
        return preferences;
    }

    /**
     * Encodes current preferences to preferences.xml
     * @param prefs preferences object to be encoded
     * @throws FileNotFoundException if file not exists
     */
    public static void setPreferences(Preferences prefs) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("preferences.xml");
        XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream);
        xmlEncoder.writeObject(prefs);
        xmlEncoder.flush();
        xmlEncoder.close();
        currentPreferences = prefs;
    }
}
