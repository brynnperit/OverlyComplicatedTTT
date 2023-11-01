package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.*;

public record BoardPosition(List<Integer> coords) {
    public BoardPosition(int[] coords){
        this(Arrays.stream(coords).boxed().toList());
    }
}
