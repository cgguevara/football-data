package football.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDTO {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private String nationality;
    private LocalDate birthDate;
    private String birthPlace;
    private String position;
    private Float weight;
    private Float height;
    private String clubName;
}
