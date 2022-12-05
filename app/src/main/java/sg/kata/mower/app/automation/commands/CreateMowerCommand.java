package sg.kata.mower.app.automation.commands;

import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.models.IGridLawn;


public class CreateMowerCommand implements ICommand {
    private int x;
    private int y;
    private Direction dir;
    public CreateMowerCommand(int x, int y, Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDir() {
        return dir;
    }
    @Override
    public String getName() {
        return "CreateMower";
    }

    @Override
    public void execute(IEnvironment env) {
        IGridLawn gridLawn = env.getGridLawn();
        gridLawn.createAndSetCurrentMower(x, y, dir);
    }
}
