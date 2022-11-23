多个查询同时进行：
query {
    authorById(id: "author-2") {
        firstName
        lastName
    },
    bookById(id: "book-1"){
        name
    }
}