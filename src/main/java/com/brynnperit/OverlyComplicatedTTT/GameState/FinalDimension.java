package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.ArrayList;
import java.util.List;

class FinalDimension implements BoardDimension{
    protected final List<PositionState> positions;
    private static final int containedDimensions = 1;

    @Override
    public int getContainedDimensions() {
        return containedDimensions;
    }

    @Override
    public List<Integer> getDimensionSizes(){
        ArrayList<Integer> dimensionSizes = new ArrayList<>();
        dimensionSizes.add(positions.size());
        return dimensionSizes;
    }

    @Override
    public PositionState getPositionState(List<Integer> boardPosition) throws IncorrectCoordinateListLengthException{
        if (boardPosition.size() != 1){
            throw new IncorrectCoordinateListLengthException("Position coordinate list was " + (boardPosition.size() - 1) + " elements too long");
        }
        return positions.get(boardPosition.get(0));
    }

    FinalDimension(List<Integer> dimensionSizes){
        positions = new ArrayList<>(dimensionSizes.get(0));
        for (int dimensionIndex = 0; dimensionIndex < dimensionSizes.get(0); dimensionIndex++) {
            positions.add(PositionState.EMPTY);
        }
    }

    @Override
    public void setPositionState(List<Integer> boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException {
        if (boardPosition.size() != 1){
            throw new IncorrectCoordinateListLengthException("Position coordinate list was " + (boardPosition.size() - 1) + " elements too long");
        }
        positions.set(boardPosition.get(0), toSet);
    }

    public PositionState getPositionState(int boardPosition){
        return positions.get(boardPosition);
    }

    public void setPositionState(int boardPosition, PositionState toSet){
        positions.set(boardPosition, toSet);
    }

    @Override
    public String toString(){
        return BoardDimension.getDimensionString(positions);
    }

    @Override
    public PositionState getPositionState(int[] boardPosition) throws IncorrectCoordinateListLengthException {
        if (boardPosition.length != containedDimensions){
            throw new IncorrectCoordinateListLengthException("Position coordinate list was " + (boardPosition.length - containedDimensions) + " elements too long");
        }
        return positions.get(boardPosition[0]);
    }

    @Override
    public void setPositionState(int[] boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException{
        if (boardPosition.length != containedDimensions){
            throw new IncorrectCoordinateListLengthException("Position coordinate list was " + (boardPosition.length - containedDimensions) + " elements too long");
        }
        positions.set(boardPosition[0], toSet);
    }
}
