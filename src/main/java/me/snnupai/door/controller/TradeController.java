package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.storage.model.DefaultPutRet;
import lombok.extern.slf4j.Slf4j;
import me.snnupai.door.mapper.ImageMapper;
import me.snnupai.door.model.EntityType;
import me.snnupai.door.model.HostHolder;
import me.snnupai.door.pojo.*;
import me.snnupai.door.service.CommentService;
import me.snnupai.door.service.TradeService;
import me.snnupai.door.status.AnnonymousStatus;
import me.snnupai.door.status.DelStatus;
import me.snnupai.door.status.TradeStatus;
import me.snnupai.door.util.ImageUtils;
import me.snnupai.door.util.JedisAdapter;
import me.snnupai.door.util.RedisKeyUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static me.snnupai.door.util.ImageUtils.fileUpload;
import static me.snnupai.door.util.Utils.*;

@Controller
@Slf4j
public class TradeController {

    @Autowired
    TradeService tradeService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    JedisAdapter jedisAdapter;

    @RequestMapping(path = "/trade_ftl", method = RequestMethod.GET)
    public String getTradeFtl(Model model, @RequestParam("pagenum") int pagenum) {
        User user = hostHolder.getUser();
        if (user == null) {
            return "redirect:/reglogin";
        }

        List<Map<String, Object> > initTrades = new LinkedList<>();
        List<Trade> trades = tradeService.getTradeList((pagenum - 1) * page_size, page_size);
        if(trades != null) {
            for (Trade trade: trades) {
                Map<String , Object> map = new HashMap<>();
                map.put("id", trade.getId());
                map.put("title", trade.getTitle());
                map.put("content", trade.getContent());
                map.put("createdDate", trade.getCreatedDate());
                boolean ret = jedisAdapter.sismember(RedisKeyUtil.getCollectionKey(EntityType.ENTITY_TRADE, user.getId()), trade.getId());
                map.put("follow", ret == true ? "follow" : "unfollow");
                String id = trade.getId();
                Image image = getImage(id);
                log.info(JSONObject.toJSONString(image));
                map.put("imageUrl", image.getUrl());
                initTrades.add(map);
            }
        }
        model.addAttribute("trades", initTrades);
        model.addAttribute("totalPages", tradeService.totalPages());
        model.addAttribute("currentNum", pagenum);
		

        return "flea_market";
    }

    private Image getImage(String id) {
        ImageExample example = new ImageExample();
        ImageExample.Criteria criteria = example.createCriteria();
        criteria.andEntityIdEqualTo(id);
        criteria.andEntityTypeEqualTo(EntityType.ENTITY_TRADE);

        List<Image> images = imageMapper.selectByExample(example);
        if(images != null && images.size() != 0) {
            return images.get(0);
        }else{
            return null;
        }
    }


    private List<Image> getImageList(String id) {
        ImageExample example = new ImageExample();
        ImageExample.Criteria criteria = example.createCriteria();
        criteria.andEntityIdEqualTo(id);
        criteria.andEntityTypeEqualTo(EntityType.ENTITY_TRADE);

        List<Image> images = imageMapper.selectByExample(example);
        return images;
    }




    @Autowired
    CommentService commentService;

    @RequestMapping(path = "/trade", method = RequestMethod.GET)
    public String queryTrade(@RequestParam("id") String id, Model model) {
        User user = hostHolder.getUser();
        if(user == null){
            return "redirect:/reglogin";
        }else {
            Trade trade = tradeService.getTradeById(id);
            List<Image> images = getImageList(id);
            int annony =trade.getAnonymous();
            model.addAttribute("annonymous", annony);
            if(annony == AnnonymousStatus.annoy){
                model.addAttribute("nickname", "张翠山");
            }else{
                model.addAttribute("nickname",  user.getNickName());
            }

            model.addAttribute("user", user);
            model.addAttribute("trade", trade);
            model.addAttribute("images", images);

            boolean ret = jedisAdapter.sismember(RedisKeyUtil.getCollectionKey(EntityType.ENTITY_TRADE, user.getId()), trade.getId());
            String followStatus = ret == true ? "follow" : "unfollow";
            //添加上架下架物品
            model.addAttribute("status", followStatus);
            Map<String , Object> comments = new HashMap<>();
            List<Comment> comments1 = commentService.queryAllCommentsByEntityId(EntityType.ENTITY_TRADE, trade.getId());
            model.addAttribute("comments", comments);
            return "flea_market_product";
        }
    }

    @Value("${file.path}")
    String filePath;

    @RequestMapping(path = "/trade/uploadHeadImage", method = RequestMethod.POST)
    @ResponseBody
    public String uploadHeadImage(MultipartFile file) {
        String extName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String fileName = UUID.randomUUID().toString() + extName;
        try {
            FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));
            return "ok";
        } catch (Exception e) {
            return "fail";
        }
    }


    @Autowired
    ImageMapper imageMapper;

    @Value("${qiniu.domain}")
    String qiNiuDomain;
   @RequestMapping(path = "/trade/add", method = RequestMethod.POST)
    @ResponseBody
    public String addTrade(@RequestParam("title") String title,
                           @RequestParam("content") String content,
                           @RequestParam("anonymous") int anonymous,
                           @RequestParam(value = "qq", required = false) String qq,
                           @RequestParam(value = "weixin", required = false) String weixin,
                           @RequestParam(value = "price", required = false) int price,
                           @RequestParam(value = "files") MultipartFile[] files) {


        User user = hostHolder.getUser();
        if (user == null) {
            return "redirect:/reglogin";
        }
        //分布式id
        String tradeId = UUID.randomUUID().toString().replaceAll("-", "");

        Trade trade = new Trade();
        trade.setId(tradeId);
        trade.setTitle(title);
        trade.setAnonymous(anonymous);
        trade.setContent(content);
        trade.setCreatedDate(new Date());
        trade.setUpdatedDate(new Date());
        trade.setOwnerId(user.getId());
        trade.setQq(qq);
        trade.setWeixin(weixin);
        trade.setPrice(price);
        trade.setDelFlag(DelStatus.non_del);
        trade.setStatus(TradeStatus.ready);
        try {

            tradeService.addTrade(trade);

            if(files!=null && files.length>=1) {
                for (MultipartFile file : files) {
                    BufferedOutputStream bw = null;
                    try {
                        String fileName = file.getOriginalFilename();
                        //判断是否有文件且是否为图片文件
                        if(fileName!=null && !"".equalsIgnoreCase(fileName.trim()) && isImageFile(fileName)) {

                            String fileUrl = filePath + "/" + UUID.randomUUID().toString()+ getFileType(fileName);
                            //创建输出文件对象
                            File outFile = new File(fileUrl);
                            //拷贝文件到输出文件对象
                            FileUtils.copyInputStreamToFile(file.getInputStream(), outFile);

                            DefaultPutRet ret = fileUpload(Zone.zone0(), ImageUtils.getUploadCredential(),fileUrl);

                            String imageUrl = qiNiuDomain + "/" + ret.hash;

                            Image image = new Image();
                            image.setEntityType(EntityType.ENTITY_TRADE);
                            image.setEntityId(tradeId);
                            image.setUrl(imageUrl);
                            image.setCreatedDate(new Date());
                            image.setDelStatus(0);
                            image.setCreatedBy(user.getId());
                            image.setUpdatedBy(user.getId());
                            image.setUpdatedDate(new Date());
                            imageMapper.insertSelective(image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if(bw!=null) {bw.close();}
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return ok;
        } catch (Exception e) {
            e.printStackTrace();
            return fail;
        }
    }

    /**
     * 判断文件是否为图片文件
     * @param fileName
     * @return
     */
    private Boolean isImageFile(String fileName) {
        String [] img_type = new String[]{".jpg", ".jpeg", ".png", ".gif", ".bmp"};
        if(fileName==null) {return false;}
        fileName = fileName.toLowerCase();
        for(String type : img_type) {
            if(fileName.endsWith(type)) {return true;}
        }
        return false;
    }

    /**
     * 获取文件后缀名
     * @param fileName
     * @return
     */
    private String getFileType(String fileName) {
        if(fileName!=null && fileName.indexOf(".")>=0) {
            return fileName.substring(fileName.lastIndexOf("."), fileName.length());
        }
        return "";
    }
}
