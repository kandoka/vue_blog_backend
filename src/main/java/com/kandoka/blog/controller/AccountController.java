package com.kandoka.blog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kandoka.blog.common.dto.LoginDTO;
import com.kandoka.blog.common.lang.Result;
import com.kandoka.blog.entity.User;
import com.kandoka.blog.service.UserService;
import com.kandoka.blog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: Kandoka
 * @createTime: 2020/11/11 16:08
 * @description:
 */

@RestController
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 用@validated来校验数据（基于entity中的各种lombok注解）
     * @param loginDTO
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>()
                .eq("username", loginDTO.getUsername()));
        Assert.notNull(user, "用户不存在");
        //验证密码是否正确
        if(!user.getPassword().equals(SecureUtil.md5(loginDTO.getPassword()))) {
            return Result.fail("密码不正确");
        }
        //登录成功后，根据user id生成jwt
        String jwt = jwtUtils.generateToken(user.getId());

//        System.out.println("===========login=========");
//        System.out.println("jwt: "+jwt);

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        //返回用户信息
        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    /**
     * 退出
     * @return
     */
    @RequiresAuthentication()
    @GetMapping("/logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
