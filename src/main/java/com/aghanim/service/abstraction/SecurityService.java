package com.aghanim.service.abstraction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SecurityService {
    String findLoggedInLogin();

    void autoLogin(String login, String password, HttpServletRequest req, HttpServletResponse resp);
}
