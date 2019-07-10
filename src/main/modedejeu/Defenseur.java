package main.modedejeu;
import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;
import java.util.List;

public class Defenseur extends ModeDeJeu {

    public Defenseur(Game game) {
        super(game);
    }

    /** méthode qui définit le déroulement d'une partie en mode défenseur */
    @Override
    public void play() {
        player1.selectCode();
        while (!gameEnd) {
            if (game instanceof PlusOuMoins){
                player2.selectProposalPOM(hint,nbTrial);
            }
            else if (game instanceof Mastermind){
                player2.selectProposalMM(hint,nbTrial);
            }
            nbTrial++;
            if (player1.code.equals(player2.proposal) || nbTrial == config.getNbTrialMax()) {
                gameEnd = true;
                sentenceEnd(player1.code,player2.proposal,nbTrial, config.getNbTrialMax());
                return;
            } else {
                String hintForIA  = game.generateHint(player1.code,player2.proposal);
                player1.selectHint(hint,game);
                logger.info("indice : " + hintForIA);
            }
        }
    }

    /**
     * affichage de phrase de fin fonction de l'issue de la partie
     * @param code combinaison à trouver
     * @param proposal proposition de l'adversaire
     * @param nbTrial nombre d'essai
     * @param nbTrialMax nombre d'essai max
     */
    public void sentenceEnd(List<Integer> code, List<Integer> proposal, int nbTrial, int nbTrialMax){
        if (code.equals(proposal)) logger.info("Perdu! L'ordinateur a trouvé votre combinaison.");
        if(nbTrial == nbTrialMax)logger.info("Bravo, vous avez gagné la partie!");
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


