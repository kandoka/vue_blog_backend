package com.kandoka.blog.service.impl;

import com.kandoka.blog.entity.Blog;
import com.kandoka.blog.mapper.BlogMapper;
import com.kandoka.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kandoka
 * @since 2020-09-09
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
