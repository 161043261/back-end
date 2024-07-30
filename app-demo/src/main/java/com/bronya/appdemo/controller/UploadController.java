package com.bronya.appdemo.controller;

import com.bronya.appdemo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @PostMapping("/upload")
    public Result upload(String username, int age, @RequestParam("image") MultipartFile image) throws IOException {
        log.info("username={}, age={}, image={}", username, age, image);
        String filename = image.getOriginalFilename();
        if (filename == null) {
            return Result.error("error");
        }
        String extname = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + extname;
        if (System.getProperty("os.name").startsWith("Windows")) {
            image.transferTo(new File("C:\\Users\\admin\\Downloads\\" + filename));
        }
        if (System.getProperty("os.name").startsWith("Mac")) {
            image.transferTo(new File("/Users/admin/Downloads/" + filename));
        }
        return Result.success();
    }
}
