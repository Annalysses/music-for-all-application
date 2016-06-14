package com.musicforall.web;

import com.musicforall.files.manager.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Evgeniy on 11.06.2016.
 */

@Controller
public class FileController {

    @Autowired
    private FileManager manager;

    @RequestMapping(value = "/files", method = RequestMethod.POST)
    public
    @ResponseBody
    String uploadFileHandler(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            boolean saved = manager.save(file);
            return saved ? "Hurray!" : "Oops!";
        }
        return "File is empty";
    }

    @RequestMapping(value = "/files/{fileName:.+}", method = RequestMethod.GET)
    public void getFileHandler(HttpServletRequest request, HttpServletResponse response, @PathVariable("fileName") String name) {
        Path filePath = manager.getFileByName(name);
        if (filePath == null) return;

        try {
            Files.copy(filePath, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/tryFiles", method = RequestMethod.GET)
    public String signUp() {
        return "tryFiles";
    }
}
