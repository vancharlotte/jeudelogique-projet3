package main;

import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;
import main.modedejeu.Challengeur;
import main.modedejeu.Defenseur;
import main.modedejeu.Duel;
import main.modedejeu.ModeDeJeu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private static final Logger logger = LogManager.getLogger();
    private static Config config = new Config();
    private static Game game;
    private static ModeDeJeu gameplay;


    /**
     * règles du jeu + sélection du jeu
     * @return choix utilisateur
     * @throws InputMismatchException
     */
    private static Game DisplayMenuJeu(){
        logger.info("Chaque combinaison doit être composée de " + config.getSizeCode()+ " chiffres.\n " +
                "Les chiffres doivent être compris entre 0 et " + config.getNumber()+". Vous avez "+ config.getNbTrialMax()+
                " essais possibles.\n");

        Scanner sc = new Scanner(System.in);
        logger.info("Avec quel mode de jeu souhaitez-vous jouer?\n"+
                    "- tapez 1 pour jouer à Plus ou Moins \n (indice = chiffre de la combinaison est plus grand (+), plus petit (-) ou c'est le bon (=)) \n "+
                    "- tapez 2 pour jouer au Mastermind \n (indice = nombre de chiffres bien placés et mal placés) \n"+
                    "Saisissez votre choix : ");
        try {
            int selectedGame = sc.nextInt();
            if (selectedGame ==1){
                game = new PlusOuMoins();
                return game;
            }
            else if (selectedGame==2){
                game = new Mastermind();
                logger.info("Indiquez à votre adversaire le nombre de chiffres bien placés et mal placés.");
                return game;
            }
            else{
                logger.error("erreur saisie");
                logger.info("Votre choix n'est pas valide. Veullez entrer 1 ou 2. ");
                DisplayMenuJeu();
            }
        }
        catch(InputMismatchException e) {
            logger.error("erreur saisie");
            logger.info("Votre choix n'est pas valide. Veullez entrer 1 ou 2. ");
            DisplayMenuJeu();
        }
        return game;
    }

    /** sélection du mode jeu
     * @throws InputMismatchException*/
    public static void DisplayMenuModeJeu(){
        game = DisplayMenuJeu();
        Scanner sc = new Scanner(System.in);
        logger.info("Avec quel mode de jeu souhaitez-vous jouer? \n" +
                "- tapez 1 pour jouer au mode Challengeur \n" +
                "- tapez 2 pour jouer au mode Defenseur \n" +
                "- tapez 3 pour jouer au mode Duel \n" +
                "Saisissez votre choix : ");
        try {
            int selectedMode = sc.nextInt();
            switch (selectedMode) {
                case 1:
                    gameplay=new Challengeur(game);
                    gameplay.play();
                    break;
                case 2:
                    gameplay =new Defenseur(game);
                    gameplay.play();
                    break;
                case 3:
                    gameplay = new Duel(game);
                    gameplay.play();
                    break;
                default :
                    logger.error("erreur saisie");
                    logger.info("Votre choix n'est pas valide. Veullez entrer 1 ou 2. ");
                    DisplayMenuModeJeu();
                    break;
            }
        }
        catch(InputMismatchException e) {
            logger.error("erreur saisie");
            logger.info("Votre choix n'est pas valide. Veullez entrer 1 ou 2. ");
            DisplayMenuModeJeu();
        }
    }
}
