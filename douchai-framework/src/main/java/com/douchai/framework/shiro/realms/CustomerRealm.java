package com.douchai.framework.shiro.realms;

import com.douchai.common.utils.JwtUtil;
import com.douchai.framework.shiro.JwtToken;
import com.douchai.system.domin.SysUser;
import com.douchai.system.service.SysUserService;
import com.douchai.system.service.impl.SysUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义realm
 */
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private SysUserServiceImpl sysUserService;

    /**
     * 重写此方法避免shiro报错
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //身份认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String username;
        try {
            username = JwtUtil.getUsername(token);
        } catch (Exception e) {
            throw new AuthenticationException("token拼写错误或者值为空");
        }
        if (username == null) {
            log.error("token无效");
            throw new AuthenticationException("token无效");
        }
        SysUser user = sysUserService.findByName(username);
        if (user == null) {
            log.error("用户不存在");
            throw new AuthenticationException("用户不存在");
        }
        if (!JwtUtil.verify(token, username, user.getPassword())) {
            log.error("用户名或密码错误(token无效或者与登录者不匹配)");
            throw new AuthenticationException("用户名或密码错误(token无效或者与登录者不匹配)");
        }
        return new SimpleAuthenticationInfo(token, token, this.getName());
    }
}
