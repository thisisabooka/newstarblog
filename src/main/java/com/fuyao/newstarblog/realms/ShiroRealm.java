package com.fuyao.newstarblog.realms;


import com.fuyao.newstarblog.beans.system.Admin;
import com.fuyao.newstarblog.service.system.AdminService;
import com.fuyao.newstarblog.utils.MD5Matcher;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Shiro Realm
 * 登录认证，访问控制（授权）
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

//    授权，访问控制
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }


//    登录认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println(token.hashCode());

//		1，把AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;

//		2，从UsernamePasswordToken中获取nickname
        String nickname = upToken.getUsername();

//		3，调用数据库的方法，从数据库中查询nickname对应的记录
//        System.out.println("从数据库中获取username "+nickname+" 所对应的信息为：");
        Admin admin = adminService.findAdminByNickname(nickname);

//		4，若用户不存在，则可以抛出UnknownAccountException异常
        if(admin==null) {
            throw new UnknownAccountException("账号不存在");
        }

//		5，根据用户信息的情况，决定是否需要抛出其他的AuthenticationException
        if("1".equals(admin.getMonster())) {
            throw new LockedAccountException("账号被锁定");
        }

//		6，根据用户的情况，来构建AuthenticationInfo对象并返回。通常使用的实现类为SimpeAuthenticationInfo
//		以下信息是从数据库中获取的
//		principal：认证的实体信息，可以是nickname，也可以是数据表对应的用户的实体类对象
        Object principal = admin;
//		credentials：密码
//		Object credentials = admin.getPassword();
        Object credentials = credentials = MD5Matcher.MD5(admin.getPassword());
//		realName：当前realm对象的name，调用父类的getName()方法即可
        String realmName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realmName);

        return info;
    }
}
