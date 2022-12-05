package sg.kata.mower.app.automation;

import sg.kata.mower.core.MowerException;
import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.automation.ICommandFactory;

import sg.kata.mower.app.automation.commands.*;

import java.lang.reflect.Constructor;
import java.util.HashMap;

public class CommandFactory implements ICommandFactory {

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


    private void populatePrebuiltCommandMap(){
        commandMap.clear();
        mapNewCommand(CREATE_LAWN, CreateGridLawnCommand.class.getName());
        mapNewCommand(CREATE_MOWER, CreateMowerCommand.class.getName());
        mapNewCommand(TURN_RIGHT, TurnRightCommand.class.getName());
        mapNewCommand(TURN_LEFT, TurnLeftCommand.class.getName());
        mapNewCommand(MOVE_FORWARD, ForwardCommand.class.getName());
    }
}
