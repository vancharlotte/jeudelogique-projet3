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
    public void selectCode() {


    }

    @Test
    public void selectProposal() {

    }

    @Test
    public void selectProposalPOM() {

    }

    @Test
    public void createCodeListTest(){
        Computer computer = new Computer(1,0);
        List <List> hint = new ArrayList();
        List <Object> index0 = new ArrayList<>(Arrays.asList(0));
        hint.add(index0);
        Assert.assertEquals(computer.createCodeList(),hint);
    }

    @Test
    public void chooseCode(){
        Computer computer = new Computer(2,0);
        computer.createCodeList();
        List <Object> test = new ArrayList<>(Arrays.asList(0,0));
        Assert.assertEquals(computer.chooseCode(),test);
        List <Object> test1 = new ArrayList<>(Arrays.asList(1,1));
        Assert.assertNotEquals(computer.chooseCode(),test1);

    }

    @Test
    public void selectProposalMM() {

    }

    @Test
    public void selectFromNumberTest() {
        Computer computer = new Computer(1,0);
        System.out.println(computer.selectFromNumber());
        List <Object> proposaltest = new ArrayList<>(Arrays.asList(0));
        Assert.assertEquals(computer.selectFromNumber(),proposaltest);
        List <Object> proposaltest2 = new ArrayList<>(Arrays.asList(1));
        Assert.assertNotEquals(computer.selectFromNumber(),proposaltest);



    }

    @Test
    public void selectFromPresentNumberTest(){
        Computer computer = new Computer(2,1);
        computer.present.add(1);
        computer.present.add(1);
        System.out.println(computer.present);
        List <Object> proposaltest = new ArrayList<>(Arrays.asList(1,1));
        Assert.assertEquals(computer.selectFromPresentNumber(),proposaltest);
        List <Object> proposaltest2 = new ArrayList<>(Arrays.asList(0,1));
        Assert.assertNotEquals(computer.selectFromPresentNumber(),proposaltest2);



    }
}