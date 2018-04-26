package me.snnupai.door.service;

import lombok.extern.log4j.Log4j;
import me.snnupai.door.mapper.TradeMapper;
import me.snnupai.door.pojo.Trade;
import me.snnupai.door.pojo.TradeExample;
import me.snnupai.door.status.TradeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.snnupai.door.util.Utils.page_size;

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

    public long totalPages() {
        TradeExample example = new TradeExample();
        TradeExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo(TradeStatus.ready);
        long count =tradeMapper.countByExample(example);
        log.info("count =" + count);
        if(count % page_size == 0){
            return count / page_size;
        }else {
            return count / page_size + 1;
        }
    }
}
