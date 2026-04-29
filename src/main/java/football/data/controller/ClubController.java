package football.data.controller;

import football.data.model.ClubRequestDTO;
import football.data.model.ClubResponseDTO;
import football.data.model.PlayerResponseDTO;
import football.data.services.ClubService;
import football.data.services.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<ClubResponseDTO> createClub(@Valid @RequestBody ClubRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.createClub(dto));
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllClubs(
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(clubService.getAllClubs(limit, offset));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubResponseDTO> getClubById(@PathVariable Integer id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ClubResponseDTO> updateClub(
            @PathVariable Integer id,
            @RequestBody ClubRequestDTO dto) {
        return ResponseEntity.ok(clubService.updateClub(id, dto));
    }

    @GetMapping("/{id}/players")
    public ResponseEntity<List<PlayerResponseDTO>> getPlayersByClub(@PathVariable Integer id) {
        return ResponseEntity.ok(playerService.getPlayersByClubId(id));
    }
}
