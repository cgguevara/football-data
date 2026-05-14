package football.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueResponseDTO {
    private Integer leagueId;
    private String leagueName;
    private String country;
    private String season;
    private Integer teamsNumber;
}
