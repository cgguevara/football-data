package football.data.model;

public class ClubNotFoundException extends RuntimeException {
    public ClubNotFoundException(Integer clubId) {
        super("Club not found: " + clubId);
    }

    public ClubNotFoundException(String name) {
        super("Club not found: " + name);
    }
}
