package me.snnupai.door.async.handler;

import me.snnupai.door.async.EventHandler;
import me.snnupai.door.async.EventModel;
import me.snnupai.door.async.EventType;
import me.snnupai.door.model.MailMessage;
import me.snnupai.door.service.MailService;
import me.snnupai.door.util.JedisAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class VerifyMailHandler implements EventHandler {

    @Autowired
    MailService mailService;

    @Autowired
    JedisAdapter jedisAdapter;

    @Override
    public void doHandle(EventModel model) {
        Map<String, String> map = model.getExts();
        String nickname = map.get("nickname");
        String email = map.get("email");
        MailMessage mailMessage = new MailMessage();
        String token = UUID.randomUUID().toString().replaceAll("-", "");

        jedisAdapter.sadd(token, "1");
        jedisAdapter.expire(token, 60 * 5);
        mailMessage.setToken(token);
        mailMessage.setNickname(nickname);
        mailService.sendMessageMail(mailMessage, "注册确认邮件", "verifymail.ftl", email);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.REGISTER);
    }
}
