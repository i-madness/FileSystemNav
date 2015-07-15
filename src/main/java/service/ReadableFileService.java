package service;

import models.ReadableFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Валерий on 10.07.2015.
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

    public static ReadableFile getDocumentByPath(String path) throws Exception {
        path = path.replace("-_-","/");
        path = path.replace("-__-",".");
        path = new String(path.getBytes("ISO8859-1"), "UTF-8"); // handling encoding issues
        java.io.File file = new java.io.File(path);
        if (file.getName().contains(".doc")) {
            WordprocessingMLPackage wmlp = WordprocessingMLPackage.load(file);
            List<Object> objs = wmlp.getMainDocumentPart().getContent();
            ArrayList<String> lines = new ArrayList<String>();
            for (Object obj : objs)
                lines.add(obj.toString());
            return new ReadableFile(file.getName(), lines);
        }
        else { // looking at the extension
            String[] fileNameParts = path.split("[.]");
            if (openableExtensions.contains(fileNameParts[fileNameParts.length - 1])){
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                ArrayList<String> lines = new ArrayList<String>();
                while ((line = reader.readLine()) != null)
                    lines.add(line);
                reader.close();
                return new ReadableFile(file.getName(),lines);
            }
            else {
                return new ReadableFile(file.getName(), null);
            }
        }
    }



}
