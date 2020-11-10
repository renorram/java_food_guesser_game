import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

final public class FoodCollection extends HashSet<Food> {
    private final HashSet<FoodType> foodTypeList;

    FoodCollection() {
        super();
        this.foodTypeList = new HashSet<>();
    }

    FoodCollection(List<Food> foodList) {
        this();
        super.addAll(foodList);
    }

    public HashSet<FoodType> getFoodTypes() {
        return this.foodTypeList;
    }

    @Override
    public boolean add(Food food) {
        this.foodTypeList.add(food.getFoodType());

        return super.add(food);
    }

    public HashSet<Food> findFoodByType(FoodType foodType) {
        return (HashSet<Food>) this.stream().filter(food -> food.getFoodType().equals(foodType)).collect(Collectors.toSet());
    }
}