package com.kandoka.blog.util;

import com.kandoka.blog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * @author: Kandoka
 * @createTime: 2020/11/11 18:00
 * @description:
 */

public class ShiroUtils {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
