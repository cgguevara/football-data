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
public class LeagueRequestDTO {

    @NotBlank
    private String leagueName;

    @NotBlank
    private String country;

    private String season;
    private Integer teamsNumber;
}
