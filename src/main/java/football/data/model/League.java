package football.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "league")
@Getter
@Setter
@NoArgsConstructor
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private Integer leagueId;

    @Column(name = "league_name", nullable = false)
    private String leagueName;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "season")
    private String season;

    @Column(name = "teams_number")
    private Integer teamsNumber;
}
