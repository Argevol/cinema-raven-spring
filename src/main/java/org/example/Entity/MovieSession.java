package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSession {
    @EqualsAndHashCode.Exclude
    private Long id;
    private Long filmId;
    private String date;
    private String time;
    private String hall;
}
