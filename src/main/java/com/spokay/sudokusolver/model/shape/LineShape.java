package com.spokay.sudokusolver.model.shape;

import com.spokay.sudokusolver.model.cases.Case;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineShape implements Shape{
    Case[] lineCases;
}
