package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Config {
    private int sizeCode;
    private int nbTrialMax;
    private int number;
    private boolean modeDev;
    private ResourceBundle bundle;
    private static final Logger logger = LogManager.getLogger();

    /** configuration du jeu
     * @throws NumberFormatException fichier de configuration : String à la place de int
     * @throws MissingResourceException fichier de configuration introuvable
     **/
    public Config() {
        try {
            if (Paths.get("config.properties").toFile().exists()){
                ClassLoader loader = new URLClassLoader(new URL[]{Paths.get("config.properties").toUri().toURL()});
                bundle = ResourceBundle.getBundle("resources.config", Locale.getDefault(),loader);
            }
            else{
                bundle = ResourceBundle.getBundle("resources.config");
            }
            sizeCode = Integer.parseInt(bundle.getString("sizeCode"));
            nbTrialMax = Integer.parseInt(bundle.getString("nbTrialMax"));
            number = Integer.parseInt(bundle.getString("number"));
            modeDev = Boolean.parseBoolean(bundle.getString("modeDev"));
            if(sizeCode<2||sizeCode>9){sizeCode=4;}
            if(nbTrialMax<2||nbTrialMax>20){nbTrialMax=10;}
            if(number<2||number>9){number=5;}
        }
        catch (NumberFormatException | MissingResourceException | MalformedURLException e ){
            sizeCode = 4;
            nbTrialMax = 10;
            number = 5;
            modeDev = false;
            logger.debug("Problème avec le fichier de  configuration. Utilisation des valeurs pas défaut");

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

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }


}
