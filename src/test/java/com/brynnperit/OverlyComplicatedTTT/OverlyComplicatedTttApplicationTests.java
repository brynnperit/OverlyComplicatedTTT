package com.brynnperit.OverlyComplicatedTTT;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.brynnperit.OverlyComplicatedTTT.GameState.Board;
import com.brynnperit.OverlyComplicatedTTT.GameState.GameState;
import com.brynnperit.OverlyComplicatedTTT.GameState.IncorrectCoordinateListLengthException;
import com.brynnperit.OverlyComplicatedTTT.GameState.PositionState;

import java.util.*;

@SpringBootTest
class OverlyComplicatedTttApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void defaultBoardEmpty() throws IncorrectCoordinateListLengthException{
		boardEmptyTest(new GameState(0).board());
	}

	@Test
	void expandedBoardEmpty() throws IncorrectCoordinateListLengthException{
		boardEmptyTest(new GameState(0, List.of(3,4,5)).board());
	}

	void boardEmptyTest(Board board) throws IncorrectCoordinateListLengthException{
		List<Integer> boardDimensions = board.getDimensionSizes();
		recurseEmptyTest(board, new int[boardDimensions.size()], 0, boardDimensions);
	}

	void recurseEmptyTest(Board board, int[] boardPosition, int dimensionIndex, List<Integer> boardDimensions) throws IncorrectCoordinateListLengthException{
		if (dimensionIndex == boardPosition.length){
			assertEquals(board.getPositionState(boardPosition), PositionState.EMPTY);
		}else{
			for (int subdimensionIndex = 0; subdimensionIndex < boardDimensions.get(dimensionIndex); subdimensionIndex++){
				boardPosition[dimensionIndex] = subdimensionIndex;
				recurseEmptyTest(board, boardPosition, dimensionIndex+1, boardDimensions);
			}
		}
	}

	@Test
	void defaultBoardCountCorrect(){
		assertEquals(3*3, new GameState(0).board().getDimensionSizes().stream().mapToInt(i -> i).reduce(1, (x,y)->x*y));
	}

	@Test
	void expandedBoardCountCorrect(){
		assertEquals(3*4*5, new GameState(0, List.of(3,4,5)).board().getDimensionSizes().stream().mapToInt(i -> i).reduce(1, (x,y)->x*y));
	}

	@Test 
	void expandedBoardPositionsCorrect() throws IncorrectCoordinateListLengthException{
		Board testBoard = new GameState(0, List.of(3,4,5)).board();
		int[] boardPosition = new int[3];
		for (int x = 0; x < 3; x++){
			boardPosition[0]=x;
			for (int y = 0; y < 4; y++){
				boardPosition[1]=y;
				for (int z = 0; z < 5; z++){
					boardPosition[2]=z;
					assertNotNull(testBoard.getPositionState(boardPosition));
					assertEquals(testBoard.getPositionState(boardPosition), PositionState.EMPTY);
					assertNotNull(testBoard.getPositionState(List.of(x,y,z)));
					assertEquals(testBoard.getPositionState(List.of(x,y,z)), PositionState.EMPTY);
					testBoard.setPositionState(boardPosition, PositionState.O);
					assertEquals(testBoard.getPositionState(boardPosition), PositionState.O);
					testBoard.setPositionState(boardPosition, PositionState.X);
					assertEquals(testBoard.getPositionState(List.of(x,y,z)), PositionState.X);
				}
			}
		}
	}
	
}
