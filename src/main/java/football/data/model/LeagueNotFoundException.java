package football.data.model;

public class LeagueNotFoundException extends RuntimeException {
    public LeagueNotFoundException(Integer leagueId) {
        super("League not found: " + leagueId);
    }
}
