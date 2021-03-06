package myPackage.Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import myPackage.Game;
import myPackage.NegaMax;

public class EngineTest {
	NegaMax nm;
	
	
	@Before
	public void setUp() throws Exception {
		 nm = new NegaMax(128, true);
	}
	
	@Test
	public void test() {
		Game game = new Game();
		nm.findBestMove(game, 4);
	}
	
	@Test
	public void quiescenceTest(){
		/*At depth 1 queen sees it can take a rook. But with a quiescence 
		 * check it should see that its queen will be taken back so the 
		 * capture is not a good move
		 */
		Game game = new Game("1k6/2r5/8/8/8/2Q5/8/1K6 w - -");
		assertFalse("Engine doesn't see past horizon",
				(nm.findBestMove(game,1).convertToUCIFormat().equals("c3c7")));
	}
	
	@Test 
	public void checkMateTest(){
		Game game = new Game("4k3/8/4K3/6R1/8/8/8/8 w - -");
		String move = nm.findBestMove(game, 2).convertToUCIFormat(); 
		String errorMessage = String.format(
				"Engine cannot find mate.  \n Chose move %s in position \n %s", 
				move, game);
		assertTrue(errorMessage,
					move.equals("g5g8"));
		
	}
	
	@Test
	public void staleMateTest(){
		Game game = new Game("k7/8/2K3P1/8/8/6B1/8/8 w - -");
		assertFalse("Engine should not value stalemate as a win",
				nm.findBestMove(game, 2).convertToUCIFormat().equals("c6b6"));
	}

}
