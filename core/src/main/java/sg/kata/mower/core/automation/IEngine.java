package sg.kata.mower.core.automation;

import sg.kata.mower.core.parsers.ISourceParser;
import sg.kata.mower.core.readers.IInputSourceReader;

/**
 * Interface of the Engine class that executes the series of commands sent by the parser
 */
public interface IEngine {

    /**
     * Runs the engine and execute the series of commands sequentially
     */
    void run();

}
