package main.modedejeu;

import main.jeux.Game;
import java.util.List;

public class Duel extends ModeDeJeu {

    public Duel(Game game) {
        super(game);
    }

    /** méthode qui définit le déroulement d'une partie en mode duel */
    @Override
   public void play() {
        player1.selectCode();
        player2.selectCode();
        while (!gameEnd) {
            player1.selectProposal();
            if (player2.code.equals(player1.proposal) || nbTrial == nbTrialMax) {
                gameEnd = true;
                sentenceEnd(player1.code,player2.proposal,player2.code,player1.proposal,nbTrial,nbTrialMax);
                return;
            } else {
                player2.selectProposal(hint,nbTrial, game);
                String hintForUser = game.generateHint(player2.code,player1.proposal);
                nbTrial++;
                if (player1.code.equals(player2.proposal) || nbTrial == nbTrialMax) {
                    gameEnd = true;
                    sentenceEnd(player1.code,player2.proposal,player2.code,player1.proposal,nbTrial,nbTrialMax);
                    return;
                } else {
                    String hintForIA  = game.generateHint(player1.code,player2.proposal);
                    player1.selectHint(hint,game);
                    logger.info("indice : "+hintForIA);
                    logger.info("Vous avez proposé la combinaison :" + player1.proposal + ". => indice :" + hintForUser);
                }
            }
        }
    }

    /**
     *  affichage de phrase de fin fonction de l'issue de la partie
     * @param code1 combinaison secrète de l'utilsateur
     * @param proposal1 propositon par utilisateur
     * @param code2 combinaison secrète de l'ordinateur
     * @param proposal2 proposition par l'ordinateur
     * @param nbTrial nombre d'essais utilisés
     * @param nbTrialMax nombre d'essais max
     */
    public void sentenceEnd(List<Integer> code1, List<Integer> proposal1, List<Integer> code2, List<Integer> proposal2, int nbTrial, int nbTrialMax){
        if (code1.equals(proposal1)) logger.info("Perdu! L'ordinateur a trouvé votre combinaison. La combinaison de l'ordinateur était : " + player2.code);
        if (code2.equals(proposal2)) logger.info("Bravo, vous avez gagné la partie!");
        if(nbTrial == nbTrialMax) logger.info("Egalité! La combinaison de l'ordinateur été : " + code1);
        replay();
    }

    /**
     * méthode qui permet de relancer une nouvelle partie ou non

     */
    @Override
    public void replay() {
        gameEnd=false;
        nbTrial=0;
        super.replay();
    }

}
