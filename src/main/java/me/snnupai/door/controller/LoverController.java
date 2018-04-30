package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.UserMapper;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.Love;
import me.snnupai.door.pojo.User;
import me.snnupai.door.service.LoveService;
import me.snnupai.door.service.UserService;
import me.snnupai.door.status.AnnonymousStatus;
import me.snnupai.door.status.LoveStatus;
import me.snnupai.door.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

import static me.snnupai.door.util.Utils.fail;
import static me.snnupai.door.util.Utils.ok;

@Controller
@Slf4j
public class LoverController {

    @Autowired
    LoveService loveService;
    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;


    @RequestMapping(path = "/love_list", method = RequestMethod.GET)
    public String queryLoveList(@RequestParam("pagenum") int pageNum,
                                Model model) {
        List<Map<String, Object>> loveInfos = getLoveInfos(1, 10);
        model.addAttribute("loveInfos", loveInfos);
        return "love_wall";
    }

    @ApiOperation(value="获取表白墙列表", notes="用户未登录时可以查看列表，但是无法评论和点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name="offset", value = "第几页，从第一页开始"),
            @ApiImplicitParam(name = "limit", value = "每页多少条信息")
    })
    @RequestMapping(path = "/love_list_ajax", method = RequestMethod.GET)
    @ResponseBody
    public String queryLoveListByAjax(@RequestParam("offset") int offset,
                                      @RequestParam("limit") int limit){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("loveInfos", getLoveInfos(offset, limit));
        log.info("jsonToString = " + jsonObject.toString());
        return jsonObject.toString();
    }

    public List<Map<String, Object>> getLoveInfos(int offset, int limit){
        List<Love> loves = loveService.getLoveList(offset, limit);
        List<Map<String, Object>> loveInfos = new ArrayList<>();
        for (Love love :
                loves) {
            Map<String, Object> info = new LinkedHashMap<String, Object>();
            info.put("content", love.getContent());
            info.put("time", DateUtils.getDateString(new Date()));

            int anonymous = love.getAnonymous();
            if (anonymous == AnnonymousStatus.annoy) {
                info.put("nickname", "林冲");
                info.put("anonymous", "匿名发布");
            } else {
                Long userId = love.getUserId();
                User user = userService.getUserById(userId);
                info.put("anonymous", "实名发布");
                info.put("nickname", user.getNickName());
            }
            loveInfos.add(info);
        }
        return loveInfos;
    }


    @ApiOperation(value = "发布表白信息", notes = "发布表白信息接口，必须登录才能发布")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "anonymous", value = "是否匿名发布，1表示匿名，0表示不匿名"),
            @ApiImplicitParam(name = "content", value = "表白内容")
    })
    @RequestMapping(path = "/love/add", method = RequestMethod.POST)
    @ResponseBody
    public String addLove(
            @RequestParam("anonymous") int anonymous,
            @RequestParam("content") String content) {
        log.info("content" + content);
        User user = hostHolder.getUser();
        Love love = new Love();
        love.setCreatedDate(new Date());
        love.setUpdatedDate(new Date());
        love.setStatus(LoveStatus.normal);
        love.setUserId(user.getId());
        love.setContent(content);
        love.setAnonymous(anonymous);
        try {
            loveService.addLove(love);
            return ok;
        } catch (Exception e) {
            return fail;
        }
    }
}
