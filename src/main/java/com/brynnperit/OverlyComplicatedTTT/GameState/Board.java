package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.*;

public record Board(Map<BoardPosition, PositionState> boardPositions) {

    private static void dimensionRecurserStarter(Map<BoardPosition, PositionState> boardPositions, List<Integer> dimensionSizes){
        dimensionRecurser(boardPositions, dimensionSizes, new int[dimensionSizes.size()], 0);
    }

    private static void dimensionRecurser(Map<BoardPosition, PositionState> boardPositions, List<Integer> dimensionSizes, int[] dimensionPosition, int dimensionIndex){
        if (dimensionIndex < dimensionSizes.size()){
            for (int currentDimensionPosition = 0; currentDimensionPosition < dimensionSizes.get(dimensionIndex); currentDimensionPosition++){
                dimensionPosition[dimensionIndex] = currentDimensionPosition;
                dimensionRecurser(boardPositions, dimensionSizes, dimensionPosition, dimensionIndex+1);
            }
        }else{
            boardPositions.put(new BoardPosition(dimensionPosition), PositionState.EMPTY);
        }
    }

    public Board(List<Integer> dimensionSizes) {
        this(new HashMap<>());
        dimensionRecurserStarter(boardPositions, dimensionSizes);
    }
    
}
