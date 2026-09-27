package factories;

import entities.Game;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    public GameFactory(){

    }

    public List<Game> createGames(int amount){
        List<Game> gameList = new ArrayList<>();

        List<String> materials = new ArrayList<>();
        materials.add("Terninger");
        materials.add("Raflebæger");

        List<String> categories = new ArrayList<>();
        categories.add("Terningespil");

        for (int i = 0; i < 9 ; i++){
        gameList.add(new Game("Snyd", 5,20,2,99, categories, materials,
                "Raflebæger + 4 eller 5 terninger pr. person",
                "Standardregler goes here",
                "Drukregler goes here",
                "Introtext goes here"));
        }

        return gameList;
    }
}
