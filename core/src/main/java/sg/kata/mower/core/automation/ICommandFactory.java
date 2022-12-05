package sg.kata.mower.core.automation;

public interface ICommandFactory {

    String CREATE_LAWN = "CreateLawn";
    String CREATE_MOWER = "CreateMower";
    String TURN_RIGHT = "D";
    String TURN_LEFT = "G";
    String MOVE_FORWARD = "A";

    void mapNewCommand(String cmdName, String cmdClass);

    void removeCommand(String cmdName);

    ICommand createCommand(String cmd, Object[] parameters);
}
