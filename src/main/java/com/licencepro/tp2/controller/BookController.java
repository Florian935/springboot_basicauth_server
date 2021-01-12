package com.licencepro.tp2.controller;

import com.licencepro.tp2.domain.Book;
import com.licencepro.tp2.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1.0/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        List<Book> allBooks = bookService.getAll();

        return new ResponseEntity<>(allBooks, OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Book> getOne(@PathVariable String uuid) {
        final Book findedBook = bookService.getOne(uuid);

        return new ResponseEntity<>(findedBook, OK);
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book) {
        Book bookSaved = bookService.save(book);

        return new ResponseEntity<>(bookSaved, CREATED);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<Book> update(@PathVariable String uuid, @RequestBody Book book) {
        book.setUuid(uuid);
        Book updatedBook = bookService.update(book);

        return new ResponseEntity<>(OK);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> deleteOne(@PathVariable String uuid) throws Exception {
        bookService.deleteOne(uuid);

        return new ResponseEntity<>(NO_CONTENT);
    }
}
