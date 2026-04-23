package com.backend.CreativityMarket.Security;

import com.backend.CreativityMarket.User.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/signin");
            return false;
        }

        User user = (User) session.getAttribute("user");

        if (user == null || user.getId() == null) {
            response.sendRedirect("/signin");
            return false;
        }

        if (!user.isAdminOrAbove()) {
            response.sendRedirect("/users/home");
            return false;
        }

        return true;
    }
}