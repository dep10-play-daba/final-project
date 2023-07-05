package lk.ijse.dep10.bookservice.service;

import lk.ijse.dep10.bookservice.dto.BookDTO;

import java.util.List;

public interface BookService {
    void saveBook(BookDTO book);
    void updateBook(BookDTO book);
    void deleteBook(String isbn);
    void getBook(String isbn);
    List<BookDTO> findBooks(String query);
}
