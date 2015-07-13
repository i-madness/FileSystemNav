package models;

import java.util.ArrayList;

/**
 * Created by Валерий on 13.07.2015.
 */
public class ReadableFile {
    private String name;
    private ArrayList<String> content;

    public ReadableFile() {}

    public ReadableFile(String name, ArrayList<String> content) {
        this.name = name;
        this.content = content;
    }

    public ArrayList<String> getContent() {
        return content;
    }

    public void setContent(ArrayList<String> content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
