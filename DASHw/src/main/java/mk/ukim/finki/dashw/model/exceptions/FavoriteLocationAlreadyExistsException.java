package mk.ukim.finki.dashw.model.exceptions;

public class FavoriteLocationAlreadyExistsException extends RuntimeException{
    public FavoriteLocationAlreadyExistsException() {
        super("Location is already added.");
    }
}
