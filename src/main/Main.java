package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger();
    private static Config config = new Config();
    private static boolean  modeDev;

    /** Le mode développeur peut être lancé à partir de la class Main */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].contains("dev")) {
            modeDev = true;
        } else {
            modeDev = config.isModeDev();
        }
        if (modeDev){logger.info("mode développeur activé");}
        Menu menu = new Menu();
        menu.displayMenuModeJeu();

    }

}

