package me.snnupai.door.service;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.PostMapper;
import me.snnupai.door.pojo.Post;
import me.snnupai.door.pojo.PostExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ForumService {

    @Autowired
    PostMapper postMapper;

    public void insertForumPost(Post post) {
        postMapper.insertSelective(post);
    }

    public List<Post> queryPostList(int offset, int limit) {
        PostExample example = new PostExample();

        example.setOffset(offset);
        example.setLimit(limit);
        example.setOrderByClause(" `id` desc ");
        return postMapper.selectByExample(example);
    }
}
