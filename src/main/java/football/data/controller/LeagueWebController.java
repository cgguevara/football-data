package football.data.controller;

import football.data.model.LeagueNotFoundException;
import football.data.model.LeagueRequestDTO;
import football.data.model.LeagueResponseDTO;
import football.data.services.ClubService;
import football.data.services.LeagueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class LeagueWebController {

    private final LeagueService leagueService;
    private final ClubService clubService;

    @GetMapping("/leagues/new")
    public String newLeagueForm(Model model) {
        model.addAttribute("leagueRequestDTO", new LeagueRequestDTO());
        return "createOrUpdateLeague";
    }

    @PostMapping("/leagues")
    public String createLeague(@Valid @ModelAttribute LeagueRequestDTO leagueRequestDTO,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "createOrUpdateLeague";
        }
        LeagueResponseDTO created = leagueService.createLeague(leagueRequestDTO);
        return "redirect:/leagues/" + created.getLeagueId();
    }

    @GetMapping("/leagues/{id}")
    public String leagueDetail(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("league", leagueService.getLeagueById(id));
        } catch (LeagueNotFoundException e) {
            return "redirect:/";
        }
        return "leagueDetails";
    }

    @GetMapping("/leagues/{id}/edit")
    public String editLeagueForm(@PathVariable Integer id, Model model) {
        try {
            LeagueResponseDTO league = leagueService.getLeagueById(id);
            LeagueRequestDTO dto = new LeagueRequestDTO(
                    league.getLeagueName(), league.getCountry(),
                    league.getSeason(), league.getTeamsNumber());
            model.addAttribute("leagueRequestDTO", dto);
            model.addAttribute("leagueId", id);
        } catch (LeagueNotFoundException e) {
            return "redirect:/";
        }
        return "createOrUpdateLeague";
    }

    @PostMapping("/leagues/{id}")
    public String updateLeague(@PathVariable Integer id,
                               @Valid @ModelAttribute LeagueRequestDTO leagueRequestDTO,
                               BindingResult result,
                               Model model) {
        if (result.hasErrors()) {
            model.addAttribute("leagueId", id);
            return "createOrUpdateLeague";
        }
        leagueService.updateLeague(id, leagueRequestDTO);
        return "redirect:/leagues/" + id;
    }

    @GetMapping("/leagues/{id}/clubs")
    public String listClubs(@PathVariable Integer id, Model model) {
        try {
            model.addAttribute("league", leagueService.getLeagueById(id));
            model.addAttribute("clubs", clubService.getClubsByLeagueId(id));
        } catch (LeagueNotFoundException e) {
            return "redirect:/";
        }
        return "listClubs";
    }
}
