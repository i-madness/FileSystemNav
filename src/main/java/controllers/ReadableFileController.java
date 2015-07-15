package controllers;

import models.ReadableFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import service.ReadableFileService;

@Controller
@EnableWebMvc
public class ReadableFileController {

    @RequestMapping("/file/{path}")
    @ResponseBody
    public ReadableFile showDocumentObject(@PathVariable String path) throws Exception {
        return ReadableFileService.getDocumentByPath(path);
    }

}
