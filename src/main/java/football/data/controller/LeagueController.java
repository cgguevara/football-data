package football.data.controller;

import football.data.model.LeagueRequestDTO;
import football.data.model.LeagueResponseDTO;
import football.data.services.LeagueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
@RequiredArgsConstructor
public class LeagueController {

    private final LeagueService leagueService;

    @PostMapping
    public ResponseEntity<LeagueResponseDTO> createLeague(@Valid @RequestBody LeagueRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(leagueService.createLeague(dto));
    }

    @GetMapping
    public ResponseEntity<List<LeagueResponseDTO>> getAllLeagues() {
        return ResponseEntity.ok(leagueService.getAllLeagues());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> getLeagueById(@PathVariable Integer id) {
        return ResponseEntity.ok(leagueService.getLeagueById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LeagueResponseDTO> updateLeague(
            @PathVariable Integer id,
            @RequestBody LeagueRequestDTO dto) {
        return ResponseEntity.ok(leagueService.updateLeague(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeague(@PathVariable Integer id) {
        leagueService.deleteLeague(id);
        return ResponseEntity.noContent().build();
    }
}
