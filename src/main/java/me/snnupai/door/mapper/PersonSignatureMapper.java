package me.snnupai.door.mapper;

import java.util.List;
import me.snnupai.door.pojo.PersonSignature;
import me.snnupai.door.pojo.PersonSignatureExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PersonSignatureMapper {
    long countByExample(PersonSignatureExample example);

    int deleteByExample(PersonSignatureExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PersonSignature record);

    int insertSelective(PersonSignature record);

    List<PersonSignature> selectByExampleWithBLOBs(PersonSignatureExample example);

    List<PersonSignature> selectByExample(PersonSignatureExample example);

    PersonSignature selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PersonSignature record, @Param("example") PersonSignatureExample example);

    int updateByExampleWithBLOBs(@Param("record") PersonSignature record, @Param("example") PersonSignatureExample example);

    int updateByExample(@Param("record") PersonSignature record, @Param("example") PersonSignatureExample example);

    int updateByPrimaryKeySelective(PersonSignature record);

    int updateByPrimaryKeyWithBLOBs(PersonSignature record);
}