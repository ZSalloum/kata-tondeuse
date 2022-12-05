package sg.kata.mower.core.models;

public interface IMower {
    public int getX();
    public int getY();

    public void setX(int x);

    public void setY(int y);

    public Direction getDirection();

    public Direction turnLeft();

    public Direction turnRight();

    public boolean tryMove();
}
