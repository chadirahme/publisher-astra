package com.tacticals.publisherastra.controller;

import com.tacticals.publisherastra.book.Book;
import com.tacticals.publisherastra.book.BookRepository;
import com.tacticals.publisherastra.model.Author;
import com.tacticals.publisherastra.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    //add get mapping for /books
    //reading from bookrepository

    @Autowired
    AuthorRepository authorRepository;
//    @Autowired
//    BookRepository bookRepository;

    @GetMapping("/all")
    public String getBooks() {

       //List<Book> list= bookRepository.findAll();
       // return "books > " + list.size();
        Author author=new Author();
        author.setId("1");
        author.setName("author1");
        author.setAddress("address1");
        authorRepository.save(author);
        return "books > "+authorRepository.findAll().size();
    }
}
