package com.pw.file.util;

import java.io.File;

public class PwFileUtil {

    public static String createPrefix(String root, String prefix){
        return root + File.separator + prefix;
    }

    public static String createFilePath(String root, String prefix, String fileName){
        return createPrefix(root, prefix) + File.separator + fileName;
    }

    public static String createFilePath(String root, String relative){
        return root + File.separator + relative;
    }

}
