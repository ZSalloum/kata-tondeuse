package sg.kata.mower.app.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.core.models.Position;
import sg.kata.mower.core.output.IDisplay;
import sg.kata.mower.core.analysis.IAnalysis;

public class SimpleDisplay implements IDisplay {
    private static final Logger logger = LogManager.getLogger(SimpleDisplay.class);
    private IAnalysis analysis;
    public SimpleDisplay(IAnalysis analysis){
        this.analysis = analysis;
    }
    @Override
    public void showMowersPosition() {
        logger.info("Show mowers positions");
        for(Position p : analysis.getMowersPositions()){
            String s = String.format("%d %d %s", p.getX(), p.getY(), p.getDirection());
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }
}
