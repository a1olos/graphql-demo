package com.aiolos.graphqldemo.controller;

import com.aiolos.graphqldemo.entity.Author;
import com.aiolos.graphqldemo.entity.Book;
import com.aiolos.graphqldemo.service.AuthorService;
import com.aiolos.graphqldemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

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

    @SchemaMapping
    public Author author(Book book) {
        return authorService.getById(book.getAuthorId());
    }

    /*@QueryMapping
    public Author authorById(@Argument String id) {
        return Author.getById(id);
    }*/
}