package main.joueur;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {

    protected static final Logger logger = LogManager.getLogger();
    public List<Integer> code = new ArrayList<>();
    public List<Integer> proposal = new ArrayList<>();
     int sizeCode;
     int number;

    public Player(int sizeCode, int number) {
        this.sizeCode = sizeCode;
        this.number = number;

    }

    public abstract void selectCode();

}
