package io.github.imadness.fsnav.service;

import io.github.imadness.fsnav.models.ReadableFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class that provides an access to non-binary file's content.
 */
@Service
public class ReadableFileService {

    /**
     * List of all extensions which are considered openable by the application.
     * Any non-binary extension
     */
    private List<String> openableExtensions = Arrays.asList("txt", "log", "xml", "html");

    /**
     * Returns an object of readable file that has specified path.
     * Path string should have format like 'Root-_-Dir-_-File-__-ext' where -_- is replaced for '/' and -__- is replaced for '.'.
     * (И да, это чудовищный костыль, в чём автор явно отдавал себе отчёт)
     *
     * @param path a string of file's path
     * @return instance of models.ReadableFile with specified path
     * @throws IOException if file is not found
     */
    public ReadableFile getDocumentByPath(String path) throws IOException {
        path = path.replaceAll("-_-", "/").replaceAll("-__-", ".");
        try {
            path = new String(path.getBytes("ISO8859-1"), "UTF-8"); // handling encoding issues
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        File file = new File(path);
        if (openableExtensions.contains(FilenameUtils.getExtension(path))) {
            ArrayList<String> lines = new ArrayList<String>();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }
            return new ReadableFile(file.getName(), lines);
        } else {
            return new ReadableFile(file.getName(), null);
        }

    }
}