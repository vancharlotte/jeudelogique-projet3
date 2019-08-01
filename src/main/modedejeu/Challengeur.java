package main.modedejeu;

import main.jeux.Game;
import java.util.List;

public class Challengeur extends ModeDeJeu  {


    public Challengeur(Game game) {
        super(game);
    }

     /** méthode qui définit le déroulement d'une partie en mode challengeur */
    @Override
    public void play() {
        player2.selectCode();
        while (!gameEnd) {
            player1.selectProposal();
            nbTrial++;
            if (player2.code.equals(player1.proposal) || nbTrial ==nbTrialMax) {
                 gameEnd= true;
                sentenceEnd(player2.code,player1.proposal,nbTrial,nbTrialMax);
                return;
            } else {
                logger.info("Vous avez proposé la combinaison :" + player1.proposal + ". => indice :" +game.generateHint(player2.code,player1.proposal));
            }
        }
    }

    /**
     *  affichage de phrase de fin fonction de l'issue de la partie
     * @param code combinaison à trouver
     * @param proposal propsiton de l'adversaire
     * @param nbTrial nombre d'essais utilisés
     * @param nbTrialMax nombre d'essais max
     */
    private void sentenceEnd(List<Integer> code, List<Integer> proposal, int nbTrial, int nbTrialMax){
        if (code.equals(proposal))logger.info("Bravo, vous avez trouv\u00e9 la combinaison.");
        if(nbTrial == nbTrialMax) logger.info("Perdu! La combinaison de l'ordinateur \u00e9t\u00e9 : " + code);
        logger.info("La combinaison a trouv\u00e9 \u00e9t\u00e9 : " + code);
        replay();
    }

    /**
     * méthode qui permet de relancer une nouvelle partie ou non

     */
    @Override
    public void replay(){
        gameEnd=false;
        nbTrial=0;
        super.replay();
    }

}
