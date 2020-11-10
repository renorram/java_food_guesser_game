package exception;

public class InvalidFoodTypeNameException extends IllegalArgumentException {
    public InvalidFoodTypeNameException(String foodTypeName) {
        super(String.format("The name %s is an invalid food type name.", foodTypeName));
    }
}
