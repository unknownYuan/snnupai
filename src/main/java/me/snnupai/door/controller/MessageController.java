package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.Message;
import me.snnupai.door.service.MessageService;
import me.snnupai.door.status.ReadStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@Controller
@Slf4j
public class MessageController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    MessageService messageService;

    @RequestMapping(value = "/msg/list", method = {RequestMethod.GET})
    @ResponseBody
    public String queryConversationList(Model model){
        if(hostHolder.getUser() == null){
            return "redirect:/reglogin";
        }
        Long localUserId = hostHolder.getUser().getId();
        List<Message> conversations = messageService.queryConversationList(localUserId);
        //to do
        model.addAttribute("conversations", conversations);
        //return "msglist";
        return JSONObject.toJSONString(conversations);
    }

    @RequestMapping(path = "/msg/addMsg", method = {RequestMethod.POST})
    public String addMsg(@RequestParam("toId") Long toId,
                         @RequestParam("content") String content){
        if(hostHolder.getUser() == null){
            return "redirect:/reglogin";
        }
        Long localUserId = hostHolder.getUser().getId();
        Long fromId = localUserId;
        Message message = new Message();
        message.setCreatedDate(new Date());
        message.setFromId(localUserId);
        message.setToId(toId);
        message.setContent(content);
        String conversationId = "";
        if(fromId < toId){
            conversationId = fromId + "_" + toId;
        }else {
            conversationId = toId + "_" + fromId;
        }
        message.setConversationId(conversationId);
        message.setHasRead(ReadStatus.has_no_read);
        try{
            messageService.addMsg(message);
            return "ok";
        }catch(Exception e){
            return "fail";
        }
    }
}
