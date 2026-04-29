package football.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSummaryDTO {
    private Integer playerId;
    private String firstName;
    private String lastName;
    private String nationality;
}
