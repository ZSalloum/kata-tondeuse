package sg.kata.mower.app.automation.commands;

import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.models.IEnvironment;

public class EndCommand implements ICommand {
    private static ICommand instance = new EndCommand();
    private EndCommand(){

    }

    public static ICommand instance(){
        return instance;
    }

    @Override
    public String getName() {
        return "END";
    }

    @Override
    public void execute(IEnvironment env) {

    }
}
