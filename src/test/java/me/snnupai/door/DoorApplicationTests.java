package me.snnupai.door;


import me.snnupai.door.mapper.FeedMapper;
import me.snnupai.door.mapper.MessageMapper;
import me.snnupai.door.mapper.PostMapper;
import me.snnupai.door.pojo.*;
import me.snnupai.door.service.LoveService;
import me.snnupai.door.service.TradeService;
import me.snnupai.door.service.UserService;
import me.snnupai.door.status.ReadStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Sql("/snnupai.sql")
public class DoorApplicationTests {

	@Autowired
	TradeService tradeService;
	@Test
	public void loadTrades() {

		int max = 30;
		for (int i = 0; i < max; i++) {
			Trade trade = new Trade();
			trade.setAnonymous(1);
			trade.setContacter("郭浩东");
			trade.setContent("有一辆二手自行车");
			trade.setPrice(100);
			trade.setCreatedDate(new Date());
			trade.setOwnerId(23L);
			trade.setQq("1593028064");
			trade.setWeixin("weixin520");
			int ret = tradeService.addTrade(trade);
			System.out.println(ret);
		}
	}

	static int max = 30;
	@Autowired
	LoveService loveService;

	@Test
	public void loadLoves(){
		for (int i = 0; i < max; i++) {
			Love love = new Love();
			love.setAnonymous(1);
			love.setContent("xx,i love you");
			love.setCreatedDate(new Date());
			love.setStatus(1);
			love.setUserId(2L);
			love.setUpdatedDate(new Date());
			loveService.addLove(love);
		}
	}

	@Autowired
	PostMapper postMapper;
	@Test
	public void loadPosts(){
		Random r = new Random();
		for (int i = 0; i < max * 2; i++) {
			Post post = new Post();
			post.setUserId(3L);
			int val = r.nextInt(2);
			post.setStatus(val);
			post.setTitle("post title");
			post.setContent("post 内容");
			post.setCreatedDate(new Date());
			postMapper.insertSelective(post);
		}
	}


	@Autowired
	MessageMapper messageMapper;

	static int messageMax = 4;
	@Test
	public void loadMessage(){
		Message message = new Message();
		for (int i = 0; i < messageMax; i++) {
			message.setHasRead(ReadStatus.has_no_read);
			message.setFromId(1000L);
			message.setToId(1001L);
			message.setConversationId("1000_1001");
			message.setContent("message");
			message.setCreatedDate(new Date());
			messageMapper.insertSelective(message);
		}

		for (int i = 0; i < messageMax; i++) {
			message.setHasRead(ReadStatus.has_no_read);
			message.setFromId(1001L);
			message.setToId(1002L);
			message.setConversationId("1001_1002");
			message.setContent("message");
			message.setCreatedDate(new Date());
			messageMapper.insertSelective(message);
		}

		for (int i = 0; i < messageMax; i++) {
			message.setHasRead(ReadStatus.has_no_read);
			message.setFromId(1002L);
			message.setToId(1003L);
			message.setConversationId("1002_1003");
			message.setContent("message");
			message.setCreatedDate(new Date());
			messageMapper.insertSelective(message);
		}
	}

	@Autowired
	UserService userService;

	static Random random = new Random();
	@Test
	public void addUser(){
//		User user = new User();
//		user.setUpdatedDate(new Date());
//		user.setCreatedDate(new Date());
//		user.setAccPoints(0);
//		user.setVip(VIPStatus.non_member);
//		user.setRegisterStatus(RegisterStatus.unConfim);
//		user.setPhone("17868800595");
//		user.setEmail("1715976003@qq.com");
//		user.setHeadUrl("http://img2.imgtn.bdimg.com/it/u=2340206182,2517834380&fm=27&gp=0.jpg");
//		user.setPassword("guohaodong");

		for (int i = 0; i < 200; i++) {
			String nickname = "工小匠" + random.nextInt(10000);
			String password = "xiaojiang" + random.nextInt(10000);
			String email = "171597" + random.nextInt(10000) + "@qq.com";
			String phone = "171597" + random.nextInt(10000);
			Map<String , Object> map =  userService.register(nickname, password, email, phone);
			String str = (String)map.get("msg");
			System.out.println(str);
		}
	}

	@Test
	public void testEditProfile(){
		User user;

		for (long id = 1003; id < 1006; id++) {
			user = userService.getUserById(id);
			user.setMajor("软件工程");
			user.setEntranceYear(2015);
			String nickname = "田爽" + random.nextInt(10000);
			String description = "生活不只眼前的枸杞，还有保温杯";
			int sex = random.nextInt(2) ;
			user.setNickName(nickname);
			user.setDescription(description);
			user.setSex((byte)sex);
			user.setUpdatedDate(new Date());
			userService.updateUser(user);
		}
	}

	@Autowired
    FeedMapper feedMapper;

	@Test
    public void loadFeeds(){
        for (int i = 0; i < 100; i++) {
            Feed feed = new Feed();
            feed.setData("明月即使有");
            feed.setEventType(2);
            feed.setEventId(1L);
            feed.setUserId(1199L);
            int ret = feedMapper.insert(feed);
            System.out.println(ret);
        }
    }
}
