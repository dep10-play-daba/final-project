package lk.ijse.dep10.bookservice.service.impl;

import lk.ijse.dep10.bookservice.dto.BookDTO;
import lk.ijse.dep10.bookservice.repository.BookRepository;
import lk.ijse.dep10.bookservice.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveBook(BookDTO book) {

    }

    @Override
    public void updateBook(BookDTO book) {

    }

    @Override
    public void deleteBook(String isbn) {

    }

    @Override
    public void getBook(String isbn) {

    }

    @Override
    public List<BookDTO> findBooks(String query) {
        return null;
    }
}
