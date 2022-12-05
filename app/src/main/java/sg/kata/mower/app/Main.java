package sg.kata.mower.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.trace("Entering application.");
        String filepath = args[0];
        Starter starter = new Starter();
        starter.start(filepath);

        System.out.println("-- end --");
    }
}