package controllers;

import models.ReadableFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.ReadableFileService;

import java.io.IOException;


/**
 * Controller that provides file output into the page
 */
@Controller
@EnableWebMvc
public class ReadableFileController {

    /**
     * Object of models.ReadableFile to be recieved by client in JSON format
     * @param path a path variable
     * @return specified ReadableFile
     * @throws Exception
     */
    @RequestMapping("/file/{path}")
    @ResponseBody
    public ReadableFile showDocumentObject(@PathVariable String path) {
        try {
            return ReadableFileService.getDocumentByPath(path);
        }   catch(IOException e) {
            return new ReadableFile(path,null);
        }
    }

}
