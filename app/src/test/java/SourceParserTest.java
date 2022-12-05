import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.kata.mower.app.automation.CommandFactory;
import sg.kata.mower.app.automation.commands.*;
import sg.kata.mower.app.parsers.SourceParser;
import sg.kata.mower.core.MowerException;
import sg.kata.mower.core.MowerParsingException;
import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.automation.ICommandFactory;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.parsers.ISourceParser;
import sg.kata.mower.core.readers.IInputSourceReader;


public class SourceParserTest {
    private class StringSource implements IInputSourceReader {
        private String header;
        private String[] mowers;

        private boolean headerRead = false;
        private int index;
        public StringSource(String header, String[] mowers){
            this.header = header;
            this.mowers = mowers;
        }

        @Override
        public String readHeader() {
            if(!headerRead){
                headerRead = true;
                return header;
            }

            throw new MowerException("Header already read");

        }

        @Override
        public String readMower() {
            if(!headerRead){
                throw new MowerException("Must read header first");
            }
            if(index % 2 == 0){
                if(index < mowers.length){
                    String data = mowers[index];
                    index++;
                    return data;
                }
            }
            return null;
        }

        @Override
        public String readMowerCommands() {
            if(!headerRead){
                throw new MowerException("Must read header first");
            }
            if(index % 2 == 1){
                if(index < mowers.length){
                    String data = mowers[index];
                    index++;
                    return data;
                }
            }
            throw new MowerException("Expecting to read Mower") ;
        }
    }
    private ICommandFactory commandFactory = new CommandFactory();

    @Test
    public void should_parse_normally(){
        //arrange
        StringSource srcReader = new StringSource("5 5", new String[]{"0 0 N", "A"});
        ISourceParser parser = new SourceParser(srcReader, commandFactory);
        ICommand cmd;

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateGridLawnCommand.class, cmd);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getWidth(), 6);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getHeight(), 6);

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateMowerCommand.class, cmd);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getX(), 0);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getY(), 0);

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(ForwardCommand.class, cmd);

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(NoMoreActionCommand.class, cmd);

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(EndCommand.class, cmd);
    }

    @Test
    public void should_throw_exception_on_extra_space_in_header(){
        //arrange
        StringSource srcReader = new StringSource("5  5 ", new String[]{"0 0 N", "A"});
        final ISourceParser parser = new SourceParser(srcReader, commandFactory);
        ICommand cmd;

        // act, assert
        Assertions.assertThrows(MowerException.class, ()->{parser.parseNext();});

        //arrange
        srcReader = new StringSource("5,5 ", new String[]{"0 0 N", "A"});
        final ISourceParser parser2 = new SourceParser(srcReader, commandFactory);

        // act
        Assertions.assertThrows(MowerException.class, ()->{parser2.parseNext();});

    }

    @Test
    public void should_throw_exception_on_extra_space_in_mower(){
        //arrange
        StringSource srcReader = new StringSource("5 5", new String[]{"0  0 N",  "A"});
        final ISourceParser parser = new SourceParser(srcReader, commandFactory);
        ICommand cmd;

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateGridLawnCommand.class, cmd);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getWidth(), 6);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getHeight(), 6);

        // act
        Assertions.assertThrows(RuntimeException.class, ()->{parser.parseNext();});


        //arrange
        srcReader = new StringSource("5 5", new String[]{"0, 0 N",  "A"});
        final ISourceParser parser2 = new SourceParser(srcReader, commandFactory);

        // act
        cmd = parser2.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateGridLawnCommand.class, cmd);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getWidth(), 6);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getHeight(), 6);

        // act
        Assertions.assertThrows(MowerParsingException.class, ()->{parser2.parseNext();});

    }


    @Test
    public void should_throw_exception_on_extra_space_in_mower_commands(){
        //arrange
        StringSource srcReader = new StringSource("5 5", new String[]{"0 0 N",  "A "});
        final ISourceParser parser = new SourceParser(srcReader, commandFactory);
        ICommand cmd;

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateGridLawnCommand.class, cmd);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getWidth(), 6);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getHeight(), 6);

        // act
        cmd = parser.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateMowerCommand.class, cmd);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getX(), 0);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getY(), 0);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getDir(), Direction.North);

        // act
        cmd = parser.parseNext();
        Assertions.assertThrows(MowerException.class, ()->{parser.parseNext();});

        //arrange
        srcReader = new StringSource("5 5", new String[]{"0 0 N",  "A, "});
        final ISourceParser parser2 = new SourceParser(srcReader, commandFactory);

        // act
        cmd = parser2.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateGridLawnCommand.class, cmd);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getWidth(), 6);
        Assertions.assertEquals(((CreateGridLawnCommand)cmd).getHeight(), 6);

        // act
        cmd = parser2.parseNext();
        //assert
        Assertions.assertInstanceOf(CreateMowerCommand.class, cmd);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getX(), 0);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getY(), 0);
        Assertions.assertEquals(((CreateMowerCommand)cmd).getDir(), Direction.North);

        // act
        cmd = parser2.parseNext();
        Assertions.assertThrows(MowerException.class, ()->{parser2.parseNext();});

    }
}
