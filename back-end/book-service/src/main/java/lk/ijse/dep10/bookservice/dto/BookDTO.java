package lk.ijse.dep10.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO implements Serializable {
    private String isbn;
    private String title;
    private String author;
    private Integer copies;
}
