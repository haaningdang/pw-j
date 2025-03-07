package com.pw.file;

import java.io.InputStream;

public interface PwFileApi {

    void init();

    boolean mkdir(String prefix);

    boolean dirExists(String prefix);

    boolean fileExists(String prefix, String fileName);

    void storage(String prefix, String fileName, byte[] bytes);

    void storage(String prefix, String fileName, InputStream inputStream);

    boolean remove(String relative);

    byte[] getBytes(String prefix, String fileName);

}
