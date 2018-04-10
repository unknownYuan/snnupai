package me.snnupai.door.async;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.log4j.Log4j;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class EventProducer {
    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel) {
        try {
            String json = JSONObject.toJSONString(eventModel);
            String key = RedisKeyUtil.getEventQueueKey();
            jedisAdapter.lpush(key, json);
            log.info("lpush " +  key + " " + json);
            return true;
        } catch (Exception e) {
            log.error("持久化数据出错...");
            return false;
        }
    }
}
