package com.kandoka.blog.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Kandoka
 * @createTime: 2020/09/10 09:11
 * @description:
 */

/**
 * 而在AccountRealm我们还用到了AccountProfile，
 * 这是为了登录成功之后返回的一个用户信息的载体
 */
@Data
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

    private String avatar;

    private String email;

}
