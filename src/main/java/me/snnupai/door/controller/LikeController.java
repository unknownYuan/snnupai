package me.snnupai.door.controller;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.User;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path = "/trade/like", method = RequestMethod.POST)
    @ResponseBody
    public String likeTrade(@RequestParam("userid") Long userId,
                            @RequestParam("tradeid") Long tradeId){
        String key = RedisKeyUtil.getLikeKey( EntityType.ENTITY_TRADE, tradeId);
        return String.valueOf(jedisAdapter.sadd(key, userId));
    }

    @RequestMapping(path = "/trade/dislike", method = RequestMethod.POST)
    @ResponseBody
    public String dislikeTrade(@RequestParam("userid") Long userId,
                            @RequestParam("tradeid") Long tradeId){
        String key = RedisKeyUtil.getDisLikeKey( EntityType.ENTITY_TRADE, tradeId);
        return String.valueOf(jedisAdapter.sadd(key, userId));
    }

    @RequestMapping(path = "/trade/comment/like", method = RequestMethod.POST)
    @ResponseBody
    public String likeComment(@RequestParam("userid") Long userId,
                            @RequestParam("commentid") Long commentId){
        String key = RedisKeyUtil.getLikeKey( EntityType.ENTITY_COMMENT, commentId);
        return String.valueOf(jedisAdapter.sadd(key, userId));
    }

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
