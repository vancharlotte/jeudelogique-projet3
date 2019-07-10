package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static main.Menu.DisplayMenuModeJeu;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static boolean  modeDev;
    private static main.Config config = new main.Config();

/** Le mode développeur peut être lancé à partir de la class Main */
    public static void main(String[] args) {
        if (args[0].equals("dev")) {
            modeDev= true;
        }
        else {
            modeDev= config.isModeDev();
        }
        if (modeDev=true){logger.info("mode développeur activé");}
        DisplayMenuModeJeu();

    }

    public static boolean isModeDev() {
        return modeDev;
    }
}

