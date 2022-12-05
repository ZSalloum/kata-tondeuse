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

public class Starter {
    private static final Logger logger = LogManager.getLogger(Starter.class);
    public void start(String filepath){
        FileSource reader = new FileSource(filepath);
        try {
            reader.open();
            ICommandFactory commandFactory = new CommandFactory();

            ISourceParser parser = new SourceParser(reader, commandFactory);
            IEnvironment env = new Environment();

            Engine engine = new Engine(parser, env);
            engine.run();

            IAnalysis output = new ResultAnalysis(env);
            IDisplay display = new sg.kata.mower.output.SimpleDisplay(output);
            display.showMowersPosition();

        }catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex);
        }
        finally {
            reader.close();
        }
    }
}
