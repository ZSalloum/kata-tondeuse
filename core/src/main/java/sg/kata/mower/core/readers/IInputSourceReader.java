package sg.kata.mower.core.readers;

/**
 * Interface to read data from a source
 * The date should be in the format of
 * header and a list of mower info, mower commands
 */
public interface IInputSourceReader {
    /**
     * reads the lawn info from the source.
     * this info allows the creation of the lawn
     * @return string representing the lawn size
     */
    public String readLawn();

    /**
     * reads the mower information from the source
     * this info allows the creation of the mower
     * @return string representing the mower position and direction
     */
    public String readMower();

    /**
     * reads the mower commands from the source
     * this info allows the creation of commands that controls the mower
     * @return string representing the list of commands
     */
    public String readMowerCommands();
}
