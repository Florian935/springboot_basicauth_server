package com.licencepro.tp2.service;

import com.licencepro.tp2.data.BookStub;
import com.licencepro.tp2.domain.Book;
import com.licencepro.tp2.exception.book.BookDeletionException;
import com.licencepro.tp2.exception.book.BookInsertException;
import com.licencepro.tp2.exception.book.BookNotFoundException;
import com.licencepro.tp2.exception.book.ResponseException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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
        return getBook(
                uuid,
                new BookNotFoundException(
                        String.format("Book with id '%s' not found", uuid),
                        BAD_REQUEST
                )
        );
    }

    private Book getBook(String uuid, ResponseException ex) {
        return books
                .stream()
                .filter(book -> book.getUuid().equals(uuid))
                .findFirst()
                .orElseThrow(() -> ex);
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
        final Book book = getBook(
                uuid,
                new BookDeletionException(
                        String.format(
                                "Cannot delete book with id '%s' because he doesn't exist",
                                uuid),
                        BAD_REQUEST
                )
        );
        books.remove(book);
    }

    @Override
    public Book update(Book book) {
        return updateBook(book);
    }

    private Book updateBook(Book book) {
        Book bookUpdated = getOne(book.getUuid());

        if (StringUtils.hasText(book.getName())) {
            bookUpdated.setName(book.getName());
        } else {
            throw new BookInsertException("Name cannot be null", BAD_REQUEST);
        }

        return bookUpdated;
    }
}
