
let gridSizeInput = document.getElementById("gridSize");
let form = document.querySelector(".sudokuForm");
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
            gridHTML += `<div class='row ${i === 0 ? 'first-tr ' : ''}${i === (this.gridSize - 1) ? 'last-tr ' : ''}${i % 3 === 0 && i !== 0 ? 'styled-tr' : ''}'>`;
            for(let j = 0; j < this.gridSize; j++) {
                gridHTML += `<div class='square ${j === 0 ? 'first-td ' : ''}${j === (this.gridSize - 1) ? 'last-td ' : ''}${j % 3 === 0 && j !== 0 ? 'styled-td' : ''}'><input type='text' name='${i}-${j}' class='case-${i}-${j}' value='' min="1" max="${this.gridSize}"></div>`;
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
                    if(e.target.value === "" || e.target.value === "0"){
                        gridData[i.toString()][j.toString()] = 0;
                        e.target.value = "";
                    }else{
                        gridData[i.toString()][j.toString()] = parseInt(e.target.value);
                    }
                });
                gridData[i.toString()][j.toString()] = currentCase.value === "" ? 0 : parseInt(currentCase.value);
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

form.addEventListener("submit", (e) => {
    e.preventDefault();
    gridStringData.value = JSON.stringify(grid.gridData);
    console.log(gridStringData.value);
    form.submit();
});