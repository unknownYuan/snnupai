package me.snnupai.door.mapper;

import java.util.List;
import me.snnupai.door.pojo.LoginTicket;
import me.snnupai.door.pojo.LoginTicketExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginTicketMapper {
    long countByExample(LoginTicketExample example);

    int deleteByExample(LoginTicketExample example);

    int deleteByPrimaryKey(Long id);

    int insert(LoginTicket record);

    int insertSelective(LoginTicket record);

    List<LoginTicket> selectByExample(LoginTicketExample example);

    LoginTicket selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") LoginTicket record, @Param("example") LoginTicketExample example);

    int updateByExample(@Param("record") LoginTicket record, @Param("example") LoginTicketExample example);

    int updateByPrimaryKeySelective(LoginTicket record);

    int updateByPrimaryKey(LoginTicket record);
}