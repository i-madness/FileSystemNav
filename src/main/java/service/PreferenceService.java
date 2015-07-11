package service;

import models.Preferences;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

// encodes/decodes users preferences to/from xml file
public class PreferenceService {

    public static Preferences getPreferences() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("preferences.xml");
        String path = new File("preferences.xml").getAbsolutePath();
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
    }
}
