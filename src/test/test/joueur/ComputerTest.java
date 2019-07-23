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

    }

    @Test
    public void selectProposalMM() {

    }

    @Test
    public void selectFromNumberTest() {

    }

    @Test
    public void selectFromPresentNumberTest(){

    }
}