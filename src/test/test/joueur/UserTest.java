package test.main.joueur;

import main.Config;
import main.joueur.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserTest {

    private User user = new User();
    Config config = new Config();

    @Before
    public void init(){
        config.setNumber(5);
        config.setSizeCode(4);
    }

    @Test
    public  void chosenCombiIsCorrectTest() {
        /*combi m^me longueur que longueur fichier config sinon => false*/
        /*selectedNumber false => false*/
        /*n'utilise pas de lettres que des chiffres => false*/
        Assert.assertTrue(user.chosenCombiIsCorrect("1234"));
        Assert.assertFalse(user.chosenCombiIsCorrect("12345"));
        Assert.assertFalse(user.chosenCombiIsCorrect("6789"));
        Assert.assertFalse(user.chosenCombiIsCorrect("abcd"));
    }

    @Test
    public void selectedNumberTest(){
        Assert.assertTrue(user.selectedNumber("0145"));
        Assert.assertFalse(user.selectedNumber("6789"));
    }

    @Test
    public void goodSizeCodeTest() {
        Assert.assertTrue(user.goodSizeCode("1234"));
        Assert.assertFalse(user.goodSizeCode("12345"));
    }

    @Test
    public void hintIsCorrectPOMTest() {
        /*input même longueur que dans fichier de config*/
        /*hint et input m^eme valeur*/
        List<Object> hint = new ArrayList<>();
        Assert.assertFalse(user.hintIsCorrectPOM(hint, "1234"));
    }

    @Test
    public void hintIsCorrectMMTest(){
        /*hint1 = input1
          hint2 = input2 */
    }


}