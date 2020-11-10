import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FoodCollectionTest {

    @Test
    public void testFoodCollectionCreation() {
        List<Food> foodList = Arrays.asList(
                new Food("Lasanha", "Massa"),
                new Food("Lasanha", "Massa"),
                new Food("Pizza", "Massa"),
                new Food("Bolo de Chocolate", "Bolo"),
                new Food("Bolo de Laranja", "BoLo")
        );

        FoodCollection foodCollection = new FoodCollection(foodList);

        assertEquals(foodList.size() - 1, foodCollection.size());
        assertEquals(2, foodCollection.getFoodTypes().size());
    }

    @Test
    public void testFindFoodByType() {
        List<Food> foodList = Arrays.asList(
                new Food("Lasanha", "Massa"),
                new Food("Lasanha", "Massa"),
                new Food("Pizza", "Massa"),
                new Food("Bolo de Chocolate", "Bolo"),
                new Food("Bolo de Laranja", "BoLo")
        );

        FoodCollection foodCollection = new FoodCollection(foodList);

        assertEquals(0, foodCollection.findFoodByType(new FoodType("testing")).size());
        assertEquals(2, foodCollection.findFoodByType(new FoodType("bolo")).size());
        assertEquals(2, foodCollection.findFoodByType(new FoodType("massa")).size());
    }
}
