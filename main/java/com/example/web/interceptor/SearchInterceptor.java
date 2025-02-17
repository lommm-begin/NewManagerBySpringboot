package com.example.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SearchInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SearchInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("SearchInterceptor preHandle执行");

        HttpSession session = request.getSession(false);

        if (session != null) {
            if (session.getAttribute("adminname") != null) {
                return true;
            }
        }

        request.getRequestDispatcher("/user/search").forward(request, response);

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("SearchInterceptor postHandle执行");
    }

}
