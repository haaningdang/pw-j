package com.pw.login;

public interface PwLoginApi<T> {

    T context();

    void login(String token);

    void refresh();

}
