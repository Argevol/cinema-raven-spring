package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSessionDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    private Long filmId;
    @DateTimeFormat(pattern = "dd.MM")
    private String date;
    @DateTimeFormat(pattern = "HH:mm")
    private String time;
    @NotNull
    private String hall;
}
