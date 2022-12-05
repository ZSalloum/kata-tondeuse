package sg.kata.mower.app.automation.commands;

import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.models.IEnvironment;

public class NoMoreActionCommand implements ICommand {

    private static ICommand instance = new NoMoreActionCommand();
    private NoMoreActionCommand(){
    }

    public static ICommand instance(){
        return instance;
    }
    @Override
    public String getName() {
        return "NoMoreAction";
    }

    @Override
    public void execute(IEnvironment env) {

    }
}
