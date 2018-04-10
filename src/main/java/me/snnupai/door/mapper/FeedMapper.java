package me.snnupai.door.mapper;

import java.util.List;
import me.snnupai.door.pojo.Feed;
import me.snnupai.door.pojo.FeedExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FeedMapper {
    long countByExample(FeedExample example);

    int deleteByExample(FeedExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Feed record);

    int insertSelective(Feed record);

    List<Feed> selectByExample(FeedExample example);

    Feed selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Feed record, @Param("example") FeedExample example);

    int updateByExample(@Param("record") Feed record, @Param("example") FeedExample example);

    int updateByPrimaryKeySelective(Feed record);

    int updateByPrimaryKey(Feed record);
}