package football.data.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClubResponseDTO {
    private Integer clubId;
    private String clubName;
    private String city;
    private Integer founded;
    private String stadium;
    private Integer capacity;
    private List<PlayerSummaryDTO> players;
}
