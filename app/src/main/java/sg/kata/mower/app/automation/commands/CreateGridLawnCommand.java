package sg.kata.mower.app.automation.commands;

import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.app.models.GridLawn;

public class CreateGridLawnCommand  implements ICommand {

    private int width;
    private int height;
    public CreateGridLawnCommand(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
    @Override
    public String getName() {
        return "CreateGridLawn";
    }

    @Override
    public void execute(IEnvironment env) {
        env.setGridLaw(new GridLawn(width, height));
    }
}
