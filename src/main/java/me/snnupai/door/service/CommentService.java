package me.snnupai.door.service;

import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.CommentMapper;
import me.snnupai.door.pojo.Comment;
import me.snnupai.door.pojo.CommentExample;
import me.snnupai.door.status.CommentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static me.snnupai.door.util.Utils.fail;
import static me.snnupai.door.util.Utils.ok;

@Service
@Slf4j
public class CommentService {

    @Autowired
    CommentMapper commentMapper;

    public String addComment(Comment comment) {
        int ret = commentMapper.insert(comment);
        if(ret < 0){
            log.error("评论插入失败！" + comment.toString());
            return fail;
        }else{
            return ok;
        }
    }

    public List<Comment> queryById(int type, String id) {
        CommentExample example = new CommentExample();
        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andEntityTypeEqualTo(type);
        criteria.andEntityIdEqualTo(id);
        return commentMapper.selectByExample(example);
    }

    public List<Comment> queryAllCommentsByEntityId(int type, String id) {
        return null;
    }

    public List<Comment> queryAllCommentsByEntityId(int banKuaiType, String id, int offset, int limit) {
        CommentExample example = new CommentExample();

        example.setOffset(offset);
        example.setLimit(limit);
        example.setOrderByClause(" `created_date` asc ");

        CommentExample.Criteria criteria = example.createCriteria();
        criteria.andBanKuaiTypeEqualTo(banKuaiType)
                .andPostIdEqualTo(id)
                .andStatusEqualTo(CommentStatus.normal);

        return commentMapper.selectByExampleWithBLOBs(example);
    }

    public Comment queryOneById(long id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
