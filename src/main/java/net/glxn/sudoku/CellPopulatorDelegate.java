package net.glxn.sudoku;

public class CellPopulatorDelegate {
    private final JSudoku jSudoku;

    public CellPopulatorDelegate(JSudoku jSudoku) {
        this.jSudoku = jSudoku;
    }

    void createRelations() {
        for (Cell activeCell : jSudoku.allCells) {
            for (Cell potentialSibling : jSudoku.allCells) {
                if (!activeCell.isSame(potentialSibling)) {
                    activeCell.addXSiblingIfInRelation(potentialSibling);
                    activeCell.addYSiblingIfInRelation(potentialSibling);
                    activeCell.addSubGridSiblingIfInRelation(potentialSibling);
                }
            }
        }
    }

    void populateAllCellsArrayList() {
        jSudoku.allCells.add(jSudoku.textField9);
        jSudoku.allCells.add(jSudoku.textField8);
        jSudoku.allCells.add(jSudoku.textField7);
        jSudoku.allCells.add(jSudoku.textField6);
        jSudoku.allCells.add(jSudoku.textField5);
        jSudoku.allCells.add(jSudoku.textField4);
        jSudoku.allCells.add(jSudoku.textField3);
        jSudoku.allCells.add(jSudoku.textField2);
        jSudoku.allCells.add(jSudoku.textField1);

        jSudoku.allCells.add(jSudoku.textField16);
        jSudoku.allCells.add(jSudoku.textField11);
        jSudoku.allCells.add(jSudoku.textField12);
        jSudoku.allCells.add(jSudoku.textField13);
        jSudoku.allCells.add(jSudoku.textField18);
        jSudoku.allCells.add(jSudoku.textField14);
        jSudoku.allCells.add(jSudoku.textField10);
        jSudoku.allCells.add(jSudoku.textField17);
        jSudoku.allCells.add(jSudoku.textField15);

        jSudoku.allCells.add(jSudoku.textField20);
        jSudoku.allCells.add(jSudoku.textField23);
        jSudoku.allCells.add(jSudoku.textField26);
        jSudoku.allCells.add(jSudoku.textField22);
        jSudoku.allCells.add(jSudoku.textField19);
        jSudoku.allCells.add(jSudoku.textField25);
        jSudoku.allCells.add(jSudoku.textField24);
        jSudoku.allCells.add(jSudoku.textField21);
        jSudoku.allCells.add(jSudoku.textField27);

        jSudoku.allCells.add(jSudoku.textField33);
        jSudoku.allCells.add(jSudoku.textField32);
        jSudoku.allCells.add(jSudoku.textField30);
        jSudoku.allCells.add(jSudoku.textField31);
        jSudoku.allCells.add(jSudoku.textField35);
        jSudoku.allCells.add(jSudoku.textField29);
        jSudoku.allCells.add(jSudoku.textField28);
        jSudoku.allCells.add(jSudoku.textField36);
        jSudoku.allCells.add(jSudoku.textField34);

        jSudoku.allCells.add(jSudoku.textField41);
        jSudoku.allCells.add(jSudoku.textField40);
        jSudoku.allCells.add(jSudoku.textField37);
        jSudoku.allCells.add(jSudoku.textField42);
        jSudoku.allCells.add(jSudoku.textField39);
        jSudoku.allCells.add(jSudoku.textField45);
        jSudoku.allCells.add(jSudoku.textField44);
        jSudoku.allCells.add(jSudoku.textField43);
        jSudoku.allCells.add(jSudoku.textField38);

        jSudoku.allCells.add(jSudoku.textField53);
        jSudoku.allCells.add(jSudoku.textField51);
        jSudoku.allCells.add(jSudoku.textField52);
        jSudoku.allCells.add(jSudoku.textField46);
        jSudoku.allCells.add(jSudoku.textField49);
        jSudoku.allCells.add(jSudoku.textField47);
        jSudoku.allCells.add(jSudoku.textField50);
        jSudoku.allCells.add(jSudoku.textField48);
        jSudoku.allCells.add(jSudoku.textField54);

        jSudoku.allCells.add(jSudoku.textField63);
        jSudoku.allCells.add(jSudoku.textField58);
        jSudoku.allCells.add(jSudoku.textField55);
        jSudoku.allCells.add(jSudoku.textField59);
        jSudoku.allCells.add(jSudoku.textField56);
        jSudoku.allCells.add(jSudoku.textField62);
        jSudoku.allCells.add(jSudoku.textField60);
        jSudoku.allCells.add(jSudoku.textField57);
        jSudoku.allCells.add(jSudoku.textField61);

        jSudoku.allCells.add(jSudoku.textField70);
        jSudoku.allCells.add(jSudoku.textField71);
        jSudoku.allCells.add(jSudoku.textField68);
        jSudoku.allCells.add(jSudoku.textField72);
        jSudoku.allCells.add(jSudoku.textField65);
        jSudoku.allCells.add(jSudoku.textField64);
        jSudoku.allCells.add(jSudoku.textField69);
        jSudoku.allCells.add(jSudoku.textField67);
        jSudoku.allCells.add(jSudoku.textField66);

        jSudoku.allCells.add(jSudoku.textField76);
        jSudoku.allCells.add(jSudoku.textField77);
        jSudoku.allCells.add(jSudoku.textField79);
        jSudoku.allCells.add(jSudoku.textField73);
        jSudoku.allCells.add(jSudoku.textField78);
        jSudoku.allCells.add(jSudoku.textField80);
        jSudoku.allCells.add(jSudoku.textField81);
        jSudoku.allCells.add(jSudoku.textField74);
        jSudoku.allCells.add(jSudoku.textField75);
    }

    void setCellCoordinates() {
        jSudoku.textField9.setCoords(1, 1);
        jSudoku.textField8.setCoords(1, 2);
        jSudoku.textField7.setCoords(1, 3);
        jSudoku.textField6.setCoords(1, 4);
        jSudoku.textField5.setCoords(1, 5);
        jSudoku.textField4.setCoords(1, 6);
        jSudoku.textField3.setCoords(1, 7);
        jSudoku.textField2.setCoords(1, 8);
        jSudoku.textField1.setCoords(1, 9);

        jSudoku.textField16.setCoords(2, 1);
        jSudoku.textField11.setCoords(2, 2);
        jSudoku.textField12.setCoords(2, 3);
        jSudoku.textField13.setCoords(2, 4);
        jSudoku.textField18.setCoords(2, 5);
        jSudoku.textField14.setCoords(2, 6);
        jSudoku.textField10.setCoords(2, 7);
        jSudoku.textField17.setCoords(2, 8);
        jSudoku.textField15.setCoords(2, 9);

        jSudoku.textField20.setCoords(3, 1);
        jSudoku.textField23.setCoords(3, 2);
        jSudoku.textField26.setCoords(3, 3);
        jSudoku.textField22.setCoords(3, 4);
        jSudoku.textField19.setCoords(3, 5);
        jSudoku.textField25.setCoords(3, 6);
        jSudoku.textField24.setCoords(3, 7);
        jSudoku.textField21.setCoords(3, 8);
        jSudoku.textField27.setCoords(3, 9);

        jSudoku.textField33.setCoords(4, 1);
        jSudoku.textField32.setCoords(4, 2);
        jSudoku.textField30.setCoords(4, 3);
        jSudoku.textField31.setCoords(4, 4);
        jSudoku.textField35.setCoords(4, 5);
        jSudoku.textField29.setCoords(4, 6);
        jSudoku.textField28.setCoords(4, 7);
        jSudoku.textField36.setCoords(4, 8);
        jSudoku.textField34.setCoords(4, 9);

        jSudoku.textField41.setCoords(5, 1);
        jSudoku.textField40.setCoords(5, 2);
        jSudoku.textField37.setCoords(5, 3);
        jSudoku.textField42.setCoords(5, 4);
        jSudoku.textField39.setCoords(5, 5);
        jSudoku.textField45.setCoords(5, 6);
        jSudoku.textField44.setCoords(5, 7);
        jSudoku.textField43.setCoords(5, 8);
        jSudoku.textField38.setCoords(5, 9);

        jSudoku.textField53.setCoords(6, 1);
        jSudoku.textField51.setCoords(6, 2);
        jSudoku.textField52.setCoords(6, 3);
        jSudoku.textField46.setCoords(6, 4);
        jSudoku.textField49.setCoords(6, 5);
        jSudoku.textField47.setCoords(6, 6);
        jSudoku.textField50.setCoords(6, 7);
        jSudoku.textField48.setCoords(6, 8);
        jSudoku.textField54.setCoords(6, 9);

        jSudoku.textField63.setCoords(7, 1);
        jSudoku.textField58.setCoords(7, 2);
        jSudoku.textField55.setCoords(7, 3);
        jSudoku.textField59.setCoords(7, 4);
        jSudoku.textField56.setCoords(7, 5);
        jSudoku.textField62.setCoords(7, 6);
        jSudoku.textField60.setCoords(7, 7);
        jSudoku.textField57.setCoords(7, 8);
        jSudoku.textField61.setCoords(7, 9);

        jSudoku.textField70.setCoords(8, 1);
        jSudoku.textField71.setCoords(8, 2);
        jSudoku.textField68.setCoords(8, 3);
        jSudoku.textField72.setCoords(8, 4);
        jSudoku.textField65.setCoords(8, 5);
        jSudoku.textField64.setCoords(8, 6);
        jSudoku.textField69.setCoords(8, 7);
        jSudoku.textField67.setCoords(8, 8);
        jSudoku.textField66.setCoords(8, 9);

        jSudoku.textField76.setCoords(9, 1);
        jSudoku.textField77.setCoords(9, 2);
        jSudoku.textField79.setCoords(9, 3);
        jSudoku.textField73.setCoords(9, 4);
        jSudoku.textField78.setCoords(9, 5);
        jSudoku.textField80.setCoords(9, 6);
        jSudoku.textField81.setCoords(9, 7);
        jSudoku.textField74.setCoords(9, 8);
        jSudoku.textField75.setCoords(9, 9);
    }
}