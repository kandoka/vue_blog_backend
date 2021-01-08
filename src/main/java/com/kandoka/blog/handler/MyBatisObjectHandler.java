package com.kandoka.blog.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <p>
 *  日期自动填充的自定义类，实现元对象处理器接口
 * </p>
 *
 * @Author kandoka
 * @Date 2021/1/8 17:57
 */
@Slf4j
@Component
public class MyBatisObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("created", LocalDateTime.now(),metaObject);
        setFieldValByName("updated", LocalDateTime.now(),metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updated",LocalDateTime.now(),metaObject);
    }
}
