package me.snnupai.door.service;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.LoveMapper;
import me.snnupai.door.pojo.Love;
import me.snnupai.door.pojo.LoveExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoveService {

    @Autowired
    LoveMapper loveMapper;

    public List<Love> getLoveList(int offset, int limit) {
        LoveExample example = new LoveExample();
        example.setOffset((offset - 1) * limit);
        example.setLimit(limit);
        example.setOrderByClause(" `id`  desc ");

        return loveMapper.selectByExampleWithBLOBs(example);
    }

    public void addLove(Love love) {
        loveMapper.insertSelective(love);
    }
}
