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

    /**
     * valider le format de la saisie utilisateur (combinaison/proposition)
     * @return boolean chosenCombiIsCorrect
     * @throws NumberFormatException
     */
    public boolean chosenCombiIsCorrect(String Combi){
        boolean chosenCombiIsCorrect;
        try{
            if (Combi.length() != config.getSizeCode()) {
                chosenCombiIsCorrect = false;}
            else if (!selectedNumber(Combi)){
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
        Scanner sc = new Scanner(System.in);
        String chosenCode = sc.nextLine();
        code.clear();
        logger.info("Choisissez votre combinaison secrète à " + config.getSizeCode() + " chiffres :");
        logger.info("Elle doit être composée de chiffres compris entre 0 et " + config.getNumber());
        boolean correctCombi = chosenCombiIsCorrect(chosenCode);
        if (correctCombi){
            for (int i = 0; i < config.getSizeCode(); i++) {
                code.add(Integer.parseInt(String.valueOf(chosenCode.charAt(i))));
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
        Scanner sc = new Scanner(System.in);
        String chosenProposal = sc.nextLine();
        proposal.clear();
        logger.info("Entrez une proposition de réponse à " + config.getSizeCode() + " chiffres :");
        boolean correctCombi = chosenCombiIsCorrect(chosenProposal);
        if (correctCombi){
            for (int i = 0; i < config.getSizeCode(); i++) {
                proposal.add(Integer.parseInt(String.valueOf(chosenProposal.charAt(i))));
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
     */
    public void selectHint(List<Object>hint, Game game) {
        Scanner sc = new Scanner(System.in);
        logger.info("Votre combinaison secrète est " + code + ". Donnez un indice à l'ordinateur : ");
        if (game instanceof PlusOuMoins) {
            String input = sc.nextLine();
            boolean hintIsCorrect = hintIsCorrectPOM(hint, input);
            if (!hintIsCorrect) {
                logger.error("erreur saisie");
                logger.info("Votre indice est incorrecte, il fallait donner l'indice : " + hint + ". Recommencez.");
                selectHint(hint, game);
            }
        } else if (game instanceof Mastermind) {
            logger.info("Combien de chiffres sont bien placés?");
            int input1 = sc.nextInt();
            logger.info("Combien de chiffres sont mal placés?");
            int input2 = sc.nextInt();
            boolean hintIsCorrect = hintIsCorrectMM(hint, input1, input2);
            if (!hintIsCorrect) {
                logger.error("erreur saisie");
                logger.info("Votre indice est incorrecte, il fallait donner l'indice : " + hint + ". Recommencez.");
                selectHint(hint, game);
            }
        }
    }

    /**
     * valider indice donné par utilisateur plus ou moins
     * @param hint (indice généré par ordinateur) input (indice donné par utilisateur)
     * @return boolean hintIsCorrect
     */
    public boolean hintIsCorrectPOM(List<Object>hint,String input){
        boolean hintIsCorrect = true;
        List<Object> userHintPOM = new ArrayList<>();
        if (input.length() != config.getSizeCode()) {
            hintIsCorrect = false;}
        else {
            userHintPOM.clear();
            for (int i = 0; i < config.getSizeCode(); i++) {
                userHintPOM.add(input.charAt(i));
            }
            if (!userHintPOM.equals(hint)) {
                hintIsCorrect = false;}
        }
	return hintIsCorrect;
}

    /**
     * valider indice donné par utilisateur mastermind
     *      * @param hint (indice généré par ordinateur) input (indice donné par utilisateur)
     *      * @return boolean hintIsCorrect
     * return boolean hintIsCorrect
     * @throws InputMismatchException
     */
    public boolean hintIsCorrectMM(List<Object>hint, int input1, int input2){
        boolean hintIsCorrect = true;
        List<Object> userHintMM = new ArrayList<>();
        userHintMM.clear();
        userHintMM.add(input1);
        userHintMM.add(input2);
        try{
            if (!userHintMM.equals(hint)) {
                    hintIsCorrect = false;}
        }
        catch (InputMismatchException e) {
            hintIsCorrect = false;
        }
        return hintIsCorrect;
    }
}
