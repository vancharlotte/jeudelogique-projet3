package main.joueur;

import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class User extends Player {
    public List<Integer> code = new ArrayList<>();
    public List<Integer> proposal = new ArrayList<>();
    String chosenCombi;

    /**
     * valider le format de la saisie utilisateur (combinaison/proposition)
     * @return boolean chosenCombiIsCorrect 
     * @throws NumberFormatException
     */

    public boolean chosenCombiIsCorrect(){
        Scanner sc = new Scanner(System.in);
        this.chosenCombi = sc.nextLine();
        boolean chosenCombiIsCorrect;
        try{
            if (chosenCombi.length() != config.getSizeCode()) {
                chosenCombiIsCorrect = false;}
            else if (!selectedNumber(chosenCombi)){
                chosenCombiIsCorrect = false;}
            else {
                chosenCombiIsCorrect = true;}
        }
        catch (NumberFormatException e){
            chosenCombiIsCorrect = false;
        }
        return chosenCombiIsCorrect;
    }

    /** choix combinaison secrète utilisateur / mode défenseur, mode duel*/
    public void selectCode(){
        code.clear();
        logger.info("Choisissez votre combinaison secrète à " + config.getSizeCode() + " chiffres :");
        logger.info("Elle doit être composée de chiffres compris entre 0 et " + config.getNumber());
        boolean correctCombi = chosenCombiIsCorrect();
        if (correctCombi){
            for (int i = 0; i < config.getSizeCode(); i++) {
                code.add(Integer.parseInt(String.valueOf(chosenCombi.charAt(i))));
            }
            logger.info("Votre combinaison secrète est : " + code);
        }
        else {
            logger.error("erreur saisie");
            logger.info("Votre combinaison n'est pas valide, veuillez en choisir une autre.");
            selectCode();
        }
    }



    /**choix proposition par utilisateur / mode challengeur, mode duel */
    public void  selectProposal(){
        proposal.clear();
        logger.info("Entrez une proposition de réponse à " + config.getSizeCode() + " chiffres :");
        boolean correctCombi = chosenCombiIsCorrect();
        if (correctCombi){
            for (int i = 0; i < config.getSizeCode(); i++) {
                proposal.add(Integer.parseInt(String.valueOf(chosenCombi.charAt(i))));
            }
        }
        else {
            logger.error("erreur saisie");
            logger.info("Votre combinaison n'est pas valide, veuillez en choisir une autre.");
            selectProposal();
        }
    }


    /**
     * méthode pour vérifier si les chiffres sélectionnés par utilisateur sont conformes
     * aux chiffres disponibles (définis dans le fichier de configuration)
     * @param combi combinaison proposé par l'utilisateur
     * @return chiffres sélectionnés autorisés
     */
    public boolean selectedNumber(String combi){
        boolean selectedNumber = true;
        for (int i =0; i <config.getSizeCode(); i++){
           if(Integer.parseInt(String.valueOf(combi.charAt(i)))>config.getNumber()){
               selectedNumber = false; }
        }
        return  selectedNumber;
    }

    /**
     * indice donné par utilisateur
     * @param hint indice donné par l'utilisateur
     * @param game jeu sélectionné
     * @throws InputMismatchException
     */
    public void selectHint(List<Object>hint, Game game) {
        List<Object> userHint = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        logger.info("Votre combinaison secrète est " + code + ". Donnez un indice à l'ordinateur : ");
        if (game instanceof PlusOuMoins) {
            String input = sc.nextLine();
            if (input.length() != config.getSizeCode()) {
                logger.error("erreur saisie");
                logger.info("Votre indice est incorrecte, il fallait donner l'indice : " + hint + ". Recommencez.");
                selectHint(hint, game);
            } else {
                userHint.clear();
                for (int i = 0; i < config.getSizeCode(); i++) {
                    userHint.add(input.charAt(i));
                }
                if (!userHint.equals(hint)) {
                    logger.error("erreur saisie");
                    logger.info("Votre indice est incorrecte, il fallait donner l'indice : " + hint + ". Recommencez.");
                    selectHint(hint, game);
                }
            }
        } else if (game instanceof Mastermind) {
            try {
                logger.info("Combien de chiffres sont bien placés?");
                int input1 = sc.nextInt();
                userHint.add(input1);
                logger.info("Combien de chiffres sont mal placés?");
                int input2 = sc.nextInt();
                userHint.add(input2);
                if (!userHint.equals(hint)) {
                    logger.error("erreur saisie");
                    logger.info("Votre indice est incorrecte, il fallait donner l'indice : " + hint + ". Recommencez.");
                    selectHint(hint, game);
                } else {
                    userHint.clear();
                }
            }
            catch (InputMismatchException e) {
                logger.error("erreur saisie");
                logger.info("Votre indice est incorrecte, il fallait donner l'indice : " + hint + ". Recommencez.");
                selectHint(hint, game);
            }

        }
    }
}
