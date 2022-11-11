package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    private Long id;
    private String name;
    private String releaseDate;
    private List<String> genres;
    private String description;
    private int duration;
    private FilmDetails filmDetails;
}
