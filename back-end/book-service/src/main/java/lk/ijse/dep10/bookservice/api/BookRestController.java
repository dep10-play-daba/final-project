package lk.ijse.dep10.bookservice.api;

import lk.ijse.dep10.bookservice.dto.BookDTO;
import lk.ijse.dep10.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/books")
@CrossOrigin
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBook(@RequestBody @Validated BookDTO bookDTO){
        bookService.saveBook(bookDTO);
    }

    @PatchMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBook(@RequestParam String isbn,@RequestBody @Validated BookDTO bookDTO){
        bookDTO.setIsbn(isbn);
        bookService.updateBook(bookDTO);
    }

    @DeleteMapping("/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBook(@RequestParam String isbn){
        bookService.deleteBook(isbn);
    }

    @GetMapping("/{isbn}")
    public BookDTO getBook(@RequestParam String isbn){
        return bookService.getBook(isbn);
    }

    @GetMapping
    public List<BookDTO> findBook(@RequestParam(name = "q",required = false) String query){
        return bookService.findBooks(query);
    }
}
