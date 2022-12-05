package sg.kata.mower.app.models;

import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.models.IGridLawn;
import sg.kata.mower.core.models.IMower;

public class Mower implements IMower {
    private int x;
    private int y;
    private Direction dir;

    private IGridLawn grid;

    public Mower(IGridLawn grid, int x, int y, Direction dir){
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Direction getDirection() {
        return dir;
    }

    @Override
    public Direction turnLeft() {
        int len = Direction.values().length;
        int newval = ((dir.getValue() - 1) + len ) % len;
        dir = Direction.fromValue(newval);
        return dir;
    }

    @Override
    public Direction turnRight() {
        int len = Direction.values().length;
        int newval = (dir.getValue() + 1 ) % len;
        dir = Direction.fromValue(newval);
        return dir;
    }

    @Override
    public boolean tryMove() {

        // compute destination cell coordinates
        int newX = x + getHorizontalOffset();
        int newY = y + getVerticalOffset();

        if(isCellAccessible(newX, newY)){
            x = newX;
            y = newY;
            return true;
        }
        return false;
    }

    private int getHorizontalOffset(){
        return dir.getOffsetX();
    }

    private int getVerticalOffset(){
        return dir.getOffsetY();
    }

    private boolean isCellAccessible(int x, int y){
        return grid.isCellInGrid(x , y) && grid.isCellAvailable(x , y);
    }
}
