package org.example.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private List<Long> films;
}
