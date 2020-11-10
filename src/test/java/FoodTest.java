import exception.InvalidFoodNameException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodTest {
    @ParameterizedTest
    @MethodSource("invalidFoodNameProvider")
    public void testFoodInvalidName(String foodName) {
        assertThrows(InvalidFoodNameException.class, () -> new Food(foodName, new FoodType("Massa")));
    }

    public static Object[][] invalidFoodNameProvider() {
        return new Object[][]{
                {""},
                {"   "},
                {" ! @ # & ( ) – [ { } ] : ; ', ? / *"},
                {" ` ~ $ ^ + = < > “ \""},
                {null}
        };
    }
}