package com.kandoka.blog.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: Kandoka
 * @createTime: 2020/09/10 09:05
 * @description:
 */

public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String jwt) {
        this.token = jwt;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
