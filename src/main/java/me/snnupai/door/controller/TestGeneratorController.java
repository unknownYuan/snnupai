package me.snnupai.door.controller;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.FeedMapper;
import me.snnupai.door.mapper.UserMapper;
import me.snnupai.door.pojo.Feed;
import me.snnupai.door.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class TestGeneratorController {

    @Autowired
    UserMapper userMapper;

    @RequestMapping(path = "/testgene")
    @ResponseBody
    public String testGene(){
        User user = userMapper.selectByPrimaryKey(1004L);
        log.info(user.toString());
        return "ok";
    }

    @Autowired
    FeedMapper feedMapper;

    @RequestMapping(path = "/genefeed")
    @ResponseBody
    public String geneFeeds(){
        int id = 1201;
        for (int i = 0; i < 100; i++) {
            Feed feed = new Feed();
            feed.setData("明月即使有");
            feed.setEventType(2);
            feed.setEventId(1L);
            feed.setUserId(1201L);
            int ret = feedMapper.insert(feed);
            System.out.println(ret);
        }
        return "ok";
    }
}
