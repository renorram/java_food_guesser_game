import exception.InvalidFoodNameException;
import exception.InvalidFoodTypeNameException;

import javax.swing.*;
import java.util.HashSet;
import java.util.TreeSet;

final public class Guesser {

    private final FoodCollection foodCollection;

    private static final String DEFAULT_TITLE = "Food Guesser Game";

    Guesser() {
        this.foodCollection = new FoodCollection();
        addGuess("Lasanha", "Massa");
        addGuess("Bolo de Chocolate", "Bolo");
    }

    public void initGuess() {
        String[] options = {"OK"};

        int i = JOptionPane.showOptionDialog(
                null,
                "Pense em um prato que você gosta!",
                Guesser.DEFAULT_TITLE,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (i == 0) {
            lookUpFoodType();
        }
    }

    public void lookUpFoodType() {
        TreeSet<FoodType> foodTypeTreeSet = new TreeSet<>(this.foodCollection.getFoodTypes());
        FoodType last = foodTypeTreeSet.last();

        for (FoodType foodType : foodTypeTreeSet) {
            if (foodType == last) {
                this.lookUpFoodByType(foodType);
                return;
            }

            int result = JOptionPane.showConfirmDialog(
                    null,
                    String.format("A comida que você pensou é um(a) %s?", foodType.getName()),
                    Guesser.DEFAULT_TITLE,
                    JOptionPane.YES_NO_OPTION
            );

            if (result == 0) {
                this.lookUpFoodByType(foodType);
                return;
            }
        }
    }

    public void lookUpFoodByType(FoodType foodType) {
        HashSet<Food> foods = this.foodCollection.findFoodByType(foodType);
        Food lastGuessed = null;
        for (Food food : foods) {
            lastGuessed = food;
            int result = JOptionPane.showConfirmDialog(
                    null,
                    String.format("A comida que você pensou é %s?", food.getName()),
                    Guesser.DEFAULT_TITLE,
                    JOptionPane.YES_NO_OPTION
            );

            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Acertei!");
                initGuess();
                return;
            }
        }

        askForNewGuess(lastGuessed);
    }

    public void askForNewGuess(Food lastGuessed) {
        String foodName = JOptionPane.showInputDialog("Qual o nome da comida que você pensou?");
        while (true) {
            try {
                Food.validateName(foodName);
                break;
            } catch (InvalidFoodNameException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "O nome da comida é inválido!",
                        DEFAULT_TITLE,
                        JOptionPane.ERROR_MESSAGE
                );

                foodName = JOptionPane.showInputDialog("Digite novamente, qual o nome da comida que você pensou??");
            }
        }

        String foodTypeName = JOptionPane.showInputDialog(String.format(
                "%s é ______ mas %s não.",
                foodName,
                lastGuessed.getName()
        ));

        while (true) {
            try {
                Food food = new Food(foodName, foodTypeName);
                this.foodCollection.add(food);
                break;
            } catch (InvalidFoodTypeNameException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "O nome do tipo da comida é inválido!",
                        DEFAULT_TITLE,
                        JOptionPane.ERROR_MESSAGE
                );

                foodTypeName = JOptionPane.showInputDialog(String.format(
                        "A comida %s é ______ mas %s não é?",
                        foodName,
                        lastGuessed.getName()
                ));
            }
        }

        initGuess();
    }

    public void addGuess(String foodName, String foodType) {
        this.foodCollection.add(new Food(foodName, foodType));
    }
}