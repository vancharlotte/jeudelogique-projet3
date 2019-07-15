package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Config {
    private int sizeCode;
    private int nbTrialMax;
    private int number;
    private boolean modeDev;
    private ResourceBundle bundle = ResourceBundle.getBundle("resources.config");
    private static final Logger logger = LogManager.getLogger();


    /** configuration du jeu
     * @throws MissingResourceException*/
    public Config() {
        try {
            sizeCode = Integer.parseInt(bundle.getString("sizeCode"));
            nbTrialMax = Integer.parseInt(bundle.getString("nbTrialMax"));
            number = Integer.parseInt(bundle.getString("number"));
            modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));
        }
        catch (MissingResourceException e ){
        logger.fatal("fichier de configuration introuvable.");
        }
    }

    public int getSizeCode() {
        return sizeCode;
    }

    public int getNbTrialMax() {
        return nbTrialMax;
    }

    public int getNumber() {
        return  number;
    }

    public boolean isModeDev() {
        return modeDev;
    }


}
