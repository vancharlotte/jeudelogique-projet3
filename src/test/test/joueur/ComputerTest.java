package main.joueur;

import main.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ComputerTest {


    @Test
    public void testSelectCode() {


    }

    @Test
    public void testSelectProposal() {

    }

    @Test
    public void testSelectProposalPOM() {

    }

    @Test
    public void testCreateCodeListTest(){
        Computer computer = new Computer(1,0);
        List <List> hint = new ArrayList<>();
        List <Object> index0 = new ArrayList<>();
        index0.add(0);
        hint.add(index0);
        Assert.assertEquals(computer.createCodeList(),hint);
    }

    @Test
    public void testChooseCode(){
        Computer computer = new Computer(2,0);
        computer.createCodeList();
        List <Object> test = new ArrayList<>(Arrays.asList(0,0));
        Assert.assertEquals(computer.chooseCode(),test);
        List <Object> test1 = new ArrayList<>(Arrays.asList(1,1));
        Assert.assertNotEquals(computer.chooseCode(),test1);

    }

    @Test
    public void testSelectProposalMM() {

    }

    @Test
    public void testSelectFromNumber() {
        Computer computer = new Computer(1,0);
        System.out.println(computer.selectFromNumber());
        List <Object> proposaltest = new ArrayList<>();
        proposaltest.add(0);
        Assert.assertEquals(computer.selectFromNumber(),proposaltest);
        List <Object> proposaltest2 = new ArrayList<>();
        proposaltest2.add(1);
        Assert.assertNotEquals(computer.selectFromNumber(),proposaltest2);

    }

    @Test
    public void testSelectFromPresentNumber(){
        Computer computer = new Computer(2,1);
        computer.present.add(1);
        computer.present.add(1);
        List <Object> proposaltest = new ArrayList<>(Arrays.asList(1,1));
        Assert.assertEquals(computer.selectFromPresentNumber(),proposaltest);
        List <Object> proposaltest2 = new ArrayList<>(Arrays.asList(1,2));
        Assert.assertNotEquals(computer.selectFromPresentNumber(),proposaltest2);



    }
}