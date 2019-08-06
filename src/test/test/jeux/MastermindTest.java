package test.jeux;

import main.jeux.Mastermind;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MastermindTest {

    Mastermind mastermind = new Mastermind();
    @Test
    public void testGenerateHint() {
        List<Integer> code = new ArrayList<>(Arrays.asList(1,2));
        List<Integer> proposal = new ArrayList<>(Arrays.asList(1,1));
        List<Integer> proposal1 = new ArrayList<>(Arrays.asList(2,1));
        List<Integer> proposal2 = new ArrayList<>(Arrays.asList(1,2));
        List<Integer> proposal3 = new ArrayList<>(Arrays.asList(3,1));
        Assert.assertEquals(mastermind.generateHint(code,proposal),1 + " chiffre(s) bien placé(s), "+ 0 + " chiffre(s) mal placé(s).");
        Assert.assertEquals(mastermind.generateHint(code,proposal1),0 + " chiffre(s) bien placé(s), "+ 2 + " chiffre(s) mal placé(s).");
        Assert.assertEquals(mastermind.generateHint(code,proposal2),2 + " chiffre(s) bien placé(s), "+ 0 + " chiffre(s) mal placé(s).");
        Assert.assertEquals(mastermind.generateHint(code,proposal3),0 + " chiffre(s) bien placé(s), "+ 1 + " chiffre(s) mal placé(s).");

    }
}