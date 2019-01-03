package com.zhao.util;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * @author aotu
 * @date 2019/1/3 18:47
 */
public class TestRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        AuthenticationInfo authenticationInfo = null;

        if (username.equals("zhangsan")) {
            authenticationInfo = new SimpleAuthenticationInfo("zhangsan", "123456", this.getName());
        }

        return authenticationInfo;
    }
}
