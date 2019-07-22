package main.jeux;

import java.util.List;

public class PlusOuMoins extends Game {


    /**
     * méthode qui génére l'indice pour l'adversaire
     * pour le jeu plus ou moins
     * @param code combinaison à trouver
     * @param proposal propositon de l'adversaire
     * @return hint indice pour l'adversaire
     */
    public String generateHint(List<Integer> code, List<Integer> proposal) {
            hint.clear();
            for (int index =0; index<code.size(); index++){
            int a = code.get(index);
            int b = proposal.get(index);
            if (a==b){
                hint.add('=');
            }
            else if (a < b){
                hint.add('-');
            }
            else{
                hint.add('+');
            }

        }
        return "" + hint;
    }
}
