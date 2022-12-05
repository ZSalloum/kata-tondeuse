package sg.kata.mower.app.automation.commands;

import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.models.IEnvironment;

public class TurnLeftCommand implements ICommand {
    public String getName(){
        return "TurnLeft";
    }

    public void execute(IEnvironment env){

        env.getGridLawn().currentMower().turnLeft();
    }
}
