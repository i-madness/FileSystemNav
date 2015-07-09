package service;

import models.Directory;

import java.io.File;
import java.util.ArrayList;

public class DirectoryService {

    // main service method; directory to be shown in json format
    public static Directory getDirectoryByPath(String path) {
        String correctPath = path.replaceAll("-_-","/"); // path variable format: C:-_-Directory1-_-directory2-_-...
        File pathDir = new File(correctPath);
        if (pathDir.exists() && pathDir.isDirectory()) {
            ArrayList<String> subdirs = new ArrayList<String>();
            ArrayList<String> files   = new ArrayList<String>();
            File[] allFiles = pathDir.listFiles();
            for (File f : allFiles)
                if (f.isDirectory() && f.getName().charAt(0)!='$')
                    subdirs.add(f.getName());
                else if (f.isFile())
                    files.add(f.getName());
            Directory directory = new Directory(subdirs, files, correctPath, pathDir.getName());
            return directory;
        }
        else return null;
    }

/*    // factory for the navigation tree
    public static void createNavTree(int level, int maxlevel, Directory start) {
        if (level > maxlevel)
            return;
        File dir = new File(start.getName());
        File[] list = dir.listFiles();
        if (list == null)
            return;
        for (File f : list) {
            if (f.isDirectory() && f.getName().charAt(0)!='$') //to exclude system folders like $RECYCLE_BIN etc.
                start.addSubDir(new Directory(new ArrayList<Directory>(),level+1,f.getPath()));
        }
        for (Directory d : start.getSubdirectories())
        createNavTree(level +1,maxlevel,d);
    }*/

}
