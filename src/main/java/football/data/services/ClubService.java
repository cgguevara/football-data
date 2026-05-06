package football.data.services;

import football.data.model.*;
import football.data.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubService {

    private final ClubRepository clubRepository;

    public ClubResponseDTO createClub(ClubRequestDTO dto) {
        Club club = new Club();
        club.setClubName(dto.getClubName());
        club.setCity(dto.getCity());
        club.setFounded(dto.getFounded());
        club.setStadium(dto.getStadium());
        club.setCapacity(dto.getCapacity());
        return toResponse(clubRepository.save(club), false);
    }

    @Transactional(readOnly = true)
    public ClubResponseDTO getClubById(Integer id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ClubNotFoundException(id));
        return toResponse(club, true);
    }

    @Transactional(readOnly = true)
    public List<ClubResponseDTO> getAllClubsList() {
        return clubRepository.findAll().stream()
                .map(c -> toResponse(c, false))
                .toList();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getAllClubs(int limit, int offset) {
        int size = Math.max(limit, 1);
        var page = clubRepository.findAll(PageRequest.of(offset / size, size));
        List<ClubResponseDTO> clubs = page.getContent().stream()
                .map(c -> toResponse(c, false))
                .toList();
        return Map.of("clubs", clubs, "total", page.getTotalElements());
    }

    @Transactional(readOnly = true)
    public ClubResponseDTO findClubByName(String name) {
        Club club = clubRepository.findByClubNameIgnoreCase(name)
                .orElseThrow(() -> new ClubNotFoundException(name));
        return toResponse(club, true);
    }

    public ClubResponseDTO updateClub(Integer id, ClubRequestDTO dto) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ClubNotFoundException(id));
        if (dto.getClubName() != null) club.setClubName(dto.getClubName());
        if (dto.getCity() != null) club.setCity(dto.getCity());
        if (dto.getFounded() != null) club.setFounded(dto.getFounded());
        if (dto.getStadium() != null) club.setStadium(dto.getStadium());
        if (dto.getCapacity() != null) club.setCapacity(dto.getCapacity());
        return toResponse(clubRepository.save(club), false);
    }

    private ClubResponseDTO toResponse(Club club, boolean includePlayers) {
        List<PlayerSummaryDTO> players = List.of();
        if (includePlayers && club.getPlayers() != null) {
            players = club.getPlayers().stream()
                    .map(p -> new PlayerSummaryDTO(p.getPlayerId(), p.getFirstName(), p.getLastName(), p.getNationality()))
                    .toList();
        }
        return new ClubResponseDTO(club.getClubId(), club.getClubName(), club.getCity(),
                club.getFounded(), club.getStadium(), club.getCapacity(), players);
    }
}
