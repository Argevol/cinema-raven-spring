package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CinemaDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private MultiValueMap<Long, List<Long>> mapOfAllFilmSessions;
}
