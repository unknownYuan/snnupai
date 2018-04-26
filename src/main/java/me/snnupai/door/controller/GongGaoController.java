package me.snnupai.door.controller;

import me.snnupai.door.service.GongGaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author guohaodong
 * @create 2018-04-11 22:10
 **/
@Controller
public class GongGaoController {

    @Autowired
    GongGaoService gongGaoService;

    @PostMapping(path = "/back/gonggao/add")
    public String addGongGao(@RequestParam("content") String content){
        gongGaoService.addGongGao(content);
        return "redirect:/index";
    }

    @GetMapping(path = "/back/gonggao")
    public String gongGaoBack(){
        return "announcement";
    }

}
