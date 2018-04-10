package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.snnupai.door.model.Data;
import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.Feed;
import me.snnupai.door.pojo.User;
import me.snnupai.door.service.UserService;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import static me.snnupai.door.util.Utils.fail;
import static me.snnupai.door.util.Utils.ok;

@Controller
public class UserController {

    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserService userService;
    @Autowired
    JedisAdapter jedisAdapter;

    @RequestMapping(path = "/snnupai_profile", method = RequestMethod.GET)
    public String queryProfile(Model model) {
        long id = hostHolder.getUser().getId();
        int offset = 1;
        int limit = 10;
        List<Feed> feeds = userService.queryFeed(id, 0, offset * limit);
        System.out.println(JSONObject.toJSONString(feeds));
        model.addAttribute("feeds", feeds);
        return "personal";
    }

    @RequestMapping(path = "/user/edit", method = RequestMethod.POST)
    @ResponseBody
    public String editProfile(@RequestParam("nickname") String nickname,
                              @RequestParam("description") String description,
                              @RequestParam("sex") byte sex,
                              @RequestParam("phone") String phone,
                              @RequestParam("realname") String realname,
                              @RequestParam("major") String major,
                              @RequestParam("entranceyear") int entranceyear) {
        User user = hostHolder.getUser();
        user.setNickName(nickname);
        user.setDescription(description);
        user.setSex(sex);
        user.setPhone(phone);
        user.setRealName(realname);
        user.setMajor(major);
        user.setEntranceYear(entranceyear);
        user.setUpdatedDate(new Date());

        try {
            userService.updateUser(user);
            return ok;
        } catch (Exception e) {
            return fail;
        }
    }

    @ApiOperation(value = "获取自己的动态", notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset", value = "第几页", required = true, dataType = "int"),
            @ApiImplicitParam(name = "limit", value = "每页多少个", required = true, dataType = "int")
    })
    @RequestMapping(path = "/snnupai_user/feed", method = RequestMethod.GET)
    @ResponseBody
    public String queryFeed(@RequestParam("offset") int offset, @RequestParam("limit") int limit,
                            Model model) {
        User user = hostHolder.getUser();
        long localUserId = user.getId();
        List<Feed> feeds = userService.queryFeed(localUserId, offset, limit);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("feeds", feeds);
        return jsonObject.toString();
    }


    @Value("${file.path}")
    String filePath;

    @RequestMapping(path = "/user/uploadHeadImage", method = RequestMethod.POST)
    @ResponseBody
    public String uploadHeadImage(MultipartFile file) {
        String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String fileName = UUID.randomUUID().toString() + extName;
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }


    @RequestMapping(path = "/user/fans/list", method = RequestMethod.GET)
    @ResponseBody
    public String queryFansList(@RequestParam("offset") int offset,
                                @RequestParam("limit") int limit) {
        long localUserId = hostHolder.getUser().getId();
        String key = RedisKeyUtil.getFansKey(localUserId);
        List<String> userIds = jedisAdapter.lrange(key, offset, offset + limit);
        List<User> users = new LinkedList<>();
        for (String sid:
             userIds) {
            long id = Long.parseLong(sid);
            User user = userService.getUserById(id);
            users.add(user);
        }
        return JSONObject.toJSONString(users);
    }


    @RequestMapping(path = "/user/follow/list", method = RequestMethod.GET)
    @ResponseBody
    public String queryFollowees(@RequestParam("offset") int offset,
                                 @RequestParam("limit") int limit){
        long localUserId = hostHolder.getUser().getId();
        String key = RedisKeyUtil.getFollowEntityKey(EntityType.ENTITY_USER, String.valueOf(localUserId));
        List<String> userIds = jedisAdapter.lrange(key, offset, offset + limit);
        List<User> users = new LinkedList<>();
        for (String sid:
             userIds) {
            long id = Long.parseLong(sid);
            User user = userService.getUserById(id);
            users.add(user);
        }
        return JSONObject.toJSONString(users);
    }
}
