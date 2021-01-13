package com.licencepro.tp2.service;

import com.licencepro.tp2.data.BookStub;
import com.licencepro.tp2.domain.Book;
import com.licencepro.tp2.exception.book.BookDeletionException;
import com.licencepro.tp2.exception.book.BookInsertException;
import com.licencepro.tp2.exception.book.BookNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
public class BookServiceImpl implements BookService {

    private final List<Book> books;

    public BookServiceImpl() {
        books = BookStub.generateBooks();
    }

    @Override
    public List<Book> getAll() {
        return Optional
                .of(books)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "An error occured while retrieving the books.",
                                INTERNAL_SERVER_ERROR
                        )
                );
    }

    @Override
    public Book getOne(String uuid) {
        return getBookFromListByUuid(uuid).orElseThrow(() ->
                new BookNotFoundException(
                        String.format("Book with id '%s' not found", uuid),
                        NOT_FOUND
                )
        );
    }

    private Optional<Book> getBookFromListByUuid(String uuid) {
        return books
                .stream()
                .filter(book -> book.getUuid().equals(uuid))
                .findFirst();
    }

    @Override
    public Book save(Book book) {
        addBook(book);
        return book;
    }

    private void addBook(Book book) {
        if (book.getName() == null) {
            throw new BookInsertException("Name cannot be null", BAD_REQUEST);
        } else {
            final String uuid = UUID.randomUUID().toString();
            book.setUuid(uuid);
            books.add(book);
        }
    }

    @Override
    public void deleteOne(String uuid) {
        final Book book = getBookFromListByUuid(uuid).orElseThrow(() ->
                new BookDeletionException(
                        String.format(
                                "Cannot delete book with id '%s' because he doesn't exist",
                                uuid),
                        NOT_FOUND
                ));
        books.remove(book);
    }

    @Override
    public Book update(Book book) {
        Book bookUpdated = getOne(book.getUuid());

        if (StringUtils.hasText(book.getName())) {
            bookUpdated.setName(book.getName());
        } else {
            throw new BookInsertException("Name cannot be null", BAD_REQUEST);
        }

        return bookUpdated;
    }
}
