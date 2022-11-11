package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDetailsDTO {
    private String country;
    @Min(0)
    @Max(18)
    private int ageRestriction;
    private List<String> directors;
    private List<String> actors;
}
