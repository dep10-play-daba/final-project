package lk.ijse.dep10.bookservice.service.impl;

import lk.ijse.dep10.bookservice.dto.BookDTO;
import lk.ijse.dep10.bookservice.entity.Book;
import lk.ijse.dep10.bookservice.repository.BookRepository;
import lk.ijse.dep10.bookservice.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper mapper;
    public BookServiceImpl(BookRepository bookRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveBook(BookDTO book) {
        if (bookRepository.existsById(book.getIsbn()) )
            throw new ResponseStatusException(HttpStatus.CONFLICT,"The isbn : "+book.getIsbn()+" already exists");
            bookRepository.save(mapper.map(book, Book.class));
    }

    @Override
    public void updateBook(BookDTO book) {
        if (!bookRepository.existsById(book.getIsbn()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The isbn : "+book.getIsbn()+" is not found");
        bookRepository.save(mapper.map(book, Book.class));
    }

    @Override
    public void deleteBook(String isbn) {
        if (!bookRepository.existsById(isbn))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The isbn : "+isbn+" is not found");
        //todo : Check whether the book has been issued
        bookRepository.deleteById(isbn);
    }

    @Override
    public BookDTO getBook(String isbn) {
        return bookRepository.findById(isbn)
                .map(book -> mapper.map(book,BookDTO.class))
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"The isbn : "+isbn+" is not found"));

    }

    @Override
    public List<BookDTO> findBooks(String query) {
        query="%"+query+"%";
        return bookRepository.findBooksByIsbnLikeOrTitleLikeOrAuthorLike(query,query,query)
                .stream().map(book -> mapper.map(book,BookDTO.class)).collect(Collectors.toList());
    }
}
