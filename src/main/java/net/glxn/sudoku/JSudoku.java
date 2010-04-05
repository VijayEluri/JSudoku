package net.glxn.sudoku;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JSudoku {
    protected JPanel panel1;

    protected Cell textField9;
    protected Cell textField8;
    protected Cell textField7;
    protected Cell textField6;
    protected Cell textField5;
    protected Cell textField4;
    protected Cell textField3;
    protected Cell textField2;
    protected Cell textField1;

    protected Cell textField16;
    protected Cell textField11;
    protected Cell textField12;
    protected Cell textField13;
    protected Cell textField18;
    protected Cell textField14;
    protected Cell textField10;
    protected Cell textField17;
    protected Cell textField15;

    protected Cell textField20;
    protected Cell textField23;
    protected Cell textField26;
    protected Cell textField22;
    protected Cell textField19;
    protected Cell textField25;
    protected Cell textField24;
    protected Cell textField21;
    protected Cell textField27;

    protected Cell textField33;
    protected Cell textField32;
    protected Cell textField30;
    protected Cell textField31;
    protected Cell textField35;
    protected Cell textField29;
    protected Cell textField28;
    protected Cell textField36;
    protected Cell textField34;

    protected Cell textField41;
    protected Cell textField40;
    protected Cell textField37;
    protected Cell textField42;
    protected Cell textField39;
    protected Cell textField45;
    protected Cell textField44;
    protected Cell textField43;
    protected Cell textField38;

    protected Cell textField53;
    protected Cell textField51;
    protected Cell textField52;
    protected Cell textField46;
    protected Cell textField49;
    protected Cell textField47;
    protected Cell textField50;
    protected Cell textField48;
    protected Cell textField54;

    protected Cell textField63;
    protected Cell textField58;
    protected Cell textField55;
    protected Cell textField59;
    protected Cell textField56;
    protected Cell textField62;
    protected Cell textField60;
    protected Cell textField57;
    protected Cell textField61;

    protected Cell textField70;
    protected Cell textField71;
    protected Cell textField68;
    protected Cell textField72;
    protected Cell textField65;
    protected Cell textField64;
    protected Cell textField69;
    protected Cell textField67;
    protected Cell textField66;

    protected Cell textField76;
    protected Cell textField77;
    protected Cell textField79;
    protected Cell textField73;
    protected Cell textField78;
    protected Cell textField80;
    protected Cell textField81;
    protected Cell textField74;
    protected Cell textField75;

    private JButton button1;
    private JButton clearButton;
    private JButton loadSampleButton;
    private JButton saveGridButton;
    private JButton loadGridButton;
    private JComboBox savedGridSelector;

    protected ArrayList<Cell> allCells = new ArrayList<Cell>();
    protected ArrayList<Cell> workingSetOfShallowCopyCells = new ArrayList<Cell>();

    private HashMap<Integer, List<Cell>> savedGrids = new HashMap<Integer, List<Cell>>();

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("JSudoku");
        frame.setContentPane(new JSudoku().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    protected void solvePuzzle(Integer numberOfEmptyCellsBefore) {
        createWorkingSetOfShallowCopyCells();
        calculatePossibleValuesAndSetCellValues();
        commitChangesToAllCells();

        Integer numberOfEmptyCells = getNumberOfEmptyCellsInGrid();
        if (!numberOfEmptyCells.equals(numberOfEmptyCellsBefore)) {
            solvePuzzle(numberOfEmptyCells);
        }
    }

    Integer getNumberOfEmptyCellsInGrid() {
        Integer emptyCells = 0;
        for (Cell cell : allCells) {
            if(cell.getIntValue() == null) {
                emptyCells++;
            }
        }
        return emptyCells;
    }


    private void commitChangesToAllCells() {
        for (Cell originalCell : allCells) {
            Cell solvedCell = getCellAtCoord(originalCell.xCoord, originalCell.yCoord, workingSetOfShallowCopyCells);
            originalCell.performShallowOverwrite(solvedCell);
        }
    }

    private void createWorkingSetOfShallowCopyCells() {
        workingSetOfShallowCopyCells = new ArrayList<Cell>();
        for (Cell cellToCopy : allCells) {
            workingSetOfShallowCopyCells.add(cellToCopy.shallowCopy());
        }
    }

    protected Cell getCellAtCoord(int xCoord, int yCoord, ArrayList<Cell> listToGetCellFrom) {
        for (Cell cell : listToGetCellFrom) {
            if(cell.xCoord == xCoord && cell.yCoord == yCoord) {
                return cell;
            }
        }
        return null;
    }

    private void calculatePossibleValuesAndSetCellValues() {
        boolean changesMade = true;
        while (changesMade) {
            boolean possibleValueUpdated = calculatePossibleValues();
            boolean cellUpdated = setCellValueForCellsWithOnlyOnePossibleValue();
            boolean cellUpdatedOnAttempt = attemptToSetValuesOnCellsWithMoreThanOnePossibleValue();
            changesMade = (possibleValueUpdated || cellUpdated || cellUpdatedOnAttempt);
        }
    }


    private boolean setCellValueForCellsWithOnlyOnePossibleValue() {
        boolean valueUpdated = false;
        for (Cell currentCell : workingSetOfShallowCopyCells) {
            if (currentCell.getIntValue() == null) {
                if (currentCell.getPossibleValues().size() == 1) {
                    currentCell.setIntValue(currentCell.getPossibleValues().get(0));
                    valueUpdated = true;
                    
                }
            }
        }
        return valueUpdated;
    }

    private boolean attemptToSetValuesOnCellsWithMoreThanOnePossibleValue() {
        boolean valueUpdated = false;
        for (Cell currentCell : workingSetOfShallowCopyCells) {
            if (currentCell.getIntValue() == null) {
                if (currentCell.getPossibleValues().size() > 1) {
                    if(currentCell.attemptToSetValue()) {
                        valueUpdated = true;
                    }
                }
            }
        }
        return valueUpdated;
    }

    private boolean calculatePossibleValues() {
        boolean valueUpdated = false;
        for (Cell currentCell : workingSetOfShallowCopyCells) {
            if (currentCell.updatePossibleValues()) {
                valueUpdated = true;
            }
        }
        return valueUpdated;
    }

    public JSudoku() {
        CellPopulatorDelegate cellPopulatorDelegate = new CellPopulatorDelegate(this);
        cellPopulatorDelegate.populateAllCellsArrayList();
        cellPopulatorDelegate.setCellCoordinates();
        cellPopulatorDelegate.createRelations();
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solvePuzzle(getNumberOfEmptyCellsInGrid());
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Cell cell : allCells) {
                    cell.clearCell();
                }
            }
        });
        loadSampleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadSample();
            }
        });
        saveGridButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                savedGrids.put(savedGrids.size(), new ArrayList<Cell>(allCells));
                savedGridSelector.addItem(savedGrids.size());
            }
        });
        loadGridButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (savedGridSelector.getSelectedItem() != null) {
                    allCells = (ArrayList<Cell>) savedGrids.get(savedGridSelector.getSelectedIndex());
                    //todo: update all values for coordinates from saved copy
                }
            }
        });
    }

    private boolean solutionComplete() {
        boolean complete = true;
        for (Cell cell : workingSetOfShallowCopyCells) {
            if (cell.getPossibleValues().size() != 1) {
                complete = false;
            }
        }
        return complete;
    }

    public void loadSample() {
        setValueForCoord(2, 1, 1);
        setValueForCoord(4, 1, 9);
        setValueForCoord(5, 1, 5);
        setValueForCoord(7, 1, 7);

        setValueForCoord(2, 2, 5);
        setValueForCoord(5, 2, 3);

        setValueForCoord(2, 3, 9);
        setValueForCoord(4, 3, 6);
        setValueForCoord(9, 3, 5);

        setValueForCoord(1, 4, 2);
        setValueForCoord(3, 4, 6);
        setValueForCoord(6, 4, 8);

        setValueForCoord(3, 5, 8);
        setValueForCoord(7, 5, 4);

        setValueForCoord(4, 6, 3);
        setValueForCoord(7, 6, 6);
        setValueForCoord(9, 6, 9);

        setValueForCoord(1, 7, 4);
        setValueForCoord(6, 7, 3);
        setValueForCoord(8, 7, 5);

        setValueForCoord(5, 8, 8);
        setValueForCoord(8, 8, 6);

        setValueForCoord(3, 9, 1);
        setValueForCoord(5, 9, 4);
        setValueForCoord(6, 9, 5);
        setValueForCoord(8, 9, 2);
    }

    private void setValueForCoord(int x, int y, int value) {
        getCellAtCoord(x, y, allCells).setIntValue(value);
    }
}
