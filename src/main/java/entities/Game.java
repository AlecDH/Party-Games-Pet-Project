package entities;

import java.util.List;

public class Game {

    private List<String> materials;
    private List<String> categories;
    private int minPlayers;
    private int maxPlayers;
    private int minDuration;
    private int maxDuration;
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

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public int getMaxDuration() {
        return maxDuration;
    }

    public Game(String name, int minDuration, int maxDuration, int minPlayers, int maxPlayers, List<String> categories, List<String> materials, String materialsText, String standardRules, String drinkingRules, String introText){
        this.materials = materials;
        this.categories = categories;

        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;

        this.materialsText = materialsText;
        this.name = name;
        this.standardRules = standardRules;
        this.drinkingRules = drinkingRules;
        this.introText = introText;
    }
}
