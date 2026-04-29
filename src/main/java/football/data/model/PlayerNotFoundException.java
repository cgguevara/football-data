package football.data.model;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(Integer playerId) {
        super("Player not found: " + playerId);
    }
}
