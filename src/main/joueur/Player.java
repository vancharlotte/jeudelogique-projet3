package main.joueur;


import main.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Player {

    protected Config config = new Config();
    protected static final Logger logger = LogManager.getLogger();
    public abstract void selectCode();

}
