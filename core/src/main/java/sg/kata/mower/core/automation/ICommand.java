package sg.kata.mower.core.automation;

import sg.kata.mower.core.models.IEnvironment;

public interface ICommand {
    public String getName();

    public void execute(IEnvironment env);
}
