package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.Post;
import me.snnupai.door.service.ForumService;
import me.snnupai.door.util.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

import static me.snnupai.door.util.Utils.fail;
import static me.snnupai.door.util.Utils.ok;
import static me.snnupai.door.util.Utils.page_size;

@Controller
@Slf4j
public class ForumController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    ForumService forumService;

    @RequestMapping(path = "/forum/post/add", method = RequestMethod.POST)
    @ResponseBody
    public String addForumPost(@RequestParam("content") String content,
                               @RequestParam("title") String title){
//        User user = hostHolder.getUser();
//        if(user == null){
//            return "redirect:/reglogin";
//        }
//        post.setId(user.getId());

        Post post = new Post();
        post.setUserId(3L);
        post.setContent(content);
        post.setCreatedDate(new Date());

        post.setTitle(title);
        post.setStatus(StatusType.create.ordinal());

        try {
            forumService.insertForumPost(post);
            return ok;
        }catch (Exception e){
            return fail;
        }
    }

    @RequestMapping(path = "/forum/post/list", method = RequestMethod.GET)
    @ResponseBody
    public String queryPostList(@RequestParam("pagenum") int pagenum,
                                Model model){
        int offset = (pagenum - 1) * page_size;
        int limit = page_size;
        List<Post> posts = forumService.queryPostList(offset, page_size);
        model.addAttribute("posts", posts);
        return JSONObject.toJSONString(posts);
//        return "forumPostList";
    }




}
