package io.github.imadness.fsnav.service;

import io.github.imadness.fsnav.models.Directory;
import io.github.imadness.fsnav.models.Preferences;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;

/**
 * Handles all matters with directory lookup
 */
@Service
public class DirectoryService {
    /**
     * Returns the directory with specified path.
     * The list of subdirectories and nested files is defined by current preferences
     *
     * @param path        a string of directory's path
     * @param preferences an instance of current preferences on service
     * @return an instance of models.Directory with specified path
     */
    public Directory getDirectoryByPath(String path, Preferences preferences) {
        String correctPath = path.replaceAll("-_-", "/").replaceAll("-__-", ".");
        // this way we look at the nesting level of the folder
        File pathDir = new File(correctPath);
        String parent = pathDir.getParent();
        if (correctPath.split("/").length > preferences.getMaxNestingLevel()) {
            return new Directory(null, null, correctPath, pathDir.getName() + "(forbidden!)", parent);
        }
        if (pathDir.exists() && pathDir.isDirectory()) {
            ArrayList<String> subdirs = new ArrayList<String>();
            ArrayList<String> files = new ArrayList<String>();
            File[] allFiles = pathDir.listFiles();
            if (allFiles == null) {
                return new Directory(null, null, correctPath, pathDir.getName(), parent);
            }
            for (File f : allFiles) {
                if (f.isDirectory() && !f.isHidden() && !f.getName().startsWith("$")) {// to exclude system folders $Recycle.Bin etc.
                    subdirs.add(f.getName());
                }
                else if (f.isFile() && !preferences.getShowFoldersOnly()) {
                    if (!preferences.getShowHiddenFiles() && !f.isHidden()) {
                        files.add(f.getName());
                    }
                    else if (preferences.getShowHiddenFiles() && f.isHidden()) {
                        files.add(f.getName());
                    }
                }
            }
            Directory directory = new Directory(subdirs, files, correctPath, pathDir.getName(), parent);
            return directory;
        }
        return null;
    }
}
