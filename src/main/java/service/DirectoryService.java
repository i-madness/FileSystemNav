package service;

import models.Directory;
import models.Preferences;

import java.io.File;
import java.util.ArrayList;

public class DirectoryService {

    private static ArrayList<String> openableExtensions = new ArrayList<String>();
    static {
        openableExtensions.add("doc");
        openableExtensions.add("docx");
        openableExtensions.add("txt");
        openableExtensions.add("html");
        openableExtensions.add("xml");
    }
    // main service method; directory to be shown in json format
    public static Directory getDirectoryByPath(String path, Preferences preferences) {
        String correctPath = path.replaceAll("-_-","/"); // path variable format: C:-_-Directory1-_-directory2-_-...
        // this way we look at the nesting level of the folder
        if (correctPath.split("/").length > preferences.getMaxNestingLevel())
            return null;
        File pathDir = new File(correctPath);
        String parent = pathDir.getParent();
        if (pathDir.exists() && pathDir.isDirectory()) {
            ArrayList<String> subdirs = new ArrayList<String>();
            ArrayList<String> files   = new ArrayList<String>();
            File[] allFiles = pathDir.listFiles();
            for (File f : allFiles)
                if (f.isDirectory() && f.getName().charAt(0)!='$') // to exclude system folders $Recycle.Bin etc.
                    subdirs.add(f.getName());
                else if (f.isFile() && !preferences.getShowFoldersOnly()) {
                    // if necessary to show openable files only, we watch at the files extension this way
                    if (preferences.getShowOpenableOnly() && openableExtensions.contains(f.getName().split(".")[f.getName().length() - 1]))
                        files.add(f.getName());
                    else {
                        if (!preferences.getShowHiddenFiles() && !f.isHidden())
                            files.add(f.getName());
                        else files.add(f.getName());
                    }
                }
            Directory directory = new Directory(subdirs, files, correctPath, pathDir.getName(), parent);
            return directory;
        }
        else return null;
    }
}
