package sg.kata.mower.core.readers;

public interface IInputSourceReader {
    public String readHeader();

    public String readMower();

    public String readMowerCommands();
}
