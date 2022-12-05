package sg.kata.mower.app.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.app.automation.commands.*;
import sg.kata.mower.app.readers.FileSource;
import sg.kata.mower.core.MowerParsingException;
import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.automation.ICommandFactory;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.parsers.ISourceParser;
import sg.kata.mower.core.readers.IInputSourceReader;


public class SourceParser implements ISourceParser {
    private static final Logger logger = LogManager.getLogger(SourceParser.class);
    private static final String SEPARATOR = " ";
    private static final int HEADER_STEP = -1;
    private static final int MOWER_STEP = 0;
    private static final int FIRST_COMMAND_STEP = 1;
    private static final int NEXT_COMMAND_STEP = 2;
    int currentStep  = HEADER_STEP;

    private IInputSourceReader reader;
    private ICommandFactory commandFactory;
    private String currentCommandSequence;
    private int currentCommandIndex;

    public SourceParser(IInputSourceReader reader, ICommandFactory commandFactory){
        this.reader = reader;
        this.commandFactory = commandFactory;
    }
    @Override
    public ICommand parseNext() {
        ICommand command = null;
        switch (currentStep){
            case HEADER_STEP:
                command = parseGridLawnInfo(reader.readHeader());
                nextStep();
                break;

            case MOWER_STEP:
                command = parseMowerPosition(reader.readMower());
                nextStep();
                break;


            case FIRST_COMMAND_STEP:
                currentCommandSequence = reader.readMowerCommands();
                currentCommandIndex = 0;
                command = parseMowerCommand(currentCommandSequence, currentCommandIndex);
                nextStep();
                break;

            case NEXT_COMMAND_STEP:
                currentCommandIndex++;
                command = parseMowerCommand(currentCommandSequence, currentCommandIndex);
                if(command == NoMoreActionCommand.instance()){
                    nextStep();
                }
                break;
        }

        if(command == null){
            command = EndCommand.instance();
        }
        return command;
    }

    private ICommand parseGridLawnInfo(String str) {
        logger.info("Parsing lawn info: {}", str);
        String[] parts = str.trim().split(SEPARATOR);
        if(parts.length == 2){
            try {
                int w = Integer.parseInt(parts[0]) + 1;
                int h = Integer.parseInt(parts[1]) + 1;
                return commandFactory.createCommand(ICommandFactory.CREATE_LAWN, new Object[]{w, h});
            }catch (Exception e){
                throw new MowerParsingException("Error during parsing", e);
            }
        }
        throw new MowerParsingException(String.format("Invalid number of values when creating GridLawn: %s", str));
    }

    private ICommand parseMowerPosition(String str) {
        logger.info("Parsing mower info: {}", str);
        if(str == null){
            return EndCommand.instance();
        }
        String[] parts = str.trim().split(SEPARATOR);
        if(parts.length == 3){
            try{
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                Direction dir = parseDirection(parts[2]);
                return commandFactory.createCommand(ICommandFactory.CREATE_MOWER, new Object[]{x, y, dir});
            }catch (Exception e){
                throw new MowerParsingException("Error during parsing", e);
            }
        }
        throw new MowerParsingException(String.format("Invalid number of values when creating Mower: %s", str));
    }

    private ICommand parseMowerCommand(String str, int index) {
        if(index >= str.length()){
            return NoMoreActionCommand.instance();
        }
        logger.info("Parsing command: {}", str.charAt(index));

        return commandFactory.createCommand(String.format("%c", str.charAt(index)), new Object[]{});
    }

    private Direction parseDirection(String str){
        logger.info("Parsing direction: {}", str);
        return Direction.fromShortcut(str);
    }

    private void nextStep(){
        currentStep = (currentStep + 1) % 3;
    }
}
