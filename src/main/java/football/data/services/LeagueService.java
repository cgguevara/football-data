package football.data.services;

import football.data.model.League;
import football.data.model.LeagueNotFoundException;
import football.data.model.LeagueRequestDTO;
import football.data.model.LeagueResponseDTO;
import football.data.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueResponseDTO createLeague(LeagueRequestDTO dto) {
        League league = new League();
        applyDto(dto, league);
        return toResponse(leagueRepository.save(league));
    }

    @Transactional(readOnly = true)
    public List<LeagueResponseDTO> getAllLeagues() {
        return leagueRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public LeagueResponseDTO getLeagueById(Integer id) {
        return toResponse(findOrThrow(id));
    }

    public LeagueResponseDTO updateLeague(Integer id, LeagueRequestDTO dto) {
        League league = findOrThrow(id);
        if (dto.getLeagueName() != null) league.setLeagueName(dto.getLeagueName());
        if (dto.getCountry() != null) league.setCountry(dto.getCountry());
        if (dto.getSeason() != null) league.setSeason(dto.getSeason());
        if (dto.getTeamsNumber() != null) league.setTeamsNumber(dto.getTeamsNumber());
        return toResponse(leagueRepository.save(league));
    }

    public void deleteLeague(Integer id) {
        if (!leagueRepository.existsById(id)) {
            throw new LeagueNotFoundException(id);
        }
        leagueRepository.deleteById(id);
    }

    League findOrThrow(Integer id) {
        return leagueRepository.findById(id)
                .orElseThrow(() -> new LeagueNotFoundException(id));
    }

    private void applyDto(LeagueRequestDTO dto, League league) {
        league.setLeagueName(dto.getLeagueName());
        league.setCountry(dto.getCountry());
        league.setSeason(dto.getSeason());
        league.setTeamsNumber(dto.getTeamsNumber());
    }

    private LeagueResponseDTO toResponse(League league) {
        return new LeagueResponseDTO(
                league.getLeagueId(),
                league.getLeagueName(),
                league.getCountry(),
                league.getSeason(),
                league.getTeamsNumber());
    }
}
