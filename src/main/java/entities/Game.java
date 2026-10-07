package entities;

import java.util.List;

public class Game {

    private int id;
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

    public int getID() {
        return id;
    }

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

    public Game(int id, String name, int minPlayers, int maxPlayers, int minDuration, int maxDuration, List<String> categories, List<String> materials, String materialsText, String standardRules, String drinkingRules, String introText){
        this.id = id;
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

    public Game(int id, String name, int minPlayers, int maxPlayers, int minDuration, int maxDuration, String introText){
        this.id = id;
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

    public void setID(int id) {
        this.id = id;
    }
}
