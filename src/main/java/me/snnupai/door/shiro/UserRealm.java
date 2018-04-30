package me.snnupai.door.shiro;

import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiImplicitParam;
import me.snnupai.door.mapper.UserMapper;
import me.snnupai.door.pojo.User;
import me.snnupai.door.service.UserService;
import me.snnupai.door.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * @author: hxy
 * @description: 自定义Realm
 * @date: 2017/10/24 10:06
 */
public class UserRealm extends AuthorizingRealm {
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Session session = SecurityUtils.getSubject().getSession();
        //查询用户的权限
        JSONObject permission = (JSONObject) session.getAttribute(Constants.SESSION_USER_PERMISSION);
        logger.info("permission的值为:" + permission);
        logger.info("本用户权限为:" + permission.get("permissionList"));
        //为当前用户设置角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermissions((Collection<String>) permission.get("permissionList"));
        return authorizationInfo;
    }

    /**
     * 验证当前登录的Subject
     * LoginController.login()方法中执行Subject.login()时 执行此方法
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String phoneOrEmail = (String) token.getPrincipal();
        // 获取用户密码
//        String password = new String((char[]) token.getCredentials());

//        JSONObject user = userService.getUser(phoneOrEmail, password);
        User user = null;
        if (phoneOrEmail.contains("@")) {
            user = userService.selectByEmail(phoneOrEmail);
        } else {
            user = userService.selectByPhone(phoneOrEmail);
        }
        if (user == null) {
            //没找到帐号
            throw new UnknownAccountException();
        }


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                phoneOrEmail,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()), //salt=username+salt,采用明文访问时，不需要此句
                getName()
        );

        //session中不需要保存密码
//        user.remove("password");
        user.setPassword("");
        user.setSalt("");
        //将用户信息放入session中
        SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER_INFO, user);
        return authenticationInfo;
    }
}
