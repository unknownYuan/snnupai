package me.snnupai.door.mapper;

import java.util.List;
import me.snnupai.door.pojo.Love;
import me.snnupai.door.pojo.LoveExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface LoveMapper {
    long countByExample(LoveExample example);

    int deleteByExample(LoveExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Love record);

    int insertSelective(Love record);

    List<Love> selectByExampleWithBLOBs(LoveExample example);

    List<Love> selectByExample(LoveExample example);

    Love selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByExampleWithBLOBs(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByExample(@Param("record") Love record, @Param("example") LoveExample example);

    int updateByPrimaryKeySelective(Love record);

    int updateByPrimaryKeyWithBLOBs(Love record);

    int updateByPrimaryKey(Love record);
}