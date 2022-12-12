package com.aiolos.graphqldemo.controller;

import cn.hutool.core.bean.BeanUtil;
import com.aiolos.graphqldemo.entity.Author;
import com.aiolos.graphqldemo.entity.Book;
import com.aiolos.graphqldemo.input.BookInput;
import com.aiolos.graphqldemo.service.AuthorService;
import com.aiolos.graphqldemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/elasticsearch")
public class ElasticsearchController {


    @GetMapping("/index")
    public String index(){
        return "index";
    }
}