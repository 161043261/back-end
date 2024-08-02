package com.bronya.projdemo.controller;

import com.bronya.projdemo.dao.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        if (!StringUtils.hasLength(filename)) return Result.err("Filename is Empty");
        String extname = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + extname;
        if (System.getProperty("os.name").startsWith("Windows")) {
            file.transferTo(new File("C:\\Users\\admin\\Downloads\\" + filename));
        }
        if (System.getProperty("os.name").startsWith("Mac")) {
            file.transferTo(new File("/Users/admin/Downloads/" + filename));
        }
        return Result.ok("Upload OK", "filename=" + filename);
    }
}
