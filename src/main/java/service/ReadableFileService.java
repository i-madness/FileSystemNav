package service;

import models.ReadableFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that provides an access to non-binary file's content.
 * Extensions are written down in static block
 */
public class ReadableFileService {

    private static ArrayList<String> openableExtensions = new ArrayList<String>();
    static {
        openableExtensions.add("txt");
        openableExtensions.add("log");
        openableExtensions.add("xml");
        openableExtensions.add("html");
        // may add any readable extension of non-binary file
    }

    /**
     * Returns an object of readable file that has specified path.
     * Path string should have format like 'Root-_-Dir-_-File-__-ext'
     * where -_- is replaced for '/' and -__- is replaced for '.'.
     * If file exists and can be read than
     * @param  path a string of file's path
     * @throws IOException if file is not found
     * @return instance of models.ReadableFile with specified path
     */
    public static ReadableFile getDocumentByPath(String path) throws IOException {
        path = path.replace("-_-","/");
        path = path.replace("-__-",".");
        try {
            path = new String(path.getBytes("ISO8859-1"), "UTF-8"); // handling encoding issues
        } catch (UnsupportedEncodingException e) { return null; }
        java.io.File file = new java.io.File(path);

        String[] fileNameParts = path.split("[.]");
        if (openableExtensions.contains(fileNameParts[fileNameParts.length - 1])){
            BufferedReader reader = null;
            ArrayList<String> lines = new ArrayList<String>();
            try {
                reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null)
                    lines.add(line);
            } finally {
                if (reader != null)
                    reader.close();
            }
            return new ReadableFile(file.getName(),lines);
        }
        else {
            return new ReadableFile(file.getName(), null);
        }

    }



}
