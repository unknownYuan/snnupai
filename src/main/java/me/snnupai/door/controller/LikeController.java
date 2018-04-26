package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.User;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 点赞
 */
@Controller
@Slf4j
public class LikeController {

    @Autowired
    JedisAdapter jedisAdapter;
    @Autowired
    HostHolder hostHolder;


    @RequestMapping(path = "/trade/follow", method = RequestMethod.POST)
    @ResponseBody
    public String followTrade(@RequestParam("id") String tradeId){

        User user = hostHolder.getUser();
        if(user == null ){
            return "redirect:/reglogin";
        }else{
            String key = RedisKeyUtil.getCollectionKey(EntityType.ENTITY_TRADE, user.getId());
            long ret = -1;
            if(jedisAdapter.sismember(key, tradeId)){
                ret = jedisAdapter.srem(key, tradeId);
            }else{
                ret = jedisAdapter.sadd(key, tradeId);
            }
            boolean status = jedisAdapter.sismember(key, tradeId);
            return status == true ? "1" : "2";
        }
    }


    @PostMapping(path = "/trade/comment/like/status")
    @ResponseBody
    public String getStatus(@RequestParam("commentId") Long commentId){
        User user = hostHolder.getUser();
        if(user == null){
            return "fail";
        }
        String key =RedisKeyUtil.getLikeKey(EntityType.ENTITY_COMMENT, commentId);
        if(jedisAdapter.sismember(key, String.valueOf(user.getId()))){
            return "true" ;
        }else {
            return "false";
        }
    }


    @PostMapping(path = "/trade/comment/like")
    @ResponseBody
    public String likeTradeComment(@RequestParam("commentId") Long commentId){
        User user = hostHolder.getUser();
        if(user == null){
            return "fail";
        }
        String key =RedisKeyUtil.getLikeKey(EntityType.ENTITY_COMMENT, commentId);

        if (jedisAdapter.sismember(key, String.valueOf(user.getId()))) {
            jedisAdapter.srem(key, String.valueOf(user.getId()));
        } else {
           jedisAdapter.sadd(key, user.getId());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("isLike", jedisAdapter.sismember(key, String.valueOf(user.getId())) ? "true" :"false" );
        result.put("num", jedisAdapter.scard(key));
        result.put("status", "ok");


        JSONObject jsonObject = new JSONObject();
        List<Map<String, Object>> info = new ArrayList<>();
        info.add(result);
        jsonObject.put("info", info);

        return jsonObject.toString();
    }

    @RequestMapping(path = "/trade/like", method = RequestMethod.POST)
    @ResponseBody
    public String likeTrade(@RequestParam("tradeid") Long tradeId){
        User user = hostHolder.getUser();
        if(user == null){
            return "fail";
        }

        String key = RedisKeyUtil.getLikeKey( EntityType.ENTITY_TRADE, tradeId);
        return String.valueOf(jedisAdapter.sadd(key, user.getId()));
    }

    @RequestMapping(path = "/trade/dislike", method = RequestMethod.POST)
    @ResponseBody
    public String dislikeTrade(@RequestParam("userid") Long userId,
                            @RequestParam("tradeid") Long tradeId){
        String key = RedisKeyUtil.getDisLikeKey( EntityType.ENTITY_TRADE, tradeId);
        return String.valueOf(jedisAdapter.sadd(key, userId));
    }

//    @RequestMapping(path = "/trade/comment/like", method = RequestMethod.POST)
//    @ResponseBody
//    public String likeComment(@RequestParam("userid") Long userId,
//                            @RequestParam("commentid") Long commentId){
//        String key = RedisKeyUtil.getLikeKey( EntityType.ENTITY_COMMENT, commentId);
//        return String.valueOf(jedisAdapter.sadd(key, userId));
//    }

    @RequestMapping(path = "/trade/comment/dislike", method = RequestMethod.POST)
    @ResponseBody
    public String dislikeComment(@RequestParam("userid") Long userId,
                              @RequestParam("commentid") Long commentId){
        String key = RedisKeyUtil.getDisLikeKey( EntityType.ENTITY_COMMENT, commentId);
        return String.valueOf(jedisAdapter.sadd(key, userId));
    }


    @RequestMapping(path = "/forum/post/like", method = RequestMethod.POST)
    @ResponseBody
    public String likePost(@RequestParam("postid") Long postId){
        User user = hostHolder.getUser();
        String key = RedisKeyUtil.getLikeKey(EntityType.ENTITY_FORUM_POST, postId);
        return String.valueOf(jedisAdapter.sadd(key, user.getId()));
    }

    @RequestMapping(path = "/forum/post/dislike", method = RequestMethod.POST)
    @ResponseBody
    public String disLikePost(@RequestParam("postid") Long postId){
        User user = hostHolder.getUser();
        String key = RedisKeyUtil.getDisLikeKey(EntityType.ENTITY_FORUM_POST, postId);
        return String.valueOf(jedisAdapter.sadd(key, user.getId()));
    }

}
