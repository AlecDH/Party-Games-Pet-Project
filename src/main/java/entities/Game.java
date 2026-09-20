package entities;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<String> materials = new ArrayList<>();
    List<String> categories = new ArrayList<>();
    List<Integer> playerCapacity = new ArrayList<>();
    List<Integer> duration = new ArrayList<>();
    String materialsText;
    String name;
    String standardRules;
    String drinkingRules;
    String introText;


    public Game(String placeholderText, int placeholderNumber){
        materials.add(placeholderText);

        categories.add(placeholderText);

        playerCapacity.add(placeholderNumber);
        playerCapacity.add(placeholderNumber);

        duration.add(placeholderNumber);
        duration.add(placeholderNumber);

        materialsText = placeholderText;
        name = placeholderText;
        standardRules = placeholderText;
        drinkingRules = placeholderText;
        introText = placeholderText;
    }
}
