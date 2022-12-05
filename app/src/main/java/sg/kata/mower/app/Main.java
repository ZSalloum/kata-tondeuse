package sg.kata.mower.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        logger.info("Starting application.");

        String filepath = args[0];
        Starter starter = new Starter();
        starter.start(filepath);

        logger.info("-- end --");
    }
}