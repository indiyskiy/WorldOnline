package model.additionalentity.admin;

import java.io.File;
import java.util.HashMap;

public class ParsedRequest {
    private HashMap<String, String> textMap = new HashMap<>();
    private HashMap<String, File> fileMap = new HashMap<>();

    public ParsedRequest(HashMap<String, String> textMap, HashMap<String, File> fileMap) {
        this.textMap = textMap;
        this.fileMap = fileMap;
    }

    public HashMap<String, String> getTextMap() {
        return textMap;
    }

    public HashMap<String, File> getFileMap() {
        return fileMap;
    }
}
