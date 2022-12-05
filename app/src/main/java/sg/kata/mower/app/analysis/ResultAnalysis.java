package sg.kata.mower.app.analysis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.models.IGridLawn;
import sg.kata.mower.core.models.IMower;
import sg.kata.mower.core.analysis.IAnalysis;

import java.util.ArrayList;

public class ResultAnalysis implements IAnalysis {
    private static final Logger logger = LogManager.getLogger(ResultAnalysis.class);
    private IEnvironment environment;
    public ResultAnalysis(IEnvironment environment){
        this.environment = environment;
    }
    @Override
    public String[] getMowersPositions() {
        logger.info("get mowers positions");
        IGridLawn lawn = environment.getGridLawn();
        if(lawn != null){
            ArrayList<String> positions = new ArrayList<>();
            for(IMower mower : lawn.getMowers()){
                String s = String.format("%d %d %s", mower.getX(), mower.getY(), mower.getDirection());
                positions.add(s);
            }

            return positions.toArray(new String[0]);
        }
        return new String[0];
    }
}
