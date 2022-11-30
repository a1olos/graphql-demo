package com.aiolos.graphqldemo.input;

import lombok.Data;

@Data
public class BookInput {

    private String id;
    private String name;
    private int pageCount;
    private String authorId;
}