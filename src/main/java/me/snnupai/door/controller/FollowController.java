package me.snnupai.door.controller;

import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.User;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Date;
import java.util.List;

@Controller
public class FollowController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    JedisAdapter jedisAdapter;

    @RequestMapping(value = "/follow", method = RequestMethod.POST)
    @ResponseBody
    public String follow(@RequestParam("followeeid") long followeeid){
        long localUserId = hostHolder.getUser().getId();
        Jedis jedis = jedisAdapter.getJedis();
        Date date = new Date();


        Transaction tx = jedisAdapter.multi(jedis);

        String followeeKey = RedisKeyUtil.getFollowEntityKey(EntityType.ENTITY_USER, String.valueOf(localUserId));
        tx.zadd(followeeKey,date.getTime(), String.valueOf(localUserId));

        String fansKey = RedisKeyUtil.getFansKey(followeeid);
        tx.zadd(fansKey,date.getTime(),  String.valueOf(localUserId));
        List<Object> ret= tx.exec();
        if(ret.size() == 2 && (int)ret.get(0) > 0 && (int)ret.get(1) > 0){
            return "ok";
        }else {
            return "fail";
        }
    }

    @RequestMapping(value = "/unfollow", method = RequestMethod.POST)
    @ResponseBody
    public String unfollow(@RequestParam("followeeid") long followeeid){
        long localUserId = hostHolder.getUser().getId();
        Jedis jedis = jedisAdapter.getJedis();
        Transaction tx = jedisAdapter.multi(jedis);
        String followeeKey = RedisKeyUtil.getFollowEntityKey(EntityType.ENTITY_USER, String.valueOf(localUserId));
        tx.zrem(followeeKey, String.valueOf(followeeid));

        String fansKey = RedisKeyUtil.getFansKey(followeeid);
        tx.zrem(fansKey, String.valueOf(localUserId));
        List<Object> ret = tx.exec();
        if(ret.size() == 2 && (int)ret.get(0) > 0 && (int)ret.get(1) > 0){
            return "ok";
        }else {
            return "fail";
        }
    }
}
