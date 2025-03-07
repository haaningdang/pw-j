package com.pw.api.auth.service;

import com.pw.api.auth.pojo.request.AuthRequest;
import com.pw.core.basic.response.PwResponse;

public interface AuthService {

    PwResponse login(AuthRequest request);

    PwResponse logout(AuthRequest request);

}
