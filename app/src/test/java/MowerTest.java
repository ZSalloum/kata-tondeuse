import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sg.kata.mower.app.models.GridLawn;
import sg.kata.mower.core.models.Direction;
import sg.kata.mower.core.models.IMower;


public class MowerTest {

    @Test
    public void should_turn_left_in_right_order(){
        //arrange
        GridLawn lawn = new GridLawn(5, 5);
        IMower mower = lawn.createAndSetCurrentMower(2, 2, Direction.North);

        // act
        Direction dir = mower.turnLeft();
        // assert
        Assertions.assertTrue(dir == Direction.West);

        // act
        dir = mower.turnLeft();
        // assert
        Assertions.assertTrue(dir == Direction.South);

        // act
        dir = mower.turnLeft();
        // assert
        Assertions.assertTrue(dir == Direction.East);

        // act
        dir = mower.turnLeft();
        // assert
        Assertions.assertTrue(dir == Direction.North);

    }


    @Test
    public void should_turn_right_in_right_order(){
        //arrange
        GridLawn lawn = new GridLawn(5, 5);
        IMower mower = lawn.createAndSetCurrentMower(2, 2, Direction.North);

        // act
        Direction dir = mower.turnRight();
        // assert
        Assertions.assertTrue(dir == Direction.East);

        // act
        dir = mower.turnRight();
        // assert
        Assertions.assertTrue(dir == Direction.South);

        // act
        dir = mower.turnRight();
        // assert
        Assertions.assertTrue(dir == Direction.West);

        // act
        dir = mower.turnRight();
        // assert
        Assertions.assertTrue(dir == Direction.North);

    }

    @Test
    public void should_move_north_until_no_more_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(1, 0, Direction.North);

        // act
        boolean success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),2 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),2 );

    }

    @Test
    public void should_move_south_until_no_more_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(1, 2, Direction.South);

        // act
        boolean success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),0 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),0 );
    }

    @Test
    public void should_move_east_until_no_more_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(0, 1, Direction.East);

        // act
        boolean success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),2 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(mower.getX(),2 );
        Assertions.assertEquals(mower.getY(),1 );
    }

    @Test
    public void should_move_west_until_no_more_possible(){
        //arrange
        GridLawn lawn = new GridLawn(3, 3);
        IMower mower = lawn.createAndSetCurrentMower(2, 1, Direction.West);

        // act
        boolean success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),1 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertTrue(success);
        Assertions.assertEquals(mower.getX(),0 );
        Assertions.assertEquals(mower.getY(),1 );

        // act
        success = mower.tryMove();
        //assert
        Assertions.assertFalse(success);
        Assertions.assertEquals(mower.getX(),0 );
        Assertions.assertEquals(mower.getY(),1 );
    }
}
