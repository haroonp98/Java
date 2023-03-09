package connectfour;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/* you will need to add test methods and likely change the
setup method as well.  The samples that are here are just so that
you can see how junit works.

Tests are run on build unless specifically excluded with -x test.
The test results are reported in the reports subfolder of the build directory */


public class BoardTest{
    private Board tester;

    @Before
    public void setup(){
        tester = new Board();

    }

    @Test
    public void testAssignPlayerSuccess(){
        tester.assignPlayer(0, 'X');
        Assert.assertEquals(tester.getGrid()[5][0],'X');

    }

    @Test
    public void testAssignPlayerFail(){
        tester.assignPlayer(0, 'X');
        Assert.assertNotEquals(tester.getGrid()[5][0],'O');

    }

    @Test
    public void testValidateSuccess(){
        Assert.assertTrue(tester.validate(0));
    }


    @Test
    public void testValidateFailure(){
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(0, 'O');
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(0, 'O');
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(0, 'O');
        Assert.assertFalse(tester.validate(0));
    }

    @Test
    public void testIsWinnerSuccess(){
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(1, 'O');
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(2, 'O');
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(3, 'O');
        tester.assignPlayer(0, 'X');
        Assert.assertTrue(tester.isWinner('X'));
    }

    @Test
    public void testIsWinnerFailure(){
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(1, 'O');
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(2, 'O');
        tester.assignPlayer(0, 'X');
        tester.assignPlayer(3, 'O');
        Assert.assertFalse(tester.isWinner('O'));
    }
}
