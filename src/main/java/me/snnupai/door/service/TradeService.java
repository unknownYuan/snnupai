package me.snnupai.door.service;

import lombok.extern.log4j.Log4j;
import me.snnupai.door.mapper.TradeMapper;
import me.snnupai.door.pojo.Trade;
import me.snnupai.door.pojo.TradeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class TradeService {

    @Autowired
    TradeMapper tradeMapper;

    public List<Trade> getTradeList(int offset, int limit) {
        TradeExample example = new TradeExample();
        example.setOffset(offset);
        example.setLimit(limit);
        example.setOrderByClause(" created_date desc ");

        return tradeMapper.selectByExample(example);
    }

    public Trade getTradeById(String id) {
        return tradeMapper.selectByPrimaryKey(id);
    }

    public int addTrade(Trade trade) {
        return tradeMapper.insertSelective(trade);
    }
}
