package service;

import models.Directory;

import java.io.File;
import java.util.ArrayList;

public class DirectoryService {

    // main service method; directory to be shown in json format
    public static Directory getDirectoryByPath(String path) {
        String correctPath = path.replaceAll("-_-","/"); // path variable format: C:-_-Directory1-_-directory2-_-...
        File pathDir = new File(correctPath);
        String parent = pathDir.getParent();
        if (pathDir.exists() && pathDir.isDirectory()) {
            ArrayList<String> subdirs = new ArrayList<String>();
            ArrayList<String> files   = new ArrayList<String>();
            File[] allFiles = pathDir.listFiles();
            for (File f : allFiles)
                if (f.isDirectory() && f.getName().charAt(0)!='$') // to exclude system folders $Recycle.Bin etc.
                    subdirs.add(f.getName());
                else if (f.isFile())
                    files.add(f.getName());
            Directory directory = new Directory(subdirs, files, correctPath, pathDir.getName(), parent);
            return directory;
        }
        else return null;
    }
}
