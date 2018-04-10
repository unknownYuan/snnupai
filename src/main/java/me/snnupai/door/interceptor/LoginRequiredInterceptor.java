package me.snnupai.door.interceptor;

import me.snnupai.door.model.HostHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(LoginRequiredInterceptor.class);

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (hostHolder.getUser() == null) {
            httpServletResponse.sendRedirect("/reglogin?next=" + httpServletRequest.getRequestURI());
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("post handle...");
        logger.info(hostHolder.getUser().getNickName() + "正在访问...");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("afterCompletion...");
        logger.info(hostHolder.getUser().getNickName() + "正在访问...");
    }
}
