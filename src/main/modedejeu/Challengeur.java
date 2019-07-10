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
            if (player2.code.equals(player1.proposal) || nbTrial == config.getNbTrialMax()) {
                 gameEnd= true;
                sentenceEnd(player2.code,player1.proposal,nbTrial,config.getNbTrialMax());
                return;
            } else {
                logger.info("Vous avez proposé la combinaison :" + player1.proposal + ". => indice :" +game.generateHint(player2.code,player1.proposal));
            }
        }
    }

    /**
     *  affichage de phrase de fin fonction de l'issue de la partie
     * @param code combinaison à trovuer
     * @param proposal propsiton de l'adversaire
     * @param nbTrial nombre d'essais utilisés
     * @param nbTrialMax nombre d'essais max
     */
    public void sentenceEnd(List<Integer> code, List<Integer> proposal, int nbTrial, int nbTrialMax){
        if (code.equals(proposal))logger.info("Bravo, vous avez gagné trouvé la combinaison.");
        if(nbTrial == nbTrialMax) logger.info("Perdu! La combinaison de l'ordinateur été : " + code);
        logger.info("La combinaison a trouvé été : " + code);
        replay(player2.proposalList,player2.present);
    }

    /**
     * méthode qui permet de relancer une nouvelle partie ou non
     * @param proposalList liste de propositions de la partie précedécente
     * @param present chiffres présents dans la combinaison à trouver à la partie précédente
     */
    @Override
    public void replay(List proposalList, List present) {
        gameEnd=false;
        nbTrial=0;
        super.replay(proposalList, present);
    }

}
