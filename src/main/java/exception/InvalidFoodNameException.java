package exception;

public class InvalidFoodNameException extends IllegalArgumentException {
    public InvalidFoodNameException(String foodName) {
        super(String.format("The name %s is an invalid name.", foodName));
    }
}
