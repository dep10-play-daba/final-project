package lk.ijse.dep10.bookservice.repository;

import lk.ijse.dep10.bookservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends JpaRepository<Book,String> {
}
