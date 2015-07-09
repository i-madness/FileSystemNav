package models;

import java.util.ArrayList;

public class Directory {
    private ArrayList<String> subdirectories;
    private ArrayList<String> files;
    private String path;
    private String name;

    public Directory() {}

    public Directory(ArrayList<String> subdirectories, ArrayList<String> files, String path, String name) {
        this.subdirectories = subdirectories;
        this.files = files;
        // this.level = level;
        this.path = path;
        this.name = name;
    }

    public void addSubDir(String sub) {
        subdirectories.add(sub);
    }

/*    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSubdirectories() {
        return subdirectories;
    }

    public void setSubdirectories(ArrayList<String> subdirectories) {
        this.subdirectories = subdirectories;
    }

    public ArrayList<String> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<String> files) {
        this.files = files;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
