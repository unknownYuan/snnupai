package me.snnupai.door.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.snnupai.door.async.EventProducer;
import me.snnupai.door.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    EventProducer eventProducer;

    @Autowired
    UserService userService;


    @ApiOperation(value = "用户注册", notes = "用户注册")
    @ApiImplicitParams( {
            @ApiImplicitParam(name = "username" , value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "rememberme", value = "是否记住我,记住时间为一周", dataType = "bool"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "confirmPassword", value = "验证密码", dataType = "String")
    })
    @RequestMapping(path = {"/reg"}, method = {RequestMethod.POST})
    public JSONObject reg(@RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value="rememberme", defaultValue = "false") boolean rememberme,
                      @RequestParam("email") String email,
                      @RequestParam("phone") String phone,
                      @RequestParam("confirmPassword") String confimPassword) {
        JSONObject jsonObject = new JSONObject();
        try {
            if(!password.equals(confimPassword)){
                jsonObject.put("msg", "你输入的两次密码不一致");
                return jsonObject;
            }
            jsonObject = userService.register(username, password, email, phone);
            if(jsonObject.get("msg").equals("ok")) {
                if (rememberme) {
                    userService.login(phone, password, rememberme);
                }
            }
            return jsonObject;
        } catch (Exception e) {
            logger.error("注册异常" + e.getMessage());
            jsonObject.put("msg", "注册异常");
            return jsonObject;
        }
    }

    @ApiOperation(value = "用户登录仅仅支持邮箱和手机号登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userstr", value = "手机号或者邮箱", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String"),
            @ApiImplicitParam(name = "rememberme", value = "是否记住我，时间为一周", dataType = "bool")
    })
    @RequestMapping(path = {"/login"}, method = {RequestMethod.POST})
    public JSONObject login(Model model,@RequestParam("userstr") String userstr,
                        @RequestParam("password") String password,
                        @RequestParam(value="rememberme", defaultValue = "false") boolean rememberme) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject = userService.login(userstr, password, rememberme);
            return jsonObject;
        } catch (Exception e) {
            logger.error("登陆异常" + e.getMessage());
            jsonObject.put("msg", "登录异常");
            return jsonObject;
        }
    }

    @ApiOperation(value = "退出登录状态")
    @RequestMapping(path = {"/logout"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            return "ok";
        }catch (Exception e){
            logger.error(e.getMessage());
            return "fail";
        }
    }
}
