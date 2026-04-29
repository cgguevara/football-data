package football.data.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubRequestDTO {

    @NotBlank
    private String clubName;
    private String city;
    private Integer founded;
    private String stadium;
    private Integer capacity;
}
