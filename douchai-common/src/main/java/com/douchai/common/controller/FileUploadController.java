package com.douchai.common.controller;


import com.douchai.common.file.UploadUtils;
import com.douchai.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 图片上传到七牛云
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class FileUploadController {

    @PostMapping("/user")
    public ResponseResult uploadUser(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UploadUtils.uploadImages(file);
        return ResponseResult.success((Object) fileName);
    }

    @PostMapping("/movie")
    public ResponseResult uploadMovie(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UploadUtils.uploadImages(file);
        return ResponseResult.success((Object) fileName);
    }

    @PostMapping("/cinema")
    public ResponseResult uploadCinema(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UploadUtils.uploadImages(file);
        return ResponseResult.success((Object) fileName);
    }

    @PostMapping("/actor")
    public ResponseResult uploadActor(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = UploadUtils.uploadImages(file);
        return ResponseResult.success((Object) fileName);
    }

    @RequestMapping("/delete")
    public ResponseResult deletePicture(String filePath){
        boolean delFile = UploadUtils.delFile(filePath);
        if(delFile){
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

}
