package sg.kata.mower.app.analysis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.models.IGridLawn;
import sg.kata.mower.core.models.IMower;
import sg.kata.mower.core.analysis.IAnalysis;
import sg.kata.mower.core.models.Position;

import java.util.ArrayList;

public class ResultAnalysis implements IAnalysis {
    private static final Logger logger = LogManager.getLogger(ResultAnalysis.class);
    private IEnvironment environment;
    public ResultAnalysis(IEnvironment environment){
        this.environment = environment;
    }
    @Override
    public Position[] getMowersPositions() {
        logger.info("get mowers positions");
        IGridLawn lawn = environment.getGridLawn();
        if(lawn != null){
            ArrayList<Position> positions = new ArrayList<>();
            for(IMower mower : lawn.getMowers()){
                Position pos = new Position(mower.getX(), mower.getY(), mower.getDirection());
                positions.add(pos);
            }

            return positions.toArray(new Position[0]);
        }
        return new Position[0];
    }
}
