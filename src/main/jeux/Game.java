package main.jeux;


import java.util.ArrayList;
import java.util.List;

public abstract class Game {
    public List<Object> hint = new ArrayList<>();

    /**
     * méthode qui génére l'indice pour l'adversaire
     * @param code combinaison à trouver
     * @param proposal proposition de l'adversaire
     * @return hint indice pour l'adversaire
     */
    public abstract String generateHint(List<Integer> code, List<Integer> proposal);

}

