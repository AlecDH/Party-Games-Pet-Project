package entities;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<String> materials = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private List<Integer> playerCapacity = new ArrayList<>();
    private List<Integer> duration = new ArrayList<>();
    private String materialsText;
    private String name;
    private String standardRules;
    private String drinkingRules;
    private String introText;

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getCategories() {
        return categories;
    }

    public List<Integer> getPlayerCapacity() {
        return playerCapacity;
    }

    public List<Integer> getDuration() {
        return duration;
    }

    public String getMaterialsText() {
        return materialsText;
    }

    public String getName() {
        return name;
    }

    public String getStandardRules() {
        return standardRules;
    }

    public String getDrinkingRules() {
        return drinkingRules;
    }

    public String getIntroText() {
        return introText;
    }

    public Game(String placeholderText, int placeholderNumber){
        materials.add(placeholderText);

        categories.add(placeholderText);

        playerCapacity.add(placeholderNumber);
        playerCapacity.add(placeholderNumber);

        duration.add(placeholderNumber);
        duration.add(placeholderNumber);

        materialsText = placeholderText;
        name = "Spil " + placeholderText;
        standardRules = placeholderText;
        drinkingRules = placeholderText;
        introText = placeholderText;
    }
}
