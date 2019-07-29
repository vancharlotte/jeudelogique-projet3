package main.modedejeu;


import main.Config;
import main.Menu;
import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;
import main.joueur.Computer;
import main.joueur.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ModeDeJeu {
    protected static final Logger logger = LogManager.getLogger();
    private static Config config = new Config();
    protected Game game;
    List<Object> hint = new ArrayList<>();
    int nbTrial;
    boolean gameEnd;
    static int nbTrialMax = config.getNbTrialMax();
    static int codeSize = config.getSizeCode();
    static int number = config.getNumber();
    User player1 = new User(codeSize,number);
    Computer player2 = new Computer(codeSize,number);
    Menu menu = new Menu();

    public ModeDeJeu(Game game) {
        this.game = game;
        if (game instanceof PlusOuMoins){
            hint =((PlusOuMoins) game).hint;
        }
        else if (game instanceof Mastermind){
            hint =((Mastermind) game).hint;
        }
    }

    /** méthode qui définit le déroulement d'une partie en fonction du mode de jeu */
    public void play(){
    }

    /**
     * méthode qui permet de relancer une nouvelle partie ou non
     * @throws InputMismatchException
     */
    public void replay() {
        Scanner sc = new Scanner(System.in);
        logger.info("Souhaitez vous refaire une partie?");
        logger.info("- 1 : oui");
        logger.info("- 2 : oui mais je veux changer de mode de jeu ");
        logger.info("- 3 : non merci, peut être plus tard");
        logger.info("Entrez le chiffre correspondant à votre choix : ");
        try {
            int choix = sc.nextInt();
            switch (choix) {
                case 1:
                    play();
                    break;
                case 2:
                    menu.displayMenuModeJeu();
                    break;
                case 3:
                    logger.info("Merci d'avoir joué!");
                    return;
                default:
                    logger.error("erreur saisie");
                    logger.info("Votre choix n'est pas valide. Veullez entrer 1, 2 ou 3. ");
                    replay();
                    break;
            }
        }
        catch (InputMismatchException e) {
            logger.error("erreur saisie");
            logger.info("Votre choix n'est pas valide. Veullez entrer 1, 2 ou 3. ");
            replay();
        }
    }

}


