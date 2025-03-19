package com.pw.file.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.pw.core.util.IdGenerator;
import com.pw.file.pojo.dto.PwFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;

public class PwFileUtil {

    /**
     * 解析文件的后缀
     * @param fileName
     * @return
     */
    public static String parseSuffix(String fileName) {
        if(fileName!= null && fileName.contains(".")){
            return fileName.substring(fileName.lastIndexOf(".")+1);
        }
        return "";
    };

    /**
     * 创建文件路径
     * @param root
     * @param prefix
     * @return
     */
    public static String createPrefix(String root, String prefix){
        return root + File.separator + prefix;
    }

    /**
     * 创建文件完整路径
     * @param root
     * @param prefix
     * @param fileName
     * @return
     */
    public static String createFilePath(String root, String prefix, String fileName){
        return createPrefix(root, prefix) + File.separator + fileName;
    }

    /**
     * 创建文件路径
     * @param root
     * @param relative
     * @return
     */
    public static String createFilePath(String root, String relative){
        return root + File.separator + relative;
    }

    /**
     * 拼接文件目录
     * @param path
     * @return
     */
    public static String join(String... path){
        return CharSequenceUtil.join(File.separator, path);
    }

    /**
     * 创建日期相对路径
     * @return
     */
    public static String createDatePath(){
        Date now = new Date();
        return join(DateUtil.format(now,"yyyy"), DateUtil.format(now, "MM"), DateUtil.format(now, "dd"));
    }

    /**
     * 创建文件对象
     * @param file
     * @return
     */
    public static PwFile createPwFile(MultipartFile file){
        String origin = file.getOriginalFilename();
        String suffix = parseSuffix(origin);
        String name = IdGenerator.uuid() + "." + suffix;
        String prefix = createDatePath();
        String relative = join(prefix, name);

        PwFile pwFile = new PwFile();
        pwFile.setOrigin(origin);
        pwFile.setName(name);
        pwFile.setSuffix(suffix);
        pwFile.setRelative(relative);
        pwFile.setSize(file.getSize());
        pwFile.setPrefix(prefix);
        return pwFile;
    }

}
