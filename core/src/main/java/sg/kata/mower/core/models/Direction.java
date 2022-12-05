package sg.kata.mower.core.models;

import sg.kata.mower.core.MowerException;

/**
 * Enum that represent the direction of a mower.
 * There are four dimensions: North, East, South and West
 * Each direction has a value, a shortcut representation and x, y offsets
 */
public enum Direction{
    North(0, "N", 0, 1),
    East(1, "E", 1, 0),
    South(2, "S", 0, -1),
    West(3, "W", -1, 0);


    private int val;
    private String shortcut;

    private int offsetX;
    private int offsetY;

    Direction(int val, String shortcut, int offsetX, int offsetY) {
        this.val = val;
        this.shortcut = shortcut;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public int getValue(){
        return val;
    }

    public int getOffsetX(){
        return offsetX;
    }

    public int getOffsetY(){
        return offsetY;
    }

    public String getShortcut(){
        return shortcut;
    }
    @Override
    public String toString(){
        return getShortcut();
    }

    public static Direction fromValue(int val){
        for (Direction d : Direction.values()) {
            if (d.getValue() == val) {
                return d;
            }
        }
        throw new MowerException(String.format("Can't convert %d to enum Direction", val));
    }

    public static Direction fromShortcut(String shortcut){
        for (Direction d : Direction.values()) {
            if (d.getShortcut().equals(shortcut)) {
                return d;
            }
        }
        throw new MowerException(String.format("Can't convert '%s' to enum Direction", shortcut));
    }
}
