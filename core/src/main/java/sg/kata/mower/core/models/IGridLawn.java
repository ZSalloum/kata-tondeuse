package sg.kata.mower.core.models;

import java.util.List;

public interface IGridLawn {

    public int getWidth();

    public int getHeight();

    public boolean isCellInGrid(int x, int y);

    public boolean isCellAvailable(int x, int y);

    List<IMower> getMowers();

    public IMower createAndSetCurrentMower(int x, int y, Direction dir);

    public IMower currentMower();

}
