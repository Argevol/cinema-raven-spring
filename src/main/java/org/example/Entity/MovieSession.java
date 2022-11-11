package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSession {
    private Long id;
    private Long filmId;
    private String date;
    private String time;
    private Long hallId;
}
