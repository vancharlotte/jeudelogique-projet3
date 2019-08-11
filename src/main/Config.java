package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.Properties;

public class Config {
    private int sizeCode;
    private int nbTrialMax;
    private int number;
    private boolean modeDev;
    private static final Logger logger = LogManager.getLogger();

    /** configuration du jeu
     * @throws NumberFormatException fichier de configuration : String à la place de int
     * @throws MissingResourceException fichier de configuration introuvable
     **/
    public Config() {
        Properties props = new Properties();
        try {
            if (Paths.get("./config.properties").toFile().exists()){
                File file = new File("./config.properties");
                FileInputStream fis = new FileInputStream(file);
                props.load(fis);
            }
            else{
                File file = new File("src/resources/config.properties");
                FileInputStream fis = new FileInputStream(file);
                props.load(fis);
            }
            sizeCode = Integer.parseInt(props.getProperty("sizeCode"));
            nbTrialMax = Integer.parseInt(props.getProperty("nbTrialMax"));
            number = Integer.parseInt(props.getProperty("number"));
            modeDev = Boolean.parseBoolean(props.getProperty("modeDev"));

            if(sizeCode<2||sizeCode>9){sizeCode=4;}
            if(nbTrialMax<2||nbTrialMax>20){nbTrialMax=10;}
            if(number<2||number>9){number=5;}
        }
        catch (NumberFormatException |  FileNotFoundException e ){
            sizeCode = 6;
            nbTrialMax = 10;
            number = 6;
            modeDev = false;
            logger.info("missing resource exception");
            logger.debug("Problème avec le fichier de configuration. Utilisation des valeurs pas défaut");

        } catch (IOException e) {
            e.printStackTrace();
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
