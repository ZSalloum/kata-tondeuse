package sg.kata.mower.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.app.analysis.ResultAnalysis;
import sg.kata.mower.app.automation.CommandFactory;
import sg.kata.mower.app.automation.Engine;
import sg.kata.mower.app.models.Environment;
import sg.kata.mower.app.parsers.SourceParser;
import sg.kata.mower.app.readers.SourceReader;
import sg.kata.mower.app.output.SimpleDisplay;
import sg.kata.mower.core.analysis.IAnalysis;
import sg.kata.mower.core.automation.ICommandFactory;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.output.IDisplay;
import sg.kata.mower.core.parsers.ISourceParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Scanner;

/**
 * Starter class that configure and setup all the components to be ready for execution
 */
public class Starter {
    private static final Logger logger = LogManager.getLogger(Starter.class);
    public void start(String filepath){
        Reader freader = null;
        try {
            freader = open(filepath);
            SourceReader reader = new SourceReader(freader);
            ICommandFactory commandFactory = new CommandFactory();

            ISourceParser parser = new SourceParser(reader, commandFactory);
            IEnvironment env = new Environment();

            Engine engine = new Engine(parser, env);
            engine.run();

            IAnalysis output = new ResultAnalysis(env);
            IDisplay display = new SimpleDisplay(output);
            display.showMowersPosition();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex);
        }
        finally {
            close(freader);
        }
    }

    private Reader open(String filepath) throws FileNotFoundException {
        logger.info("Opening file : {}", filepath);
        return new FileReader(filepath);
    }

    private void close(Reader reader){
        try{
            if(reader != null) {
                reader.close();
            }
        }catch (Exception ex) {
            logger.error(ex);
        }
    }

}
