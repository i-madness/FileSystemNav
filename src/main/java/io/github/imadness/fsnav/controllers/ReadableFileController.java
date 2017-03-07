package io.github.imadness.fsnav.controllers;

import io.github.imadness.fsnav.models.ReadableFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import io.github.imadness.fsnav.service.ReadableFileService;

import java.io.IOException;


/**
 * Controller that provides file output into the page
 */
@Controller
public class ReadableFileController {
    @Autowired
    private ReadableFileService readableFileService;

    /**
     * Object of models.ReadableFile to be received by client in JSON format
     *
     * @param path a path variable
     * @return specified ReadableFile
     * @throws Exception
     */
    @RequestMapping("/file/{path}")
    @ResponseBody
    public ReadableFile showDocumentObject(@PathVariable String path) {
        try {
            return readableFileService.getDocumentByPath(path);
        }   catch(IOException e) {
            return new ReadableFile(path, null);
        }
    }

}
