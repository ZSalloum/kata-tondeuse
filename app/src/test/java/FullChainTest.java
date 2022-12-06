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

    private String str = "{'CreateLawn':'sg.kata.mower.app.automation.commands.CreateGridLawnCommand','CreateMower':'sg.kata.mower.app.automation.commands.CreateMowerCommand','D':'sg.kata.mower.app.automation.commands.TurnRightCommand','G':'sg.kata.mower.app.automation.commands.TurnLeftCommand','A':'sg.kata.mower.app.automation.commands.ForwardCommand'}";


    @Test
    public void should_execute_full_with_custom_commands(){
        Reader freader = null;
        final String fpath = "C:\\Users\\zsall\\source\\kata-tondeuse\\app\\target\\classes\\mower.txt";
        try {
            freader = open(fpath);
            SourceReader reader = new SourceReader(freader);
            ICommandFactory commandFactory = new CommandFactory();
            commandFactory.clearMapping();
            StringReader sr = new StringReader(str);
            commandFactory.loadCustomCommandsMapping(sr);

            ISourceParser parser = new SourceParser(reader, commandFactory);
            IEnvironment env = new Environment();

            Engine engine = new Engine(parser, env);
            engine.run();

            IAnalysis output = new ResultAnalysis(env);

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

        }catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            close(freader);
        }
    }

    private Reader open(String filepath) throws FileNotFoundException {
        return new FileReader(filepath);
    }

    private void close(Reader reader){
        try{
            if(reader != null) {
                reader.close();
            }
        }catch (Exception ex) {
        }
    }
}
