import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.kata.mower.app.automation.CommandFactory;
import sg.kata.mower.app.models.Environment;
import sg.kata.mower.app.models.GridLawn;
import sg.kata.mower.core.automation.ICommand;
import sg.kata.mower.core.automation.ICommandFactory;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.models.IEnvironment;
import sg.kata.mower.core.models.IMower;


public class CommandTest {
    ICommandFactory factory = new CommandFactory();
    @Test
    public void should_turn_left_in_right_order_using_commands(){
        //arrange
        GridLawn lawn = new GridLawn(5, 5);
        IMower mower = lawn.createAndSetCurrentMower(2, 2, Direction.North);
        IEnvironment env = new Environment();
        env.setGridLaw(lawn);

        ICommand command;

        // act
        command = createTurnLeftCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.West);

        // act
        command = createTurnLeftCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() ==  Direction.South);

        // act
        command = createTurnLeftCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() ==  Direction.East);

        // act
        command = createTurnLeftCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() ==  Direction.North);
    }


    @Test
    public void should_turn_right_in_right_order_using_commands(){
        //arrange
        GridLawn lawn = new GridLawn(5, 5);
        IMower mower = lawn.createAndSetCurrentMower(2, 2, Direction.North);
        IEnvironment env = new Environment();
        env.setGridLaw(lawn);

        ICommand command;

        // act
        command = createTurnRightCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.East);

        // act
        command = createTurnRightCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() ==  Direction.South);

        // act
        command = createTurnRightCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() ==  Direction.West);

        // act
        command = createTurnRightCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() ==  Direction.North);
    }


    @Test
    public void should_go_north_using_command_until_not_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(1, 0, Direction.North);
        IEnvironment env = new Environment();
        env.setGridLaw(lawn);

        ICommand command;

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.North);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.North);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),2 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.North);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),2 );

    }



    @Test
    public void should_go_south_using_command_until_not_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(1, 2, Direction.South);
        IEnvironment env = new Environment();
        env.setGridLaw(lawn);

        ICommand command;

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.South);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.South);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),0 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.South);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),0 );

    }



    @Test
    public void should_go_east_using_command_until_not_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(0, 1, Direction.East);
        IEnvironment env = new Environment();
        env.setGridLaw(lawn);

        ICommand command;

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.East);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.East);
        Assertions.assertEquals(mower.getX(),2 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.East);
        Assertions.assertEquals(mower.getX(),2 );
        Assertions.assertEquals(mower.getY(),1 );
    }



    @Test
    public void should_go_west_using_command_until_not_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(2, 1, Direction.West);
        IEnvironment env = new Environment();
        env.setGridLaw(lawn);

        ICommand command;

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.West);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.West);
        Assertions.assertEquals(mower.getX(),0 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        command = createMoveCommand();
        command.execute(env);
        // assert
        Assertions.assertTrue(mower.getDirection() == Direction.West);
        Assertions.assertEquals(mower.getX(),0 );
        Assertions.assertEquals(mower.getY(),1 );
    }




    private ICommand createTurnLeftCommand(){
        return factory.createCommand(ICommandFactory.TURN_LEFT, new Object[0]);
    }

    private ICommand createTurnRightCommand(){
        return factory.createCommand(ICommandFactory.TURN_RIGHT, new Object[0]);
    }

    private ICommand createMoveCommand(){
        return factory.createCommand(ICommandFactory.MOVE_FORWARD, new Object[0]);
    }
}
