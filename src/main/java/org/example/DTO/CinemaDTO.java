package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private Map<Long, List<Long>> mapOfAllFilmSessions;
}
