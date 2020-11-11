package com.kandoka.blog.controller;

import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kandoka.blog.common.dto.LoginDTO;
import com.kandoka.blog.common.lang.Result;
import com.kandoka.blog.entity.User;
import com.kandoka.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kandoka
 * @createTime: 2020/11/11 16:08
 * @description:
 */

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    /**
     * 用@validated来校验数据（基于entity中的各种lombok注解）
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO) {
        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", loginDTO.getUsername()));
        Assert.notNull(user, "用户不存在");
        return null;
    }
}
