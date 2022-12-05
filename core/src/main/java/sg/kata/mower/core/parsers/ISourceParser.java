package sg.kata.mower.core.parsers;

import sg.kata.mower.core.automation.ICommand;

public interface ISourceParser {

    public ICommand parseNext();
}
