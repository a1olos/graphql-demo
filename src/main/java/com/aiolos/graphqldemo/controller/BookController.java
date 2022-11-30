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

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;


    @QueryMapping
    public Book bookById(@Argument String id) {
        return bookService.getById(id);
    }

    @QueryMapping
    public List<Book> bookList() {
        return bookService.list();
    }

    @SchemaMapping
    public Author author(Book book) {
        return authorService.getById(book.getAuthorId());
    }


    @MutationMapping
    public Book createBook(@Argument BookInput input){
        Book book = BeanUtil.copyProperties(input,Book.class);
        bookService.save(book);
        return book;
    }

}