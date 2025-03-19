package com.pw.modular.file.controller;

import com.pw.api.file.service.FileService;
import com.pw.core.annotation.PwFetch;
import com.pw.core.annotation.PwRoute;
import com.pw.core.basic.response.PwResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@PwRoute
public class FileController {

    @Resource
    private FileService fileService;

    @PwFetch(url = "/file/upload")
    public PwResponse upload(@RequestParam("file") MultipartFile file) {
        try{
            return fileService.upload(file);
        }catch (Exception e){
            return PwResponse.failure("上传文件失败");
        }
    }

}
