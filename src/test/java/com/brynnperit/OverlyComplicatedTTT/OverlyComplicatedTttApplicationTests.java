package com.brynnperit.OverlyComplicatedTTT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.brynnperit.OverlyComplicatedTTT.GameState.Board;
import com.brynnperit.OverlyComplicatedTTT.GameState.BoardPosition;
import com.brynnperit.OverlyComplicatedTTT.GameState.GameState;
import com.brynnperit.OverlyComplicatedTTT.GameState.PositionState;

import java.util.*;

@SpringBootTest
class OverlyComplicatedTttApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void defaultBoardEmpty(){
		boardEmptyTest(new GameState(0).board().boardPositions());
	}

	@Test
	void expandedBoardEmpty(){
		boardEmptyTest(new GameState(0, List.of(3,4,5)).board().boardPositions());
	}

	void boardEmptyTest(Map<BoardPosition, PositionState> boardPositions){
		for (BoardPosition boardPosition:boardPositions.keySet()){
			assertEquals(boardPositions.get(boardPosition), PositionState.EMPTY);
		}
	}

	@Test
	void defaultBoardCountCorrect(){
		assertEquals(new GameState(0).board().boardPositions().size(), 3*3);
	}

	@Test
	void expandedBoardCountCorrect(){
		assertEquals(new GameState(0, List.of(3,4,5)).board().boardPositions().size(), 3*4*5);
	}

	@Test 
	void expandedBoardPositionsCorrect(){
		Board testBoard = new GameState(0, List.of(3,4,5)).board();
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 4; y++){
				for (int z = 0; z < 5; z++){
					assertNotNull(testBoard.boardPositions().get(new BoardPosition(List.of(x,y,z))));
				}
			}
		}
	}
	
}
