package com.aiolos.graphqldemo.controller;


import com.aiolos.graphqldemo.entity.Author;
import com.aiolos.graphqldemo.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author system
 * @since 2022-11-23
 */
@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @QueryMapping
    public Author authorById(@Argument String id) {
        return authorService.getById(id);
    }
}

