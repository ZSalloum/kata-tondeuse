package sg.kata.mower.core.automation;
import java.io.Reader;

/**
 * Interface of a factory to create commands
 */
public interface ICommandFactory {

    /**
     * String constant that represents command for creation of a lawn
     */
    String CREATE_LAWN = "CreateLawn";
    /**
     * String constant that represents command for creation of a mower
     */
    String CREATE_MOWER = "CreateMower";
    /**
     * String constant that represents command for turning mower to the right
     */
    String TURN_RIGHT = "D";
    /**
     * String constant that represents command for turning mower to the left
     */
    String TURN_LEFT = "G";
    /**
     * String constant that represents command for moving mower forward
     */
    String MOVE_FORWARD = "A";

    /**
     * Maps a command name to a command class
     * @param cmdName name of the command
     * @param cmdClass full class name to be instantiated when the command name is encountered
     */
    void mapNewCommand(String cmdName, String cmdClass);

    /**
     * Remove command and its mapping
     * @param cmdName name of the command to be removed
     */
    void removeCommand(String cmdName);

    /**
     * Clears the command mapping inside the CommandFactory
     */
    void clearMapping();


    /**
     * Create an instance of ICommand based on the command name
     * @param cmdName command name
     * @param parameters parameters to be passed to the ICommand instance
     * @return ICommand instance
     */
    ICommand createCommand(String cmdName, Object[] parameters);

    /**
     * Load custom command mapping from a reader such string or file, etc...
     * @param reader source of the mapping
     */
    void loadCustomCommandsMapping(Reader reader);
}
