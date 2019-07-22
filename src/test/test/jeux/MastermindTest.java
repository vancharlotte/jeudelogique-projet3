package main.jeux;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MastermindTest {

    Mastermind mastermind = new Mastermind();
    @Test
    public void generateHintTest() {
        List<Integer> code = new ArrayList<>();
        code.add(1);
        code.add(2);
        List<Integer> proposal = new ArrayList<>();
        proposal.add(1);
        proposal.add(1);
        List<Integer> proposal1 = new ArrayList<>();
        proposal1.add(2);
        proposal1.add(1);
        List<Integer> proposal2 = new ArrayList<>();
        proposal2.add(1);
        proposal2.add(2);
        List<Integer> proposal3 = new ArrayList<>();
        proposal3.add(3);
        proposal3.add(1);
        Assert.assertEquals(mastermind.generateHint(code,proposal),1 + " chiffre(s) bien placé(s), "+ 0 + " chiffre(s) mal placé(s).");
        Assert.assertEquals(mastermind.generateHint(code,proposal1),0 + " chiffre(s) bien placé(s), "+ 2 + " chiffre(s) mal placé(s).");
        Assert.assertEquals(mastermind.generateHint(code,proposal2),2 + " chiffre(s) bien placé(s), "+ 0 + " chiffre(s) mal placé(s).");
        Assert.assertEquals(mastermind.generateHint(code,proposal3),0 + " chiffre(s) bien placé(s), "+ 1 + " chiffre(s) mal placé(s).");

    }
}