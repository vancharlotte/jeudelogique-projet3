package main.jeux;

import main.Config;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public  List<Object> hint = new ArrayList<>();
    protected Config config = new Config();

    /**
     * méthode qui génére l'indice pour l'adversaire
     * @param code combinaison à trouver
     * @param proposal proposition de l'adversaire
     * @return hint indice pour l'adversaire
     */
    public String generateHint(List<Integer> code, List<Integer> proposal){
        return "";
    }
}
