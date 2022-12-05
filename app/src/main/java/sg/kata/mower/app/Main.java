package sg.kata.mower.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.app.analysis.ResultAnalysis;
import sg.kata.mower.app.automation.CommandFactory;
import sg.kata.mower.app.automation.Engine;
import sg.kata.mower.app.models.Environment;
import sg.kata.mower.app.parsers.SourceParser;
import sg.kata.mower.app.readers.FileSource;
import sg.kata.mower.core.analysis.IAnalysis;
import sg.kata.mower.core.automation.ICommandFactory;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.output.IDisplay;
import sg.kata.mower.core.parsers.ISourceParser;

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