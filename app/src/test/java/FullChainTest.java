import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.kata.mower.app.analysis.ResultAnalysis;
import sg.kata.mower.app.automation.CommandFactory;
import sg.kata.mower.app.automation.Engine;
import sg.kata.mower.app.models.Environment;
import sg.kata.mower.app.output.SimpleDisplay;
import sg.kata.mower.app.parsers.SourceParser;
import sg.kata.mower.app.readers.SourceReader;
import sg.kata.mower.core.analysis.IAnalysis;
import sg.kata.mower.core.automation.ICommandFactory;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.models.Position;
import sg.kata.mower.core.output.IDisplay;
import sg.kata.mower.core.parsers.ISourceParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;

public class FullChainTest {

    private final String StandardMapping = "{'CreateLawn':'sg.kata.mower.app.automation.commands.CreateGridLawnCommand','CreateMower':'sg.kata.mower.app.automation.commands.CreateMowerCommand','D':'sg.kata.mower.app.automation.commands.TurnRightCommand','G':'sg.kata.mower.app.automation.commands.TurnLeftCommand','A':'sg.kata.mower.app.automation.commands.ForwardCommand'}";
    private final String StandardInput = "5 5\n1 2 N\nGAGAGAGAA\n3 3 E\nAADAADADDA";


    private final String EnglishMapping = "{'CreateLawn':'sg.kata.mower.app.automation.commands.CreateGridLawnCommand','CreateMower':'sg.kata.mower.app.automation.commands.CreateMowerCommand','R':'sg.kata.mower.app.automation.commands.TurnRightCommand','L':'sg.kata.mower.app.automation.commands.TurnLeftCommand','F':'sg.kata.mower.app.automation.commands.ForwardCommand'}";

    private final String EnglishInput = "5 5\n1 2 N\nLFLFLFLFF\n3 3 E\nFFRFFRFRRF";

    @Test
    public void should_execute_full_with_custom_commands(){
        IAnalysis output = execute_full_chain(StandardMapping, StandardInput);

        Position[] positions = output.getMowersPositions();
        Assertions.assertEquals(positions.length, 2);

        Assertions.assertEquals(positions[0].getX(), 1);
        Assertions.assertEquals(positions[0].getY(), 3);
        Assertions.assertEquals(positions[0].getDirection(), Direction.North);

        Assertions.assertEquals(positions[1].getX(), 5);
        Assertions.assertEquals(positions[1].getY(), 1);
        Assertions.assertEquals(positions[1].getDirection(), Direction.East);

        IDisplay display = new SimpleDisplay(output);
        display.showMowersPosition();
    }


    @Test
    public void should_execute_full_with_english_commands(){
        IAnalysis output = execute_full_chain(EnglishMapping, EnglishInput);

        Position[] positions = output.getMowersPositions();
        Assertions.assertEquals(positions.length, 2);

        Assertions.assertEquals(positions[0].getX(), 1);
        Assertions.assertEquals(positions[0].getY(), 3);
        Assertions.assertEquals(positions[0].getDirection(), Direction.North);

        Assertions.assertEquals(positions[1].getX(), 5);
        Assertions.assertEquals(positions[1].getY(), 1);
        Assertions.assertEquals(positions[1].getDirection(), Direction.East);

        IDisplay display = new SimpleDisplay(output);
        display.showMowersPosition();
    }

    private IAnalysis execute_full_chain(String mapping, String input){
        try {
            StringReader inputStrReader = new StringReader(input);
            SourceReader reader = new SourceReader(inputStrReader);

            ICommandFactory commandFactory = new CommandFactory();
            commandFactory.clearMapping();
            StringReader mappingStrReader = new StringReader(mapping);
            commandFactory.loadCustomCommandsMapping(mappingStrReader);

            ISourceParser parser = new SourceParser(reader, commandFactory);
            IEnvironment env = new Environment();

            Engine engine = new Engine(parser, env);
            engine.run();

            IAnalysis output = new ResultAnalysis(env);
            return output;

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }
}
