package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private Long id;
    private String name;
    private MultiValueMap<Long, List<Long>> mapOfAllFilmSessions;
}