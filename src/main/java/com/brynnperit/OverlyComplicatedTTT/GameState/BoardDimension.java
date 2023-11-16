package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.List;

public interface BoardDimension {
    public List<Integer> getDimensionSizes();
    public int getContainedDimensions();
    public PositionState getPositionState(List<Integer> boardPosition) throws IncorrectCoordinateListLengthException, IndexOutOfBoundsException;
    public void setPositionState(List<Integer> boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException, IndexOutOfBoundsException;
    PositionState getPositionState(int[] boardPosition) throws IncorrectCoordinateListLengthException, IndexOutOfBoundsException;
    void setPositionState(int[] boardPosition, PositionState toSet) throws IncorrectCoordinateListLengthException, IndexOutOfBoundsException;

    public static <E> String getDimensionString(List<E> positions){
        StringBuilder returnString = new StringBuilder();
        returnString.append('{');
        for (int dimensionIndex = 0; dimensionIndex < positions.size(); dimensionIndex++) {
            returnString.append("Index " + dimensionIndex + ":");
            returnString.append(positions.get(dimensionIndex));
            returnString.append(", ");
        }
        returnString.delete(returnString.length() - 2, returnString.length());
        returnString.append('}');
        return returnString.toString();
    }
}
