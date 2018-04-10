package me.snnupai.door.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * @author guohaodong
 * @create 2018-04-01 19:37
 **/
@ControllerAdvice
public class MyControllerAdvice {
    @ModelAttribute
    public void addAttributes(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession();
    }

}
