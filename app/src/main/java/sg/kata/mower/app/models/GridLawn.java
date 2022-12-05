package sg.kata.mower.app.models;

import sg.kata.mower.core.MowerException;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.models.IGridLawn;
import sg.kata.mower.core.models.IMower;

import java.util.*;

public class GridLawn implements IGridLawn {
    private int width;
    private int height;

    private ArrayList<IMower> mowers = new ArrayList<>();

    private IMower currentMower;
    public GridLawn(int width, int height){
        this.width = width;
        this.height = height;
    }
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public boolean isCellInGrid(int x, int y) {
        return (x >= 0 && x < width) && (y >= 0 && y < height);
    }

    @Override
    public boolean isCellAvailable(int x, int y) {
        Optional<IMower> result = mowers.stream().filter(m -> m.getX() == x && m.getY() == y).findFirst();
        return  result.isEmpty();
    }

    @Override
    public List<IMower> getMowers() {
        return new ArrayList<IMower>(mowers);
    }

    @Override
    public IMower createAndSetCurrentMower(int x, int y, Direction dir) {
        if (isCellInGrid(x, y) && isCellAvailable(x, y)) {
            IMower mower = new Mower(this, x, y, dir);
            mowers.add(mower);
            setCurrentMower(mower);
            return mower;
        }
        throw new MowerException(String.format("Could not create mower at position %d, %d",x, y));
    }

    @Override
    public IMower currentMower(){
        return currentMower;
    }

    private void setCurrentMower(IMower mower){
        currentMower = mower;
    }

}
