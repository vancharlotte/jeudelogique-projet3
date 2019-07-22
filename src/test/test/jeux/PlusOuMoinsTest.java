package main.jeux;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PlusOuMoinsTest {

    PlusOuMoins plusoumoins = new PlusOuMoins();

    @Test
    public void generateHintTest() {

        List<Integer> code = new ArrayList<>();
        code.add(2);
        List<Integer> proposal = new ArrayList<>();
        proposal.add(2);
        List<Integer> proposal1 = new ArrayList<>();
        proposal.add(1);
        List<Integer> proposal2 = new ArrayList<>();
        proposal.add(3);
        Assert.assertEquals(plusoumoins.generateHint(code,proposal),"[-]");
        Assert.assertEquals(plusoumoins.generateHint(code,proposal1),"[=]");
        Assert.assertEquals(plusoumoins.generateHint(code,proposal2),"[+]");
    }
}