package football.data.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDTO {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String nationality;
    private LocalDate birthDate;
    private String birthPlace;
    private String position;
    private Float weight;
    private Float height;

    @NotNull
    private Integer clubId;
}
