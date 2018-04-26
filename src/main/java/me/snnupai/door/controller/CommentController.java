package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j;
import me.snnupai.door.mapper.UserMapper;
import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.Comment;
import me.snnupai.door.pojo.User;
import me.snnupai.door.service.CommentService;
import me.snnupai.door.service.TradeService;
import me.snnupai.door.status.BanKuaiType;
import me.snnupai.door.status.CommentStatus;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Log4j
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    HostHolder hostHolder;
    @Autowired
    TradeService tradeService;

    @Autowired
    JedisAdapter jedisAdapter;

    @Autowired
    UserMapper userMapper;

    @RequestMapping(path = "/comment/list", method = RequestMethod.GET)
    @ResponseBody
    public String getTradeCommentList(HttpServletRequest request) {
        log.info("comment list page....");
        String postId = request.getParameter("postId");
        int offset = Integer.parseInt(request.getParameter("offset"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int banKuaiType = Integer.parseInt(request.getParameter("banKuaiType"));

        List<Map<String, Object>> commentInfos = new LinkedList<>();
        List<Comment> comments = commentService.queryAllCommentsByEntityId(banKuaiType, postId, offset, limit);

        for (Comment comment :
                comments) {
            int entityType = comment.getEntityType();
            Long ownerId = comment.getCreatedBy();
            String nickname = userMapper.selectByPrimaryKey(ownerId).getNickName();

            String toName = "";
            if (entityType == EntityType.ENTITY_TRADE) {
                toName = "";
            } else if (entityType == EntityType.ENTITY_COMMENT) {
                String commentId = comment.getEntityId();
                Comment toComment = commentService.queryOneById(Long.parseLong(commentId));
                Long toId = toComment.getCreatedBy();
                toName = userMapper.selectByPrimaryKey(toId).getNickName();
            }
            String content = comment.getContent();
            Date time = comment.getCreatedDate();
            Map<String, Object> info = new HashMap<>();
            info.put("nickname", nickname);
            info.put("toName", toName);
            info.put("entityType", entityType);
            info.put("content", content);
            info.put("id", comment.getId());
            info.put("num", jedisAdapter.scard(RedisKeyUtil.getLikeKey(EntityType.ENTITY_COMMENT, comment.getId())));
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:MM");

            info.put("time", bartDateFormat.format(time));
            commentInfos.add(info);
        }
        log.info(JSONObject.toJSONString(commentInfos));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commentInfos", commentInfos);
        return jsonObject.toString();
    }

    @RequestMapping(path = "/trade_comment_reply", method = RequestMethod.POST)
    @ResponseBody
    public String replyTradeComment(HttpServletRequest request){
        User user = hostHolder.getUser();
        if (user == null) {
            return "redict:/reglogin";
        }

        String content = request.getParameter("content");
        String commentId = request.getParameter("entityId");
        log.info("commentId" + commentId);
        int anonymous = Integer.parseInt(request.getParameter("anonymous"));


        Comment toComment = commentService.queryOneById(Long.parseLong(commentId));

        Comment comment = new Comment();
        comment.setCreatedBy(user.getId());
        comment.setUpdateBy(user.getId());
        comment.setCreatedDate(new Date());
        comment.setUpdatedDate(new Date());
        comment.setEntityType(EntityType.ENTITY_COMMENT);
        comment.setEntityId(commentId);
        comment.setAnonymous(anonymous);
        comment.setBanKuaiType(BanKuaiType.trade);
        comment.setContent(content);
        comment.setPostId(toComment.getPostId());
        comment.setStatus(CommentStatus.normal);
        try {
            commentService.addComment(comment);
            return "ok";
        }catch (Exception e){
            log.error(e.getMessage());
            return "fail";
        }
    }

    @RequestMapping(path = "/trade/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public String addTradeComment(HttpServletRequest request) {
        User user = hostHolder.getUser();

        if (user == null) {
            return "redict:/reglogin";
        } else {

            String content = request.getParameter("content");
            int entityType = Integer.parseInt(request.getParameter("entityType")); //1代表评论帖子，2代表评论评论
            int banKuaiType = Integer.parseInt(request.getParameter("banKuaiType"));
            String entityId = request.getParameter("entityId");
            int anonymous = Integer.parseInt(request.getParameter("anonymous"));
            String postId = request.getParameter("postId");

            Map<String, String[]> map = request.getParameterMap();
            for (String key :
                    map.keySet()) {
                if (map.get(key) != null) {
                    System.out.println(map.get(key)[0]);
                } else {
                    System.out.println("null");
                }
            }

            Comment comment = new Comment();
            comment.setPostId(postId);
            comment.setCreatedBy(user.getId());
            comment.setUpdateBy(user.getId());
            comment.setEntityType(entityType);
            comment.setEntityId(entityId);
            comment.setContent(content);
            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());
            comment.setStatus(CommentStatus.normal);
            comment.setEntityId(entityId);
            comment.setAnonymous(anonymous);
            comment.setBanKuaiType(banKuaiType);
            try {
                commentService.addComment(comment);
                return "ok";
            } catch (Exception e) {
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
        comment.setCreatedBy(user.getId());
        comment.setContent(content);
        comment.setCreatedDate(new Date());
        comment.setEntityId(entityId);
        comment.setEntityType(entityType);
        return commentService.addComment(comment);
    }
}
