package com.aiolos.graphqldemo.service;

import com.aiolos.graphqldemo.dao.AuthorMapper;
import com.aiolos.graphqldemo.entity.Author;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务简化类
 * </p>
 *
 * @author system
 * @since 2022-11-23
 */
@Service
public class AuthorService extends ServiceImpl<AuthorMapper, Author> {

}
