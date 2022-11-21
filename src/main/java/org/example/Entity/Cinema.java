package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    private Long id;
    private String name;
    private String city;
    private Map<Long, List<Long>> mapOfAllFilmSessions;
}