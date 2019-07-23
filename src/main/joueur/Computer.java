package main.joueur;

import main.Main;
import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Computer extends Player {

    public List<Integer> code = new ArrayList<>();
    public List<Integer> proposal = new ArrayList<>();
    private List<List> codeList = new ArrayList<>();
    public List<Integer> present = new ArrayList<>();
    public List<Object> proposalList = new ArrayList<>();


    /**
     * choix combinaison secrète ordinateur / mode challengeur, mode duel
     */
    @Override
    public void selectCode() {
        code.clear();
        Random rd = new Random();
        for (int i = 0; i < config.getSizeCode(); i++) {
            code.add(rd.nextInt(config.getNumber() ));//on peut choisir les chiffres entre 0 et 9 (à modif pr mastermind)
        }
        if (Main.isModeDev()) {
            logger.info("Mode dev : La combinaison secrète de l'ordinateur est : " + code);
            logger.info("combinaison à " + config.getSizeCode()+ " chiffres comprise entre 0 et "+config.getNumber());
        }
    }

    /**
     * choix proposition par ordinateur / mode defenseur, mode duel
     * @param hint dernier indice donné à l'ordinateur
     * @param nbTrials nombres d'essais utilisés
     * @param game jeu sélectionné
     */
    public void selectProposal(List<Object> hint, int nbTrials, Game game) {
        if (game instanceof PlusOuMoins){
            selectProposalPOM(hint, nbTrials);

        }
        else if (game instanceof Mastermind){
            selectProposalMM(hint, nbTrials);
        }

    }

    /**
     * méthode qui permet à l'ordinauteur de sélectionner une proposition  pour le jeu plus ou moins
     * @param hint dernier indice donné à l'ordinateur
     * @param nbTrials nombres d'essais utilisés
     *
     */
    public void selectProposalPOM(List<Object> hint, int nbTrials) {
        if (nbTrials == 0) {
            codeList.clear();
            codeList = createCodeList();
            proposal.clear();
            chooseCode();

        } else {
                for (int i = 0; i < config.getSizeCode(); i++) {
                    List<Integer> possible = new ArrayList<>();
                    possible.clear();
                    possible = codeList.get(i);
                    switch ((Character) hint.get(i)) {
                        case '=':
                            for (Iterator<Integer> it = possible.iterator(); it.hasNext(); ) {
                                int egale = it.next();
                                if (egale != proposal.get(i)) {
                                    it.remove();
                                }
                            }
                            break;
                        case '+':
                            for (Iterator<Integer> it = possible.iterator(); it.hasNext(); ) {
                                int plus = it.next();
                                if (plus <= proposal.get(i)) {
                                    it.remove();
                                }
                            }
                            break;
                        case '-':
                            for (Iterator<Integer> it = possible.iterator(); it.hasNext(); ) {
                                int moins = it.next();
                                if (moins >= proposal.get(i)) {
                                    it.remove();
                                }
                            }
                            break;
                    }
                }
                proposal.clear();
                chooseCode();
            }
        logger.info("L'ordi vous propose :" + proposal);

    }

    /**
     * créer une liste des chiffres disponibles pour chaque index de la combinaison
     * (fonction du fichier de configuration) / jeu plusoumoins
     * @return liste des chiffres possibles
     */
    private List createCodeList() {
        for (int i = 0; i < config.getSizeCode(); i++) {
            List<Integer> possibleNb = new ArrayList<>();
            for (int j = 0; j < config.getNumber(); j++) {
                possibleNb.add(j);
            }
            codeList.add(possibleNb);
        }
        return codeList;
    }

    /**
     * pour choisir aléatoirement une proposition par l'ordinateur/ jeu plusoumoins
     * @return une proposition possible
     */
    private List chooseCode() {
        Random rd = new Random();
        for (int i = 0; i < config.getSizeCode(); i++) {
            List<Integer> possible = codeList.get(i);
            proposal.add(possible.get(rd.nextInt(possible.size())));
        }
        return proposal;

    }

    /**
     * méthode qui permet à l'ordinateur de sélectionner une proposition  pour le mastermind
     * - trouver tous les chiffres présents dans la combinaison
     * - mélanger les chiffres présents jusqu'à trouver la bonne combinaison
     * @param hint dernier indice donné à l'ordinateur
     * @param nbTrials nombres d'essais utilisés     */
    public void selectProposalMM(List<Object> hint, int nbTrials){
        if (nbTrials==0){
            proposalList.clear();
            present.clear();}
        if (nbTrials!=0&&present.size()<config.getSizeCode()){
            int nbHint =((Integer) hint.get(0) + (Integer) hint.get(1));
            for (int i = 0; i < nbHint; i++) {
                present.add(proposal.get(0));
            }
        }
        if(present.size()<config.getSizeCode()){
            selectFromNumber();
        }
        else if (present.size()==config.getSizeCode()){
            selectFromPresentNumber();
        }
        List<Object> newProposal = new ArrayList<>();
        newProposal.addAll(proposal);
        proposalList.add(newProposal);
        logger.info("L'ordi vous propose :" + proposal);

    }



    /**
     * l'ordinateur choisit une nouvelle proposition composé du même chiffre pour mastermind (lorsque tous les chiffres
     * présents n'ont pas encore été trouvés par l'ordinateur) /jeu mastermind
     */
    private void selectFromNumber() {
        Random rd = new Random();
        int n = rd.nextInt(config.getNumber());
        proposal.clear();
        for (int i = 0; i < config.getSizeCode(); i++) {
            proposal.add(n);
        }
        if (proposalList.contains(proposal)) {
            selectFromNumber();}
    }

    /**
     * l'ordinateur choisit une nouvelle proposition aléatoire composés des chiffres présents /jeu mastermind
     */
    private void selectFromPresentNumber(){
        Random rd = new Random();
        List<Integer> present2 = new ArrayList<>();
        present2.addAll(present);
        proposal.clear();
        for (int i =0; i<config.getSizeCode();i++){
            int random = rd.nextInt(present2.size());
            proposal.add(present2.get(random));
            present2.remove(random);
        }
        if (proposalList.contains(proposal)) {
            selectFromPresentNumber();
        }
    }

}