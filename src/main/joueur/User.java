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
     * choix combinaison secrète utilisateur / mode défenseur, mode duel
     * @throws NumberFormatException
     */
    public void selectCode() {
        code.clear();
        Scanner sc = new Scanner(System.in);
        logger.info("Choisissez votre combinaison secrète à " + config.getSizeCode() + " chiffres :");
        logger.info("Elle doit être composée de chiffres compris entre 0 et " + config.getNumber());
        try{
        String chosenCombi = sc.nextLine();
        if (chosenCombi.length() != config.getSizeCode()) {
            logger.error("erreur saisie");
            logger.info("Votre combinaison n'est pas valide, veuillez en choisir une autre.");
            selectCode();
        }
        else if (!selectedNumber(chosenCombi)){
            logger.error("erreur saisie");
            logger.info("Les chiffres doivent être compris entre 0 et " + config.getNumber()+ ".");
            selectCode();
        }
        else {
            for (int i = 0; i < config.getSizeCode(); i++) {
                code.add(Integer.parseInt(String.valueOf(chosenCombi.charAt(i))));
            }
            logger.info("Votre combinaison secrète est : " + code);
        }
            } catch (NumberFormatException e) {
                logger.error("erreur saisie");
                logger.info("Votre combinaison n'est pas valide, veuillez en choisir une autre.");
                selectCode();
            }

    }


    /**choix proposition par utilisateur / mode challengeur, mode duel
     * @throws NumberFormatException*/
    public void  selectProposal(){
        Scanner sc = new Scanner(System.in);
        proposal.clear();
        logger.info("Entrez une proposition de réponse à " + config.getSizeCode() + " chiffres :");
        try{
        String chosenProposal = sc.nextLine();
         if (chosenProposal.length() != config.getSizeCode()) {
             logger.error("erreur saisie");
             logger.info("Votre combinaison n'est pas valide, veuillez en choisir une autre.");
             selectProposal();
         }
         else if (!selectedNumber(chosenProposal)){
             logger.error("erreur saisie");
             logger.info("Les chiffres doivent être compris entre 0 et " + config.getNumber()+ ".");
             selectProposal();
         }
         else {
             for (int i = 0; i < config.getSizeCode(); i++) {
                 if (Integer.parseInt(String.valueOf(chosenProposal.charAt(i))) > config.getNumber()) {
                     logger.error("erreur saisie");
                     logger.info("les chiffres choisis doivent être compris entre 0 et " + config.getNumber());
                     selectProposal();
                 }
             }
             for (int i = 0; i < config.getSizeCode(); i++) {
                 proposal.add(Integer.parseInt(String.valueOf(chosenProposal.charAt(i))));
             }
         }
         } catch (NumberFormatException e) {
                logger.error("erreur saisie {}",e.getMessage());
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
