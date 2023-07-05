package lk.ijse.dep10.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO implements Serializable {
    @NotBlank(message = "ISBN can not be empty")
    private String isbn;
    @NotBlank(message = "Title can not be empty")
    private String title;
    @NotBlank(message = "Author can not be empty")
    @Pattern(regexp = "^[A-Za-z ]+$",message = "Invalid author name")
    private String author;
    @NotNull(message = "Copies can't be null")
    @PositiveOrZero(message = "copies can't be negative")
    private Integer copies;
}
