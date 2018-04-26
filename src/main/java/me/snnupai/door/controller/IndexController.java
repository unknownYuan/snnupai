package me.snnupai.door.controller;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.service.GongGaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    GongGaoService gongGaoService;

    @RequestMapping(path = {"/index", "/"}, method = RequestMethod.GET)
    public String index(Model model){
        String gonggao = gongGaoService.getContent();
        log.info("gonggao=" + gonggao);
        model.addAttribute("gonggao", gonggao);
        return "index";
    }
}
