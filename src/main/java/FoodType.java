import exception.InvalidFoodTypeNameException;

import java.util.Objects;

final public class FoodType implements Comparable<FoodType> {
    private final String name;

    FoodType(String name) {
        validateName(name);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    private static void validateName(String name) throws InvalidFoodTypeNameException {
        if (name == null) {
            throw new InvalidFoodTypeNameException("");
        }

        name = name.replaceAll("((?! )\\W|\\d)", "");
        if (name.trim().equals("")) {
            throw new InvalidFoodTypeNameException(name);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodType foodType = (FoodType) o;
        return name.toLowerCase().equals(foodType.name.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase());
    }

    @Override
    public int compareTo(FoodType foodType) {
        return name.compareTo(foodType.name);
    }
}
