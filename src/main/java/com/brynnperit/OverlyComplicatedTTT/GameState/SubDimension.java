package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.*;
import java.util.stream.IntStream;

class SubDimension implements BoardDimension {
    protected final List<BoardDimension> positions;
    private final int containedDimensions;

    @Override
    public int getContainedDimensions() {
        return this.containedDimensions;
    }

    public List<Integer> getDimensionSizes(){
        List<Integer> dimensionSizeList = positions.get(0).getDimensionSizes();
        dimensionSizeList.add(0, positions.size());
        return dimensionSizeList;
    }

    public PositionState getPositionState(List<Integer> boardPosition) throws IncorrectCoordinateListLengthException{
        return positions.get(boardPosition.get(0)).getPositionState(boardPosition.subList(1, boardPosition.size()));
    }

    SubDimension(List<Integer> dimensionSizes){
        containedDimensions = dimensionSizes.size();
        positions = new ArrayList<>(dimensionSizes.get(0));
        if (dimensionSizes.size() > 2){
            IntStream.range(0, dimensionSizes.get(0)).forEach(i -> positions.add(new SubDimension(dimensionSizes.subList(1, dimensionSizes.size()))));
        }else{
            IntStream.range(0, dimensionSizes.get(0)).forEach(i -> positions.add(new FinalDimension(dimensionSizes.subList(1, dimensionSizes.size()))));
        }
    }

    @Override
    public void setPositionState(List<Integer> boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException {
        positions.get(boardPosition.get(0)).setPositionState(boardPosition.subList(1, boardPosition.size()), toSet);
    }

    private FinalDimension getFinalDimensionForBoardPosition(int[] boardPosition){
        SubDimension currentDimension = this;
        for (int dimensionIndex = 0; dimensionIndex < boardPosition.length - 2; dimensionIndex++){
            currentDimension = (SubDimension) currentDimension.positions.get(boardPosition[dimensionIndex]);
        }
        return (FinalDimension) currentDimension.positions.get(boardPosition[boardPosition.length - 2]);
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
        return getFinalDimensionForBoardPosition(boardPosition).getPositionState(boardPosition[boardPosition.length - 1]);
    }

    @Override
    public void setPositionState(int[] boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException {
        if (boardPosition.length != containedDimensions){
            throw new IncorrectCoordinateListLengthException("Position coordinate list was " + (boardPosition.length - containedDimensions) + " elements too long");
        }
        getFinalDimensionForBoardPosition(boardPosition).setPositionState(boardPosition[boardPosition.length-1], toSet);
    }
}
