package models;

// for customization of the service
public class Preferences {
    private String initialDirectory = "C:/";
    private Integer maxNestingLevel = 10;
    private Boolean showHiddenFiles = false;
    private Boolean showFoldersOnly = false;

    public Preferences() { }

    public Preferences(String initialDirectory, Integer maxNestingLevel, Boolean showHiddenFiles, Boolean showFoldersOnly, Boolean showOpenableOnly) {
        this.initialDirectory = initialDirectory;
        this.maxNestingLevel = maxNestingLevel;
        this.showHiddenFiles = showHiddenFiles;
        this.showFoldersOnly = showFoldersOnly;
    }

    public Boolean getShowHiddenFiles() {
        return showHiddenFiles;
    }

    public void setShowHiddenFiles(Boolean showHiddenFiles) {
        this.showHiddenFiles = showHiddenFiles;
    }

    public Boolean getShowFoldersOnly() {
        return showFoldersOnly;
    }

    public Integer getMaxNestingLevel() {
        return maxNestingLevel;
    }

    public void setMaxNestingLevel(Integer maxNestingLevel) {
        this.maxNestingLevel = maxNestingLevel;
    }

    public void setShowFoldersOnly(Boolean showFoldersOnly) {
        this.showFoldersOnly = showFoldersOnly;
    }

    public String getInitialDirectory() {
        return initialDirectory;
    }

    public void setInitialDirectory(String initialDirectory) {
        this.initialDirectory = initialDirectory;
    }

}
