package sg.kata.mower.output;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sg.kata.mower.app.parsers.SourceParser;
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
        for(String s : analysis.getMowersPositions()){
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }
}
