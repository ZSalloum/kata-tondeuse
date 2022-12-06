package sg.kata.mower.app.automation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.app.automation.commands.EndCommand;
import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.automation.IEngine;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.parsers.ISourceParser;

public class Engine implements IEngine {
    private static final Logger logger = LogManager.getLogger(Engine.class);
    private ISourceParser sourceParser;
    private IEnvironment environment;

    public Engine(ISourceParser sourceParser, IEnvironment environment){
        this.sourceParser = sourceParser;
        this.environment = environment;
    }
    public void run(){
        ICommand command;
        while ((command = sourceParser.parseNext()) != EndCommand.instance()){
            logger.info("Execute command: {}", command.getName());
            command.execute(environment);
        }
    }
}
