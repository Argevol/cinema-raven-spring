package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO {
    @EqualsAndHashCode.Exclude
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private HashMap<Long, List<Long>> mapOfAllFilmSessions;
}
