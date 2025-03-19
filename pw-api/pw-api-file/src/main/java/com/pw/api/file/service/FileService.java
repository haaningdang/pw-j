package com.pw.api.file.service;

import com.pw.core.basic.response.PwResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    PwResponse upload(MultipartFile file) throws Exception;

    void preview();

}
