package com.zhao;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @author aotu
 * @date 2019/1/3 18:42
 */
public class TestShiro {


    @Test
    public void test1() {
        //安全管理器工厂
        IniSecurityManagerFactory iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //获取安全管理器
        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();
        //将安全管理器设置到安全工具类中
        SecurityUtils.setSecurityManager(securityManager);
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //创建用户密码令牌
        AuthenticationToken authenticationToken = new UsernamePasswordToken("zhangsan", "1234567");

        try {
            subject.login(authenticationToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);


    }

}
