package football.data.repository;

import football.data.model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepository extends JpaRepository<Club, Integer> {
    Optional<Club> findByClubNameIgnoreCase(String clubName);
}
