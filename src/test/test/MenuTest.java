package test.test;

import main.Menu;
import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;
import main.modedejeu.Challengeur;
import main.modedejeu.Defenseur;
import main.modedejeu.Duel;
import org.junit.Assert;
import org.junit.Test;

public class MenuTest {

    Menu menu = new Menu(4,5,10);

    @Test
    public void testInitJeu() {
        Game game1 = new PlusOuMoins();
        Assert.assertSame(menu.initJeu(1).getClass(), game1.getClass());
        Game game2 = new Mastermind();
        Assert.assertSame(menu.initJeu(2).getClass(), game2.getClass());
        Assert.assertNotSame(menu.initJeu(3).getClass(), game2.getClass());

    }
    @Test
    public void testInitModeJeu() {
        Game game = new PlusOuMoins();
        Challengeur gameplay1 = new Challengeur(game,4,5,10);
        Assert.assertSame(menu.initModeJeu(1).getClass(), gameplay1.getClass());
        Defenseur gameplay2 = new Defenseur(game,4,5,10);
        Assert.assertSame(menu.initModeJeu(2).getClass(), gameplay2.getClass());
        Duel gameplay3 = new Duel(game, 4,5,10);
        Assert.assertSame(menu.initModeJeu(3).getClass(), gameplay3.getClass());
        Assert.assertNotSame(menu.initModeJeu(4).getClass(), gameplay3.getClass());

    }
}