package me.snnupai.door.controller;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.pojo.Love;
import me.snnupai.door.service.LoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

import static me.snnupai.door.controller.TradeController.page_size;
import static me.snnupai.door.util.Utils.fail;
import static me.snnupai.door.util.Utils.ok;

@Controller
@Slf4j
public class LoverController {

    @Autowired
    LoveService loveService;

    @RequestMapping(path = "/love/list", method = RequestMethod.GET)
    public String queryLoveList(@RequestParam("pagenum") int pageNum,
                           Model model){
        int offset = (pageNum - 1) * page_size;
        int limit = page_size;
        List<Love> loves = loveService.getLoveList(pageNum, page_size);
        model.addAttribute("loves", loves);
        return "lovelist";
    }

    @RequestMapping(path = "/love/add", method = RequestMethod.POST)
    @ResponseBody
    public String addLove(@RequestParam("userid") Long userId,
                          @RequestParam("anonymous") int anonymous,
                          @RequestParam("content") String content){
        Love love = new Love();
        love.setCreatedDate(new Date());
        love.setUserId(userId);
        love.setContent(content);
        love.setAnonymous(anonymous);
        try {
            loveService.addLove(love);
            return ok;
        }catch (Exception e){
            return fail;
        }
    }
}
