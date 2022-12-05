package sg.kata.mower.core.models;

/**
 * Interface that represents the mower
 */
public interface IMower {
    /**
     * gets the x-coordinate
     * @return x
     */
    public int getX();
    /**
     * gets the y-coordinate
     * @return y
     */
    public int getY();

    /**
     * sets the x-coordinate of the mower
     * @param  x the new x-coordinate
     */
    public void setX(int x);

    /**
     * sets the y-coordinate of the mower
     * @param y the new y-coordinate
     */
    public void setY(int y);

    /**
     * gets the direction of the mower
     * @return  direction
     */
    public Direction getDirection();

    /**
     * instructs the mower to turn left.
     * @return the new direction
     */
    public Direction turnLeft();

    /**
     * instructs the mower to turn right.
     * @return the new direction
     */
    public Direction turnRight();

    /**
     * try to move the mower forward.
     * if the attempt is successful the mower is mover and the method returns true
     * if the attempt failed the mower remains at its place and the method returns false
     * @return true if mower moved, false otherwise
     */
    public boolean tryMove();
}
