package main.modedejeu;

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

public abstract class ModeDeJeu {
    protected static final Logger logger = LogManager.getLogger();
    protected Game game;
    List<Object> hint = new ArrayList<>();
    int nbTrial;
    boolean gameEnd;
    protected int nbTrialMax;
    private int sizeCode;
    private int number;
    User player1;
    Computer player2;

    public ModeDeJeu(Game game, int sizeCode, int number, int nbTrialMax) {
        this.sizeCode= sizeCode;
        this.number = number;
        this.nbTrialMax = nbTrialMax;
        this.player1 = new User(sizeCode,number);
        this.player2 = new Computer(sizeCode,number);
        this.game = game;
        if (game instanceof PlusOuMoins){
            hint =((PlusOuMoins) game).hint;
        }
        else if (game instanceof Mastermind){
            hint =((Mastermind) game).hint;
        }
    }

    /** méthode qui définit le déroulement d'une partie en fonction du mode de jeu */
    public abstract void play();

    /**
     * méthode qui permet de relancer une nouvelle partie ou non
     * @throws InputMismatchException input = String
     */
    public void replay() {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu(sizeCode, number, nbTrialMax);
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


