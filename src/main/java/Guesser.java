import exception.InvalidFoodNameException;
import exception.InvalidFoodTypeNameException;

import javax.swing.*;
import java.util.HashSet;

final public class Guesser {

    private final FoodCollection foodCollection;

    private static final String DEFAULT_TITLE = "Food Guesser Game";

    Guesser() {
        this.foodCollection = new FoodCollection();
        addGuess("Lasanha", "Massa");
        addGuess("Bolo de Chocolate", "Bolo");
    }

    public void initGuess() {
        String[] options = {"Adivinhar..", "Sair"};

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

        if (i == 1) {
            JOptionPane.showMessageDialog(null, "Até mais!", DEFAULT_TITLE, JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        boolean foundFood = lookUpFoodType();
        if (!foundFood) {
            this.askForNewGuess();
        }

        this.initGuess();
    }

    public boolean lookUpFoodType() {
        HashSet<FoodType> foodTypes = this.foodCollection.getFoodTypes();
        for (FoodType foodType : foodTypes) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    String.format("A comida que você pensou é um(a) %s?", foodType.getName()),
                    Guesser.DEFAULT_TITLE,
                    JOptionPane.YES_NO_OPTION
            );

            if (result == 0) {
                return this.lookUpFoodByType(foodType);
            }
        }

        return false;
    }

    public boolean lookUpFoodByType(FoodType foodType) {
        HashSet<Food> foods = this.foodCollection.findFoodByType(foodType);
        for (Food food : foods) {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    String.format("A comida que você pensou é %s?", food.getName()),
                    Guesser.DEFAULT_TITLE,
                    JOptionPane.YES_NO_OPTION
            );

            if (result == 0) {
                JOptionPane.showMessageDialog(null, "Acertei!");
                return true;
            }
        }

        return false;
    }

    public void askForNewGuess() {
        String foodTypeName = JOptionPane.showInputDialog("Que tipo de comida você pensou?");
        String foodName = JOptionPane.showInputDialog("Qual o nome da comida que você pensou?");

        while (true) {
            try {
                Food food = new Food(foodName, foodTypeName);
                this.foodCollection.add(food);
                break;
            } catch (InvalidFoodNameException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "O nome da comida é inválido!",
                        DEFAULT_TITLE,
                        JOptionPane.ERROR_MESSAGE
                );

                foodName = JOptionPane.showInputDialog("Digite novamente, qual o nome da comida que você pensou??");
            } catch (InvalidFoodTypeNameException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "O nome do tipo da comida é inválido!",
                        DEFAULT_TITLE,
                        JOptionPane.ERROR_MESSAGE
                );

                foodTypeName = JOptionPane.showInputDialog("Digite novamente, que tipo de comida você pensou?");
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "Guesser atualizado! pressione ok para jogar novamente ;)",
                DEFAULT_TITLE,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void addGuess(String foodName, String foodType) {
        this.foodCollection.add(new Food(foodName, foodType));
    }
}