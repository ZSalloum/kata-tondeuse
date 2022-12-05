package sg.kata.mower.app.automation.commands;

import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.models.IEnvironment;

public class TurnRightCommand implements ICommand {
    public String getName(){
        return "TurnRight";
    }

    public void execute(IEnvironment env){
        env.getGridLawn().currentMower().turnRight();
    }
}