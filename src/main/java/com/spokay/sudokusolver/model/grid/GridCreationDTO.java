package com.spokay.sudokusolver.model.grid;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
@Builder
public class GridCreationDTO {
    @NonNull
    private String gridStringData;
}
