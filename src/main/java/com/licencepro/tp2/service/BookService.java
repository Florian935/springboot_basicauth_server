package com.licencepro.tp2.service;

import com.licencepro.tp2.domain.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getOne(String uuid);

    Book save(Book book);

    void deleteOne(String uuid);

    Book update(Book book);
}
