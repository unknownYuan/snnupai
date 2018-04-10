package me.snnupai.door.service;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.MessageMapper;
import me.snnupai.door.pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public List<Message> queryConversationList(Long id) {
        //return messageDAO.queryConversationList(localUserId);
        // to do
        return null;
    }

    public void addMsg(Message message) {
        messageMapper.insertSelective(message);
    }
}
