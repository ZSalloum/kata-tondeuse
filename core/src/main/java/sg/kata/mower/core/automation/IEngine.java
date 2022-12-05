package sg.kata.mower.core.automation;

import sg.kata.mower.core.parsers.ISourceParser;
import sg.kata.mower.core.readers.IInputSourceReader;

public interface IEngine {
    public void setInputSource(IInputSourceReader src);
    public void  setSourceParser(ISourceParser parser);

    public void Run();

}
