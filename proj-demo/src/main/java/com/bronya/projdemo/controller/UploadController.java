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
    public Result<String> upload(@RequestParam("image") MultipartFile image) throws IOException {
        String filename = image.getOriginalFilename();
        if (!StringUtils.hasLength(filename)) return Result.err("Filename Error");
        String extname = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + extname;
        if (System.getProperty("os.name").startsWith("Windows")) {
            image.transferTo(new File("C:\\Users\\admin\\workspace\\frontend\\proj-demo\\public\\" + filename));
        } else {
            image.transferTo(new File("~/workspace/frontend/proj-demo/public/" + filename));
        }
        return Result.ok("Upload OK", "/" + filename);
    }
}
