package test.jeux;

import main.jeux.PlusOuMoins;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PlusOuMoinsTest {

    PlusOuMoins plusoumoins = new PlusOuMoins();

    @Test
    public void testGenerateHint() {

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