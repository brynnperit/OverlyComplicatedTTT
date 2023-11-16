package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.*;

public class Board implements BoardDimension {

    private final BoardDimension dimensions;
    
    public int getContainedDimensions() {
        return dimensions.getContainedDimensions();
    }

    public List<Integer> getDimensionSizes(){
        return dimensions.getDimensionSizes();
    }

    public PositionState getPositionState(List<Integer> boardPosition) throws IncorrectCoordinateListLengthException{
        return dimensions.getPositionState(boardPosition);
    }

    @Override
    public String toString() {
        return dimensions.toString();
    }

    public Board(List<Integer> dimensionSizes) {
        if (dimensionSizes.size() > 1) {
            dimensions = new SubDimension(dimensionSizes);
        } else {
            dimensions = new FinalDimension(dimensionSizes);
        }
    }

    @Override
    public void setPositionState(List<Integer> boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException{
         dimensions.setPositionState(boardPosition, toSet);
    }

    @Override
    public PositionState getPositionState(int[] boardPosition) throws IncorrectCoordinateListLengthException {
        return dimensions.getPositionState(boardPosition);
    }

    @Override
    public void setPositionState(int[] boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException {
        dimensions.setPositionState(boardPosition, toSet);
    }

}
