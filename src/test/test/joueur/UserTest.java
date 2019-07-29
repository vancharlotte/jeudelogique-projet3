package main;

import main.joueur.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class UserTest {

    User user = new User(4,5);

    @Before
    public void init(){
    }

    @Test
    public  void testChosenCombiIsCorrect() {
        Assert.assertFalse(user.chosenCombiIsCorrect("abcd"));
    }


    @Test
    public void testSelectedNumber(){
        Assert.assertTrue(user.selectedNumber("0145"));
        Assert.assertFalse(user.selectedNumber("6789"));
    }

    @Test
    public void testGoodSizeCode() {
        Assert.assertTrue(user.goodSizeCode("123"));
        Assert.assertFalse(user.goodSizeCode("1234"));
    }



    @Test /* ?*/
    public void testHintIsCorrectPOM() {
        List <Object> hint = new ArrayList<>(Arrays.asList('+','+','+','+'));
        Assert.assertTrue(user.hintIsCorrectPOM(hint,"++++"));
        Assert.assertFalse(user.hintIsCorrectPOM(hint,"-+-+-"));

    }

    @Test
    public void testHintIsCorrectMM(){
        List <Object> hint = new ArrayList<>(Arrays.asList(1,0));
        Assert.assertTrue(user.hintIsCorrectMM(hint,1, 0));
        Assert.assertFalse(user.hintIsCorrectMM(hint, 1,2));

    }


}