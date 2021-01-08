package com.kandoka.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kandoka.blog.common.lang.Result;
import com.kandoka.blog.entity.Blog;
import com.kandoka.blog.service.BlogService;
import com.kandoka.blog.shiro.AccountProfile;
import com.kandoka.blog.util.ShiroUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author kandoka
 * @since 2020-09-09
 */
@RestController
public class BlogController {

    @Autowired
    BlogService blogService;

        @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        //一页5条记录
        Page page = new Page(currentPage, 5);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        //判断该博客是否存在
        Assert.notNull(blog, "该博客已被删除");
        return Result.succ(blog);
    }

    /**
     * 编辑博客
     * @param blog
     * @return
     */
    @RequiresAuthentication
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;

        if(blog.getId() != null){//更新已有博客
            temp = blogService.getById(blog.getId());
            //只能编辑自己的文章
            //long类型封装类的比较用equals
            Assert.isTrue(temp.getUserId()
                    .equals( ShiroUtils.getProfile().getId()),
                    "没有权限编辑"
                    );
        } else {//新的博客
            temp = new Blog();
            temp.setUserId(ShiroUtils.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        //复制blog的其它属性到temp
        BeanUtil.copyProperties(blog, temp,
                "id", "userId", "created", "status");

        blogService.saveOrUpdate(temp);

        return Result.succ(null);
    }
}
