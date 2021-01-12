package com.licencepro.tp2.data;

import com.licencepro.tp2.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BookStub {

    public static List<Book> generateBooks() {
        return new ArrayList<>() {{
            add(new Book(UUID.randomUUID().toString(), "Mon livre 1"));
            add(new Book(UUID.randomUUID().toString(), "Mon livre 2"));
            add(new Book(UUID.randomUUID().toString(), "Mon livre 3"));
        }};
    }
}
