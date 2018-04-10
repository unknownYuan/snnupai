package me.snnupai.door.controller;

import me.snnupai.door.listener.OnLineListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

import static me.snnupai.door.listener.OnLineListener.count;

/**
 * @author guohaodong
 * @create 2018-04-01 18:50
 **/
@Controller
public class OnLineListenerController {

    @GetMapping(path = "/online/count")
    @ResponseBody
    public String onlineCount(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
//        try{  //把sessionId记录在浏览器
//            Cookie c = new Cookie("JSESSIONID", URLEncoder.encode(httpServletRequest.getSession().getId(), "utf-8"));
//            c.setPath("/");
//            //先设置cookie有效期为2天，不用担心，session不会保存2天
//            c.setMaxAge(-1);
//            httpServletResponse.addCookie(c);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

//        HttpSession session = httpServletRequest.getSession();
//        Object count=session.getServletContext().getAttribute("count");



        return "count : "+count;
    }
}
