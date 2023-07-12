package com.spokay.sudokusolver;

import com.spokay.sudokusolver.builder.ClassicGridBuilder;
import com.spokay.sudokusolver.model.grid.ClassicGrid;
import com.spokay.sudokusolver.model.grid.Grid;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class SudokuGameResolverApplicationTests {

    @MockBean
    ClassicGridBuilder gridBuilder;

    /*@Test
    void contextLoads() {
    }*/

    @Test
    public void testGridDataIsTransformedIntoGrid(){

        Grid grid = ClassicGrid.builder().build();

        when(gridBuilder.buildFromData(anyString())).thenReturn(grid);

    }


}
