package main;

import main.jeux.Game;
import main.jeux.Mastermind;
import main.jeux.PlusOuMoins;
import main.modedejeu.Challengeur;
import main.modedejeu.Defenseur;
import main.modedejeu.Duel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MenuTest {

    @Test
    public void initJeuTest() {
        Game game1 = new PlusOuMoins();
        Assert.assertSame(Menu.initJeu(1).getClass(), game1.getClass());
        Game game2 = new Mastermind();
        Assert.assertSame(Menu.initJeu(2).getClass(), game2.getClass());
        Assert.assertNotSame(Menu.initJeu(3).getClass(), game2.getClass());

    }
    @Test
    public void initModeJeuTest() {
        Game game = new Game();
        Challengeur gameplay1 = new Challengeur(game);
        Assert.assertSame(Menu.initModeJeu(1).getClass(), gameplay1.getClass());
        Defenseur gameplay2 = new Defenseur(game);
        Assert.assertSame(Menu.initModeJeu(2).getClass(), gameplay2.getClass());
        Duel gameplay3 = new Duel(game);
        Assert.assertSame(Menu.initModeJeu(3).getClass(), gameplay3.getClass());
        Assert.assertNotSame(Menu.initModeJeu(4).getClass(), gameplay3.getClass());

    }
}