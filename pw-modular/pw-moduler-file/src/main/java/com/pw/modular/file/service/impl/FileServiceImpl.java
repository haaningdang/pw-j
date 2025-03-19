package com.pw.modular.file.service.impl;

import com.pw.api.file.service.FileService;
import com.pw.core.basic.response.PwResponse;
import com.pw.file.PwFileApi;
import com.pw.file.pojo.dto.PwFile;
import com.pw.file.util.PwFileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private PwFileApi pwFileApi;

    @Override
    public PwResponse upload(MultipartFile file) throws Exception {

        PwFile pwFile = PwFileUtil.createPwFile(file);
        pwFileApi.storage(pwFile.getPrefix(), pwFile.getName(), file.getBytes());

        return PwResponse.success(pwFile);
    }

    @Override
    public void preview() {

    }

}
