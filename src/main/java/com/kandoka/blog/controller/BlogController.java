package com.kandoka.blog.controller;


import com.kandoka.blog.common.lang.Result;
import com.kandoka.blog.entity.Blog;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        return Result.succ(null);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        return Result.succ(null);
    }

    @GetMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        return Result.succ(null);
    }
}
