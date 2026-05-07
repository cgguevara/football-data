package football.data.services;

import football.data.model.*;
import football.data.repository.ClubRepository;
import football.data.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;

    public PlayerResponseDTO createPlayer(PlayerRequestDTO dto) {
        Club club = clubRepository.findById(dto.getClubId())
                .orElseThrow(() -> new ClubNotFoundException(dto.getClubId()));
        Player player = new Player();
        applyDto(dto, player, club);
        return toResponse(playerRepository.save(player));
    }

    @Transactional(readOnly = true)
    public PlayerResponseDTO getPlayerById(Integer id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
        return toResponse(player);
    }

    @Transactional(readOnly = true)
    public List<PlayerResponseDTO> getPlayersByClubId(Integer clubId) {
        if (!clubRepository.existsById(clubId)) {
            throw new ClubNotFoundException(clubId);
        }
        return playerRepository.findByClub_ClubId(clubId).stream()
                .map(this::toResponse)
                .toList();
    }

    public PlayerResponseDTO updatePlayer(Integer id, PlayerRequestDTO dto) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
        if (dto.getFirstName() != null) player.setFirstName(dto.getFirstName());
        if (dto.getLastName() != null) player.setLastName(dto.getLastName());
        if (dto.getNationality() != null) player.setNationality(dto.getNationality());
        if (dto.getBirthDate() != null) player.setBirthDate(dto.getBirthDate());
        if (dto.getBirthPlace() != null) player.setBirthPlace(dto.getBirthPlace());
        if (dto.getPosition() != null) player.setPosition(dto.getPosition());
        if (dto.getWeight() != null) player.setWeight(dto.getWeight());
        if (dto.getHeight() != null) player.setHeight(dto.getHeight());
        if (dto.getClubId() != null) {
            Club club = clubRepository.findById(dto.getClubId())
                    .orElseThrow(() -> new ClubNotFoundException(dto.getClubId()));
            player.setClub(club);
        }
        return toResponse(playerRepository.save(player));
    }

    public void deletePlayer(Integer id) {
        if (!playerRepository.existsById(id)) {
            throw new PlayerNotFoundException(id);
        }
        playerRepository.deleteById(id);
    }

    private void applyDto(PlayerRequestDTO dto, Player player, Club club) {
        player.setFirstName(dto.getFirstName());
        player.setLastName(dto.getLastName());
        player.setNationality(dto.getNationality());
        player.setBirthDate(dto.getBirthDate());
        player.setBirthPlace(dto.getBirthPlace());
        player.setPosition(dto.getPosition());
        player.setWeight(dto.getWeight());
        player.setHeight(dto.getHeight());
        player.setClub(club);
    }

    private PlayerResponseDTO toResponse(Player player) {
        String clubName = player.getClub() != null ? player.getClub().getClubName() : null;
        return new PlayerResponseDTO(
                player.getPlayerId(), player.getFirstName(), player.getLastName(),
                player.getNationality(), player.getBirthDate(), player.getBirthPlace(),
                player.getPosition(), player.getWeight(), player.getHeight(), clubName);
    }
}
