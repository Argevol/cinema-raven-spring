package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSessionDTO {
    private Long id;
    @NotNull
    private Long filmId;
    @DateTimeFormat(pattern = "dd.MM")
    private String date;
    @DateTimeFormat(pattern = "HH:mm")
    private String time;
    @NotNull
    private Long hallId;
}
