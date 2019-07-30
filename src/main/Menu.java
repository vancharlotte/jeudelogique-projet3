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



    /** affichage sélection du jeu et lancement de la partie
     * @throws InputMismatchException input=String*/

       private void displayMenuJeu(){
        logger.info("Chaque combinaison doit être composée de " + config.getSizeCode()+ " chiffres." +
                "Les chiffres doivent être compris entre 0 et " + config.getNumber()+". Vous avez "+ config.getNbTrialMax()+
                " essais possibles.");

        Scanner sc = new Scanner(System.in);
        logger.info("Avec quel mode de jeu souhaitez-vous jouer?");
        logger.info("tapez 1 pour jouer à Plus ou Moins (indice = chiffre de la combinaison est plus grand (+), plus petit (-) ou c'est le bon (=))");
        logger.info("tapez 2 pour jouer au Mastermind  (indice = nombre de chiffres bien placés et mal placés)");
        logger.info("Saisissez votre choix : ");
        try{
            int selectedGame = sc.nextInt();
            initJeu(selectedGame);
            if (selectedGame ==1){
                logger.info("Vous avez choisi de jouer  au Plus ou Moins.");
            }
            else if (selectedGame==2){
                logger.info("Vous avez choisi de jouer  au MasterMind.");
            }
            else{
                logger.error("erreur saisie");
                logger.info("Votre choix n'est pas valide. Veullez entrer 1 ou 2. ");
                displayMenuJeu();
            }
        }
        catch(InputMismatchException e) {
            logger.error("erreur saisie");
            logger.info("Votre choix n'est pas valide. Veuillez entrer 1 ou 2. ");
            displayMenuJeu();
        }
    }

    /** méthode qui instancie le jeu en fonction de la sasie utilisateur
     * @param selectedGame input utilisateur, choix du jeu
     * @return instance du jeu sélectionné
     */
    public Game initJeu (int selectedGame){
        if (selectedGame ==1){
            game = new PlusOuMoins();
        }
        else if (selectedGame==2){
            game = new Mastermind();
        }
        else{
            game = new Game();

        }
       return game;

    }

    /** affichage sélection du mode jeu et lancement de la partie
     * @throws InputMismatchException input = String*/
    public  void displayMenuModeJeu(){
        displayMenuJeu();
        Scanner sc = new Scanner(System.in);
        logger.info("Avec quel mode de jeu souhaitez-vous jouer?");
        logger.info("tapez 1 pour jouer au mode Challengeur");
        logger.info("tapez 2 pour jouer au mode Defenseur");
        logger.info("tapez 3 pour jouer au mode Duel");
        logger.info("Saisissez votre choix : ");
        try{
            int selectedMode = sc.nextInt();
            initModeJeu(selectedMode);
            switch (selectedMode) {
                case 1:
                    logger.info("vous avez choisi le mode Challengeur.");
                    gameplay.play();
                    break;
                case 2:
                    logger.info("vous avez choisi le mode Defenseur.");
                    gameplay.play();
                    break;
                case 3:
                    logger.info("vous avez choisi le mode Duel.");
                    gameplay.play();
                    break;
                default :
                    logger.error("erreur saisie");
                    logger.info("Votre choix n'est pas valide. Veullez entrer 1, 2 ou 3. ");
                    displayMenuModeJeu();
                    break;
            }
        }
        catch(InputMismatchException e) {
            displayMenuModeJeu();
        }
    }

    /** méthode qui instancie le mode de jeu en fonction de la sasie utilisateur
     * @param selectedMode input utilisateur choix du mode jeu
     * @return instance du mode de jeu sélectionné
     */
    public ModeDeJeu initModeJeu (int selectedMode){
        switch (selectedMode) {
            case 1:
                gameplay=new Challengeur(game);
                break;
            case 2:
                gameplay =new Defenseur(game);
                break;
            case 3:
                gameplay = new Duel(game);
                break;
            default :
                gameplay = new ModeDeJeu(game);
                break;
        }
        return gameplay;

    }




}
