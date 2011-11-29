package strategy.playerBLHM;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import strategy.common.PlayerColor;

public class StrategyPlayerImplTest {
	StrategyPlayerImpl playerRed;
	StrategyPlayerImpl playerBlue;

	@Before
	public void setUp() throws Exception {
		playerRed = new StrategyPlayerImpl(PlayerColor.RED);
		playerBlue = new StrategyPlayerImpl(PlayerColor.BLUE);
	}

	@Test
	public void testRedInit(){
		System.out.println(playerRed.boardCopy.toString());
		assertEquals(playerRed, playerRed);
	}
	
	@Test
	public void testBlueInit(){
		System.out.println(playerBlue.boardCopy.toString());
		assertEquals(playerBlue,playerBlue);
	}
}
