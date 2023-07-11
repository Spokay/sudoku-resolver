package com.spokay.sudokusolver.model.cases;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Builder
public class FilledCase implements Case{
    private Integer value;
}
