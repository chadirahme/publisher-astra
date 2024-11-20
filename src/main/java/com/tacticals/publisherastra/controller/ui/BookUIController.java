package com.tacticals.publisherastra.controller.ui;

import com.tacticals.publisherastra.model.Book;
import com.tacticals.publisherastra.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/ui")
public class BookUIController {

    private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

    @Autowired
    BookRepository bookRepository;

    //http://localhost:8080/ui/books/OL1001041W
    @GetMapping(value = "/books/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            String coverImageUrl = "/images/no-image.png";
            if (book.getCoverIds() != null && book.getCoverIds().size() > 0) {
                coverImageUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
            }
            model.addAttribute("coverImage", coverImageUrl);
            model.addAttribute("book", book);
//            if (principal != null && principal.getAttribute("login") != null) {
//                String userId = principal.getAttribute("login");
//                model.addAttribute("loginId", userId);
//                UserBooksPrimaryKey key = new UserBooksPrimaryKey();
//                key.setBookId(bookId);
//                key.setUserId(userId);
//                Optional<UserBooks> userBooks = userBooksRepository.findById(key);
//                if (userBooks.isPresent()) {
//                    model.addAttribute("userBooks", userBooks.get());
//                } else {
//                    model.addAttribute("userBooks", new UserBooks());
//                }
//            }
            return "book";
        }
        return "book-not-found";
    }
}
