package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cinema {
    @EqualsAndHashCode.Exclude
    private Long id;
    private String name;
    private String city;
    private HashMap<Long, List<Long>> mapOfAllFilmSessions;
}