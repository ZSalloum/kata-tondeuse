package sg.kata.mower.core.analysis;

import sg.kata.mower.core.models.Position;

/**
 * defines the a set of methods to compute analysis and statistics on the result of execution
 * of mowers in the lawn
 */
public interface IAnalysis {
    /**
     * gets the final positions of mowers a string
     * @return array of Position
     */
    Position[] getMowersPositions();
}
