package models;

import java.util.ArrayList;

public class Directory {
    private ArrayList<Directory> subdirectories;
    private Integer level;
    private String name;

    public Directory() {}

    public Directory(ArrayList<Directory> subdirectories, Integer level, String name) {
        this.subdirectories = subdirectories;
        this.level = level;
        this.name = name;
    }

    public void addSubDir(Directory sub) {
        subdirectories.add(sub);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Directory> getSubdirectories() {
        return subdirectories;
    }

    public void setSubdirectories(ArrayList<Directory> subdirectories) {
        this.subdirectories = subdirectories;
    }
}
