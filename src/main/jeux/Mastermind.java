package main.jeux;

import java.util.ArrayList;
import java.util.List;

public class Mastermind extends Game {

    /**
     * méthode qui génére l'indice pour l'adversaire
     * pour le jeu Mastermind (bien placés, mal placés)
     * @param code combinaison à trouver
     * @param proposal proposition de l'adversaire
     * @return hint indice pour l'adversaire
     */
    public String generateHint(List<Integer> code, List<Integer> proposal){
        List<Object> copyCode = new ArrayList<>();
        copyCode.clear();
        hint.clear();
        copyCode.addAll(code);
        int rightPosition = 0;
        int wrongPosition = 0;


        for(int i =0; i<code.size(); i++){
            if(copyCode.get(i).equals(proposal.get(i))){
                rightPosition++;
                copyCode.set(i,"ok");
            }
            for(int j=0; j <code.size();j++){
                if(copyCode.get(i).equals(proposal.get(j))){
                    wrongPosition++;
                    copyCode.set(i,"ok");
                }
            }
        }
        hint.add(rightPosition);
        hint.add(wrongPosition);
        return hint.get(0) + " chiffre(s) bien placé(s), "+ hint.get(1) + " chiffre(s) mal placé(s).";
    }
}