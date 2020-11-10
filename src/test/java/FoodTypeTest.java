import exception.InvalidFoodTypeNameException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class FoodTypeTest {
    @ParameterizedTest
    @MethodSource("invalidFoodTypeNameProvider")
    public void testFoodInvalidName(String foodTypeName) {
        assertThrows(InvalidFoodTypeNameException.class, () -> new FoodType(foodTypeName));
    }

    public static Object[][] invalidFoodTypeNameProvider() {
        return new Object[][]{
                {""},
                {"   "},
                {" ! @ # & ( ) – [ { } ] : ; ', ? / *"},
                {" ` ~ $ ^ + = < > “ \""},
                {null}
        };
    }
}
