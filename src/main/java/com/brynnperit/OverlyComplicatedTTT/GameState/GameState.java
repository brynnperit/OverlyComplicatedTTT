package com.brynnperit.OverlyComplicatedTTT.GameState;

import java.util.*;

public record GameState(long id, Board board) {
    public GameState(long id){
        this(id, new Board(List.of(3,3)));
    }
    public GameState(long id, List<Integer> dimensions){
        this(id, new Board(dimensions));
    }
}
