package com.spokay.sudokusolver.model.cases;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilledCase implements Case{
    private Integer value;
}
