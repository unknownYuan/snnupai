package me.snnupai.door.controller;

import lombok.extern.log4j.Log4j;
import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.Comment;
import me.snnupai.door.pojo.User;
import me.snnupai.door.service.CommentService;
import me.snnupai.door.service.TradeService;
import me.snnupai.door.status.CommentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@Log4j
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;
    @Autowired
    TradeService tradeService;



    @RequestMapping(path = "/trade/comment/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> getTradeCommentList(@RequestParam("id") String tradeId,
                                             @RequestParam("offset") int offset,
                                             @RequestParam("limit") int limit){
        return commentService.queryById(EntityType.ENTITY_TRADE, tradeId);
    }

    @RequestMapping(path = "/trade_comment_add", method = RequestMethod.POST)
    @ResponseBody
    public String addTradeComment(@RequestParam("content") String content,
                                  @RequestParam("type") int type,
                                  @RequestParam("id") String entityId,
                                  @RequestParam("annonymous") int annonymous) {
        User user = hostHolder.getUser();
        if(user == null){
            return "redict:/reglogin";
        }else{
            Comment comment = new Comment();
            comment.setContent(content);
            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
            comment.setStatus(CommentStatus.normal);
            comment.setUserId(user.getId());
            comment.setEntityId(entityId);
            comment.setEntityType(EntityType.ENTITY_TRADE);
            comment.setAnnonymous(annonymous);
            try {
                commentService.addComment(comment);
                return "ok";
            }catch (Exception e){
                log.error(e.getMessage());
                return "fail";
            }
        }
    }

    @RequestMapping(path = "/forum/post/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public String addPostComment(@RequestParam("content") String content,
                                 @RequestParam("entityid") String entityId) {
        return addComment(content, entityId, EntityType.ENTITY_FORUM_POST);
    }

    @RequestMapping(path = "/forum/postcomment/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCommentOfPostComment(@RequestParam("content") String content,
                                          @RequestParam("entityid") String entityId) {
        return addComment(content, entityId, EntityType.ENTITY_COMMENT);
    }


    private String addComment(String content, String entityId, int entityType) {
        User user = hostHolder.getUser();
        Comment comment = new Comment();
        comment.setUserId(user.getId());
        comment.setContent(content);
        comment.setCreatedDate(new Date());
        comment.setEntityId(entityId);
        comment.setEntityType(entityType);
        return commentService.addComment(comment);
    }
}
