package me.snnupai.door.service;

import me.snnupai.door.mapper.GongGaoMapper;
import me.snnupai.door.pojo.GongGao;
import me.snnupai.door.pojo.GongGaoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author guohaodong
 * @create 2018-04-11 22:12
 **/
@Service
public class GongGaoService {

    @Autowired
    GongGaoMapper gongGaoMapper;

    public String getContent() {
        GongGaoExample example = new GongGaoExample();
        example.setOrderByClause(" created_date desc ");
        List<GongGao> gongGaos = gongGaoMapper.selectByExample(example);

        List<GongGao> result = new ArrayList<>();
        for (GongGao gongGao:
             gongGaos) {
            Long id = gongGao.getId();
            GongGao gg = gongGaoMapper.selectByPrimaryKey(id);
            result.add(gg);
        }
        if(gongGaos.size() == 0){
            return "";
        }else {
            return result.get(0).getContent();
        }
    }

    public void addGongGao(String content) {
        GongGao gongGao = new GongGao();
        gongGao.setContent(content);
        gongGao.setCreatedDate(new Date());
        gongGaoMapper.insert(gongGao);
    }
}
