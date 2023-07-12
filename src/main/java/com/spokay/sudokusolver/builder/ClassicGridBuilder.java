package com.spokay.sudokusolver.builder;

import com.spokay.sudokusolver.model.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class ClassicGridBuilder implements GridBuilder{
    @Override
    public Grid buildFromData(String data) {
        System.out.println(data);
        return null;
    }
}
