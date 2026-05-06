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
public class ClubWebController {

    private final ClubService clubService;
    private final PlayerService playerService;

    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    @GetMapping("/clubs")
    public String findClubs(@RequestParam(required = false) String name, Model model) {
        if (name != null && !name.isBlank()) {
            try {
                model.addAttribute("club", clubService.findClubByName(name));
            } catch (ClubNotFoundException e) {
                model.addAttribute("notFound", true);
            }
            model.addAttribute("name", name);
        }
        return "findClubs";
    }

    @GetMapping("/clubs/new")
    public String newClubForm(Model model) {
        model.addAttribute("clubRequestDTO", new ClubRequestDTO());
        return "createOrUpdateClub";
    }

    @GetMapping("/clubs/{id}")
    public String clubDetail(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("club", clubService.getClubById(id));
            model.addAttribute("players", playerService.getPlayersByClubId(id));
        } catch (ClubNotFoundException e) {
            return "redirect:/clubs";
        }
        return "clubDetails";
    }

    @GetMapping("/clubs/{id}/edit")
    public String editClubForm(@PathVariable Integer id, Model model) {
        try {
            ClubResponseDTO club = clubService.getClubById(id);
            model.addAttribute("clubRequestDTO",
                    new ClubRequestDTO(club.getClubName(), club.getCity(),
                            club.getFounded(), club.getStadium(), club.getCapacity()));
            model.addAttribute("clubId", id);
        } catch (ClubNotFoundException e) {
            return "redirect:/clubs";
        }
        return "createOrUpdateClub";
    }

    @PostMapping("/clubs")
    public String createClub(@Valid @ModelAttribute ClubRequestDTO clubRequestDTO,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "createOrUpdateClub";
        }
        ClubResponseDTO created = clubService.createClub(clubRequestDTO);
        return "redirect:/clubs/" + created.getClubId();
    }

    @PostMapping("/clubs/{id}")
    public String updateClub(@PathVariable Integer id,
                             @Valid @ModelAttribute ClubRequestDTO clubRequestDTO,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("clubId", id);
            return "createOrUpdateClub";
        }
        clubService.updateClub(id, clubRequestDTO);
        return "redirect:/clubs/" + id;
    }
}
