package sg.kata.mower.core.models;

import java.util.List;

/**
 * Interface that represents the lawn (in the form of a grid) in which the mowers will work
 */
public interface IGridLawn {

    /**
     * gets the width of the lawn
     * @return width
     */
    public int getWidth();

    /**
     * gets the height of the lawn
     * @return height
     */
    public int getHeight();

    /**
     * checks if the coordinates are in the grid
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if in the grid, false otherwise
     */
    public boolean isCellInGrid(int x, int y);

    /**
     * checks if the cell at the given coordinates is empty
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if empty, false otherwise
     */
    public boolean isCellAvailable(int x, int y);

    /**
     * gets the list of mowers in the lawn
     * @return List of IMower
     */
    List<IMower> getMowers();

    /**
     * creates a mower in the lawn and set it at the current one
     * @param x x-coordinate
     * @param y y-coordinate
     * @param dir direction of the mower
     * @return the new mower
     */
    public IMower createAndSetCurrentMower(int x, int y, Direction dir);

    /**
     * gets the current mower in the lawn
     * @return mower
     */
    public IMower currentMower();

}
