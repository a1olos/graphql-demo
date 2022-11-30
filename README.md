多个查询同时进行：
query {
    authorById(id: "author-2") {
        firstName
        lastName
    },
    bookById(id: "book-1"){
        name
    }
    bookList{
        name
    }
}


更改数据：
mutation{
    createBook(input:{
        id: "book-4",
        name:"this is test book",
        pageCount: 12,
        authorId:"author-1"    
    }) {
    id
    }
}