package me.snnupai.door.interceptor;



//import me.snnupai.door.dao.LoginTicketDAO;
//import me.snnupai.door.dao.UserDAO;
import me.snnupai.door.mapper.LoginTicketMapper;
import me.snnupai.door.mapper.UserMapper;
import me.snnupai.door.model.HostHolder;
//import me.snnupai.door.model.User;
import me.snnupai.door.pojo.LoginTicket;
import me.snnupai.door.pojo.User;
import me.snnupai.door.service.LoginTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@Component
public class PassportInterceptor implements HandlerInterceptor {


//    @Autowired
//    LoginTicketMapper loginTicketMapper;
    @Autowired
    LoginTicketService loginTicketService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }

        if (ticket != null) {
            LoginTicket loginTicket = loginTicketService.selectByTicket(ticket);
            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                return true;
            }

            User user = userMapper.selectByPrimaryKey(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//        if (modelAndView != null && hostHolder.getUser() != null) {
//            modelAndView.addObject("user", hostHolder.getUser());
//        }


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        hostHolder.clear();
    }
}
