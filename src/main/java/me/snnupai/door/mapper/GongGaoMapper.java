package me.snnupai.door.mapper;

import java.util.List;
import me.snnupai.door.pojo.GongGao;
import me.snnupai.door.pojo.GongGaoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface GongGaoMapper {
    long countByExample(GongGaoExample example);

    int deleteByExample(GongGaoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GongGao record);

    int insertSelective(GongGao record);

    List<GongGao> selectByExampleWithBLOBs(GongGaoExample example);

    List<GongGao> selectByExample(GongGaoExample example);

    GongGao selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GongGao record, @Param("example") GongGaoExample example);

    int updateByExampleWithBLOBs(@Param("record") GongGao record, @Param("example") GongGaoExample example);

    int updateByExample(@Param("record") GongGao record, @Param("example") GongGaoExample example);

    int updateByPrimaryKeySelective(GongGao record);

    int updateByPrimaryKeyWithBLOBs(GongGao record);

    int updateByPrimaryKey(GongGao record);
}