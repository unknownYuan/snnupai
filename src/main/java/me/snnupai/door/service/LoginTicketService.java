package me.snnupai.door.service;

import me.snnupai.door.mapper.LoginTicketMapper;
import me.snnupai.door.pojo.LoginTicket;
import me.snnupai.door.pojo.LoginTicketExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginTicketService {
    @Autowired
    LoginTicketMapper loginTicketMapper;


    public LoginTicket selectByTicket(String ticket) {
        LoginTicketExample loginTicketExample = new LoginTicketExample();
        LoginTicketExample.Criteria criteria = loginTicketExample.createCriteria();
        criteria.andTicketEqualTo(ticket);

        List<LoginTicket> tickets = loginTicketMapper.selectByExample(loginTicketExample);
        if(tickets.size() != 0){
            return tickets.get(0);
        }else{
            return null;
        }
    }
}
