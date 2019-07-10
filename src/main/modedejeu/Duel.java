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
            if (player2.code.equals(player1.proposal) || nbTrial == config.getNbTrialMax()) {
                gameEnd = true;
                sentenceEnd(player1.code,player2.proposal,player2.code,player1.proposal,nbTrial,config.getNbTrialMax());
                return;
            } else {
                player2.selectProposal(hint,nbTrial, game);
                String hintForUser = game.generateHint(player2.code,player1.proposal);
                nbTrial++;
                if (player1.code.equals(player2.proposal) || nbTrial == config.getNbTrialMax()) {
                    gameEnd = true;
                    sentenceEnd(player1.code,player2.proposal,player2.code,player1.proposal,nbTrial,config.getNbTrialMax());
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
     * @param code1
     * @param proposal1
     * @param code2
     * @param proposal2
     * @param nbTrial
     * @param nbTrialMax
     */
    public void sentenceEnd(List<Integer> code1, List<Integer> proposal1, List<Integer> code2, List<Integer> proposal2, int nbTrial, int nbTrialMax){
        if (code1.equals(proposal1)) logger.info("Perdu! L'ordinateur a trouvé votre combinaison. La combinaison de l'ordinateur était : " + player2.code);
        if (code2.equals(proposal2)) logger.info("Bravo, vous avez gagné la partie!");
        if(nbTrial == nbTrialMax) logger.info("Egalité! La combinaison de l'ordinateur été : " + code1);
        replay(player2.proposalList, player2.present);
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
        proposalList.clear();
        super.replay(proposalList, present);
    }

}
