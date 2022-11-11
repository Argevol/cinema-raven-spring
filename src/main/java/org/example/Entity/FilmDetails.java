package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDetails {
    private String country;
    private int ageRestriction;
    private List<String> directors;
    private List<String> actors;
}
