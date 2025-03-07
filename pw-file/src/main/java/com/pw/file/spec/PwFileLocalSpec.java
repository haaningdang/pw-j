package com.pw.file.spec;

import cn.hutool.core.io.FileUtil;
import com.pw.file.PwFileApi;
import com.pw.file.properties.PwFileProperties;
import com.pw.file.util.PwFileUtil;

import java.io.InputStream;

public class PwFileLocalSpec implements PwFileApi {

    private final PwFileProperties properties;

    public PwFileLocalSpec(PwFileProperties properties) {
        this.properties = properties;
        init();
    }

    @Override
    public void init() {

    }

    @Override
    public boolean mkdir(String prefix) {
        if(!dirExists(prefix)){
            FileUtil.mkdir(PwFileUtil.createPrefix(properties.getRoot(), prefix));
        }
        return true;
    }

    @Override
    public boolean dirExists(String prefix) {
        return FileUtil.exist(PwFileUtil.createPrefix(properties.getRoot(), prefix));
    }

    @Override
    public boolean fileExists(String prefix, String fileName) {
        return FileUtil.exist(PwFileUtil.createFilePath(properties.getRoot(), prefix, fileName));
    }

    @Override
    public void storage(String prefix, String fileName, byte[] bytes) {
        mkdir(prefix);
        FileUtil.writeBytes(bytes, PwFileUtil.createFilePath(properties.getRoot(), prefix, fileName));
    }

    @Override
    public void storage(String prefix, String fileName, InputStream inputStream) {
        mkdir(prefix);
        FileUtil.writeFromStream(inputStream, PwFileUtil.createFilePath(properties.getRoot(), prefix, fileName));
    }

    @Override
    public boolean remove(String relative) {
        return FileUtil.del(relative);
    }

    @Override
    public byte[] getBytes(String prefix, String fileName) {
        return FileUtil.readBytes(PwFileUtil.createFilePath(properties.getRoot(), prefix, fileName));
    }

}
