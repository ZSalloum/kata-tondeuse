package sg.kata.mower.core.automation;

import sg.kata.mower.core.models.IEnvironment;

/**
 * Defines the command that is used to execute in the environment
 */
public interface ICommand {

    /**
     * gets the name of the command
     * @return name as string
     */
    public String getName();

    /**
     * Execute the command in the environment
     * @param env environment in which the lawn and mowers
     */
    public void execute(IEnvironment env);
}
