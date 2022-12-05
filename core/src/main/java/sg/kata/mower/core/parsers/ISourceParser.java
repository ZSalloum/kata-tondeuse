package sg.kata.mower.core.parsers;

import sg.kata.mower.core.automation.ICommand;

/**
 * Interface to parse data coming from a source
 */
public interface ISourceParser {

    /**
     * parses the next input.
     * It reads from the source and tries to converts to an ICommand instance
     * @return command
     */
    ICommand parseNext();
}
