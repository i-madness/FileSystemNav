package service;

import models.Directory;
import models.Preferences;

import java.io.File;
import java.util.ArrayList;

public class DirectoryService {

    // main service method; directory to be shown in json format
    public static Directory getDirectoryByPath(String path, Preferences preferences) throws Exception {
        String correctPath = path.replaceAll("-_-","/"); // path variable format: C:-_-Directory1-_-directory2-_-...
        correctPath = new String(correctPath.getBytes("ISO8859-1"), "UTF-8"); // handling encoding issues
        // this way we look at the nesting level of the folder
        if (correctPath.split("/").length > preferences.getMaxNestingLevel())
            return null;
        File pathDir = new File(correctPath);
        String parent = pathDir.getParent();
        if (pathDir.exists() && pathDir.isDirectory()) {
            ArrayList<String> subdirs = new ArrayList<String>();
            ArrayList<String> files   = new ArrayList<String>();
            File[] allFiles = pathDir.listFiles();
            if (allFiles == null)
                return new Directory(null, null, correctPath, pathDir.getName(), parent);
            for (File f : allFiles)
                if (f.isDirectory() && !f.isHidden() && !f.getName().contains("$")) // to exclude system folders $Recycle.Bin etc.
                    subdirs.add(f.getName());
                else if (f.isFile()) {
                    if (!preferences.getShowHiddenFiles() && !f.isHidden())
                        files.add(f.getName());
                    else if (preferences.getShowHiddenFiles() && f.isHidden())
                        files.add(f.getName());
                }
            Directory directory = new Directory(subdirs, files, correctPath, pathDir.getName(), parent);
            return directory;
        }
        else return null;
    }
}
