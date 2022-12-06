package sg.kata.mower.app.automation;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.core.MowerException;
import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.automation.ICommandFactory;

import sg.kata.mower.app.automation.commands.*;

import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory implements ICommandFactory {
    private static final Logger logger = LogManager.getLogger(CommandFactory.class);
    private HashMap<String, String> commandMap = new HashMap<>();

    public CommandFactory(){
        populatePrebuiltCommandMap();
    }

    @Override
    public void mapNewCommand(String cmdName, String cmdClass){
        commandMap.put(cmdName, cmdClass);
    }

    @Override
    public void removeCommand(String cmdName){
        commandMap.remove(cmdName);
    }

    @Override
    public void clearMapping(){
        commandMap.clear();
    }

    @Override
    public ICommand createCommand(String cmd, Object[] parameters) {
        if (commandMap.containsKey(cmd)) {
            try {
                String className = commandMap.get(cmd);
                Class<?> clazz = Class.forName(className);
                Constructor<?>[] constructorstructors = clazz.getConstructors();
                if(constructorstructors.length != 1){
                    throw new MowerException(String.format("Command must have only one constructor, %d for class '%s'", constructorstructors.length, className));
                }
                Object instance = constructorstructors[0].newInstance(parameters);
                return (ICommand) instance;
            } catch (Exception ex) {
                throw new MowerException(String.format("Failed to create command for '%s'", cmd), ex);
            }
        }
        throw new MowerException(String.format("No command found for '%s'", cmd));
    }


    @Override
    public void loadCustomCommandsMapping(Reader reader) {
        try {
            logger.info("Loading custom commands");
            Gson gson = new Gson();
            Map map = gson.fromJson(reader, Map.class);
            if(map != null) {
                logger.info("{} custom commands loaded", map.size());
                for (Object key : map.keySet()) {
                    mapNewCommand(key.toString(), map.get(key).toString());
                }
            }
        }catch (Exception ex){
            logger.error("Error loading custom commands'");
            logger.error(ex);
        }
    }

    private void populatePrebuiltCommandMap(){
        commandMap.clear();
        mapNewCommand(CREATE_LAWN, CreateGridLawnCommand.class.getName());
        mapNewCommand(CREATE_MOWER, CreateMowerCommand.class.getName());
        mapNewCommand(TURN_RIGHT, TurnRightCommand.class.getName());
        mapNewCommand(TURN_LEFT, TurnLeftCommand.class.getName());
        mapNewCommand(MOVE_FORWARD, ForwardCommand.class.getName());
    }
}
