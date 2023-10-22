
let gridSizeInput = document.getElementById("gridSize");
let squareSizeInput = document.getElementById("squareSize");
let submitBtn = document.getElementById("submitBtn");
let sudokuGridContainer = document.querySelector(".sudoku");
let gridStringData = document.getElementById("gridStringData");
class Grid{
    gridSize = gridSizeInput.value;
    gridHTML = "";
    gridData = {};
    constructor(gridSize) {
        this.gridSize = gridSize;
        this.gridHTML = this.setGridHTML();
        this.loadGrid();
        this.gridData = this.setGridData();
    }



    setGridHTML() {
        let gridHTML = "";
        for(let i = 0; i < this.gridSize; i++) {
            gridHTML += "<div class='row'>";
            for(let j = 0; j < this.gridSize; j++) {
                gridHTML += `<div class='square'><input type='text' name='${i}-${j}' class='case-${i}-${j}' value='0'></div>`;
            }
            gridHTML += "</div>";
        }
        return gridHTML;
    }
    querySelectCase(i, j){
        return document.querySelector(`.case-${i}-${j}`);
    }

    setGridData() {

        let gridData = {};
        for(let i = 0; i < this.gridSize; i++) {
            gridData[i.toString()] = [];
            for(let j = 0; j < this.gridSize; j++) {
                let currentCase = this.querySelectCase(i, j);
                currentCase.addEventListener("change", (e) => {
                    console.log(e.target.value);
                    gridData[i.toString()][j.toString()] = e.target.value;
                });
                gridData[i.toString()][j.toString()] = currentCase.value;
            }
        }
        console.log(gridData);
        return gridData;
    }

    loadGrid() {
        sudokuGridContainer.innerHTML = this.gridHTML;
    }
}

let grid = new Grid(gridSizeInput.value);


gridSizeInput.addEventListener("change", () => {
    grid = new Grid(gridSizeInput.value);
});

submitBtn.addEventListener("click", (e) => {
    e.preventDefault();
    gridStringData.value = JSON.stringify(grid.gridData);
    console.log(gridStringData.value);
});