package com.backend.CreativityMarket.Security;

import com.backend.CreativityMarket.User.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserStatusInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null) {
            return true; // let AuthController handle unauthenticated users
        }

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return true;
        }

        // =========================
        // BANNED CHECK
        // =========================
        if (user.isBanned()) {
            session.invalidate();
            response.sendRedirect("/signin?error=banned");
            return false;
        }

        // =========================
        // SUSPENDED CHECK
        // =========================
        if (user.isSuspended()) {
            response.sendRedirect("/signin?error=suspended");
            return false;
        }

        return true;
    }
}