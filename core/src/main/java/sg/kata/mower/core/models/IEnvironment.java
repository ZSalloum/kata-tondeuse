package sg.kata.mower.core.models;

/**
 * Interface that represents the environemnt in which the program is executing
 */
public interface IEnvironment {

    /**
     * gets the lawn in the environment
     * @return the lawn
     */
    public IGridLawn getGridLawn();

    /**
     * Sets the lawn in the environment
     * @param gridLawn lawn to be set
     */
    public void setGridLaw(IGridLawn gridLawn);
}
