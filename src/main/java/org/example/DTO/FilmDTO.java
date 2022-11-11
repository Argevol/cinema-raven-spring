package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.Entity.FilmDetails;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDTO {
    private Long id;
    @NotNull
    private String name;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private String releaseDate;
    @NotNull
    private List<String> genres;
    @NotNull
    private String description;
    @Min(90)
    @Max(180)
    private int duration;
    private FilmDetails filmDetails;
}

