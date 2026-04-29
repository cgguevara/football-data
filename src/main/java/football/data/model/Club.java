package football.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "club")
@Getter
@Setter
@NoArgsConstructor
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Integer clubId;

    @Column(name = "club_name", nullable = false)
    private String clubName;

    @Column(name = "city")
    private String city;

    @Column(name = "founded")
    private Integer founded;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    private List<Player> players;
}
