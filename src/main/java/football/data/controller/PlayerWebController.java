package football.data.controller;

import football.data.model.*;
import football.data.services.ClubService;
import football.data.services.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PlayerWebController {

    private final PlayerService playerService;
    private final ClubService clubService;

    @GetMapping("/clubs/{clubId}/players/new")
    public String newPlayerForm(@PathVariable Integer clubId, Model model) {
        try {
            clubService.getClubById(clubId);
        } catch (ClubNotFoundException e) {
            return "redirect:/clubs";
        }
        PlayerRequestDTO dto = new PlayerRequestDTO();
        dto.setClubId(clubId);
        model.addAttribute("playerRequestDTO", dto);
        model.addAttribute("clubId", clubId);
        return "createOrUpdatePlayer";
    }

    @GetMapping("/players/{id}/edit")
    public String editPlayerForm(@PathVariable Integer id,
                                 @RequestParam Integer clubId,
                                 Model model) {
        try {
            PlayerResponseDTO player = playerService.getPlayerById(id);
            PlayerRequestDTO dto = new PlayerRequestDTO();
            dto.setFirstName(player.getFirstName());
            dto.setLastName(player.getLastName());
            dto.setNationality(player.getNationality());
            dto.setBirthDate(player.getBirthDate());
            dto.setBirthPlace(player.getBirthPlace());
            dto.setPosition(player.getPosition());
            dto.setWeight(player.getWeight());
            dto.setHeight(player.getHeight());
            dto.setClubId(clubId);
            model.addAttribute("playerRequestDTO", dto);
            model.addAttribute("playerId", id);
            model.addAttribute("clubId", clubId);
        } catch (PlayerNotFoundException e) {
            return "redirect:/clubs/" + clubId;
        }
        return "createOrUpdatePlayer";
    }

    @PostMapping("/clubs/{clubId}/players")
    public String createPlayer(@PathVariable Integer clubId,
                               @Valid @ModelAttribute PlayerRequestDTO playerRequestDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clubId", clubId);
            return "createOrUpdatePlayer";
        }
        playerRequestDTO.setClubId(clubId);
        playerService.createPlayer(playerRequestDTO);
        return "redirect:/clubs/" + clubId;
    }

    @PostMapping("/players/{id}")
    public String updatePlayer(@PathVariable Integer id,
                               @Valid @ModelAttribute PlayerRequestDTO playerRequestDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("playerId", id);
            model.addAttribute("clubId", playerRequestDTO.getClubId());
            return "createOrUpdatePlayer";
        }
        playerService.updatePlayer(id, playerRequestDTO);
        Integer clubId = playerRequestDTO.getClubId();
        return clubId != null ? "redirect:/clubs/" + clubId : "redirect:/clubs";
    }
}
