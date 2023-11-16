package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.ArrayList;

abstract class PrintableDimension<E> {
    protected final ArrayList<E> positions;

    

    protected PrintableDimension(int arraySize){
        positions = new ArrayList<>(arraySize);
    }
}
