import exception.InvalidFoodNameException;

import java.util.Objects;

final public class Food {
    private final String name;
    private final FoodType foodType;

    Food(String name, FoodType foodType) {
        validateName(name);

        this.name = name;
        this.foodType = foodType;
    }

    Food(String name, String foodType) {
        this(name, new FoodType(foodType));
    }

    public static void validateName(String name) throws InvalidFoodNameException {
        if (name == null) {
            throw new InvalidFoodNameException("");
        }

        name = name.replaceAll("((?! )\\W|\\d)", "");
        if (name.trim().equals("")) {
            throw new InvalidFoodNameException(name);
        }
    }

    public String getName() {
        return name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return name.toLowerCase().equals(food.name.toLowerCase()) &&
                foodType.equals(food.foodType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), foodType);
    }
}