package net.glxn.sudoku;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Cell extends JFormattedTextField {

    int xCoord;

    int yCoord;

    ArrayList<Cell> xSiblings = new ArrayList<Cell>();

    ArrayList<Cell> ySiblings = new ArrayList<Cell>();

    ArrayList<Cell> subGridSiblings = new ArrayList<Cell>();

    private ArrayList<Integer> possibleValues = new ArrayList<Integer>();

    public Cell() throws ParseException {
        this.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("#")));
        possibleValues.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        addMouseListener();
    }

    public Cell(int xCoord, int yCoord, ArrayList<Cell> xSiblings, ArrayList<Cell> ySiblings,
                ArrayList<Cell> subGridSiblings, ArrayList<Integer> possibleValues, String textValue) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.xSiblings = xSiblings;
        this.ySiblings = ySiblings;
        this.subGridSiblings = subGridSiblings;
        this.possibleValues = possibleValues;
        setText(textValue);
    }

    public void clearCell() {
        possibleValues.clear();
        possibleValues.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        setText("");
        setValue(null);
    }

    private void addMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    String message = "possible values:[";
                    Iterator<Integer> integerIterator = getPossibleValues().iterator();
                    while (integerIterator.hasNext()) {
                        Integer val = integerIterator.next();
                        message += val;
                        if (integerIterator.hasNext()) {
                            message += ",";
                        }
                    }
                    message += "]";
//                    if (getBackground() == Color.WHITE) {
//                        setBackground(Color.RED);
//                        showRelations();
                        JOptionPane.showMessageDialog(e.getComponent().getParent(), message);
//                    } else {
//                        setBackground(Color.WHITE);
//                        hideRelations();
//                    }
                }
            }
        });
    }

    private void hideRelations() {
        for (Cell x : xSiblings) {
            x.setBackground(Color.WHITE);
        }
        for (Cell y : ySiblings) {
            y.setBackground(Color.WHITE);
        }
        for (Cell s : subGridSiblings) {
            s.setBackground(Color.WHITE);
        }
    }

    private void showRelations() {
        for (Cell x : xSiblings) {
            x.setBackground(Color.RED);
        }
        for (Cell y : ySiblings) {
            y.setBackground(Color.RED);
        }
        for (Cell s : subGridSiblings) {
            s.setBackground(Color.RED);
        }
    }


    public int getxCoord() {
        return xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void addXSiblingIfInRelation(Cell potentialSibling) {
        if (this.isXsibling(potentialSibling)) {
            this.xSiblings.add(potentialSibling);
        }
    }


    public void addYSiblingIfInRelation(Cell potentialSibling) {
        if (this.isYsibling(potentialSibling)) {
            this.ySiblings.add(potentialSibling);
        }
    }

    public void addSubGridSiblingIfInRelation(Cell potentialSibling) {
        if (this.isSubGridSibling(potentialSibling)) {
            this.subGridSiblings.add(potentialSibling);
        }
    }

    public void setCoords(int xCoord, int yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public boolean isSame(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cell cell = (Cell) o;

        return xCoord == cell.xCoord && yCoord == cell.yCoord;

    }

    public boolean isXsibling(Cell potentialSibling) {
        return this.getxCoord() == potentialSibling.getxCoord();
    }

    public boolean isYsibling(Cell potentialSibling) {
        return this.getyCoord() == potentialSibling.getyCoord();
    }

    public boolean isSubGridSibling(Cell potentialSibling) {
        return this.getSubGrid() == potentialSibling.getSubGrid();
    }

    public boolean updatePossibleValues() {
        boolean updatedBasedOnXSiblings = false;
        boolean updatedBasedOnYSiblings = false;
        boolean updatedBasedOnSubGridSiblings = false;

        if (possibleValues.size() > 1) {
            for (Cell xSib : xSiblings) {
                Integer siblingIntValue = xSib.getIntValue();
                if (possibleValues.contains(siblingIntValue)) {
                    possibleValues.remove(siblingIntValue);
                    updatedBasedOnXSiblings = true;
                }
            }
        }

        if (possibleValues.size() > 1) {
            for (Cell ySib : ySiblings) {
                Integer siblingIntValue = ySib.getIntValue();
                if (possibleValues.contains(siblingIntValue)) {
                    possibleValues.remove(siblingIntValue);
                    updatedBasedOnYSiblings = true;
                }
            }
        }

        if (possibleValues.size() > 1) {
            for (Cell subSib : subGridSiblings) {
                Integer siblingIntValue = subSib.getIntValue();
                if (possibleValues.contains(siblingIntValue)) {
                    possibleValues.remove(siblingIntValue);
                    updatedBasedOnSubGridSiblings = true;
                }
            }
        }

        return (updatedBasedOnXSiblings || updatedBasedOnYSiblings || updatedBasedOnSubGridSiblings);
    }

    public boolean attemptToSetValue() {
        boolean valueSet = false;

        for (Integer possibleValue : possibleValues) {
            boolean isPossibleOnSubGridSiblings = false;
            boolean isPossibleOnXSiblings = false;
            boolean isPossibleOnYSiblings = false;
            for (Cell subSibling : subGridSiblings) {
                if (subSibling.getPossibleValues().contains(possibleValue)) {
                    isPossibleOnSubGridSiblings = true;
                    break;
                }
            }
            for (Cell subSibling : xSiblings) {
                if (subSibling.getPossibleValues().contains(possibleValue)) {
                    isPossibleOnXSiblings = true;
                    break;
                }
            }
            for (Cell subSibling : ySiblings) {
                if (subSibling.getPossibleValues().contains(possibleValue)) {
                    isPossibleOnYSiblings = true;
                    break;
                }
            }

            if(!isPossibleOnSubGridSiblings || !isPossibleOnXSiblings || !isPossibleOnYSiblings) {
                setIntValue(possibleValue);
                valueSet = true;
                break;
            }
        }

        return valueSet;
    }

    public Integer getIntValue() {
        Integer returnValue = null;
        try {
            returnValue = Integer.valueOf(getText().trim());
        } catch (NumberFormatException ignored) {}

        return returnValue;
    }

    public void setIntValue(Integer valueToSet) {
        String value = String.valueOf(valueToSet);
        setText(value);
        possibleValues = new ArrayList<Integer>(Arrays.asList(valueToSet));
        System.out.println("value set to:" + value);
    }

    private int getSubGrid() {
        //// y 1,2,3
        if (yCoord == 1 || yCoord == 2 || yCoord == 3) {
            // x: 1,2,3
            // is grid 1
            if (xCoord == 1 || xCoord == 2 || xCoord == 3) {
                return 1;
            }

            // x: 4,5,6
            // is grid 2
            if (xCoord == 4 || xCoord == 5 || xCoord == 6) {
                return 2;
            }

            // x: 7,8,9
            // is grid 3
            if (xCoord == 7 || xCoord == 8 || xCoord == 9) {
                return 3;
            }
        }

        //// y 4,5,6
        if (yCoord == 4 || yCoord == 5 || yCoord == 6) {
            // x: 1,2,3
            // is grid 4
            if (xCoord == 1 || xCoord == 2 || xCoord == 3) {
                return 4;
            }

            // x: 4,5,6
            // is grid 5
            if (xCoord == 4 || xCoord == 5 || xCoord == 6) {
                return 5;
            }

            // x: 7,8,9
            // is grid 6
            if (xCoord == 7 || xCoord == 8 || xCoord == 9) {
                return 6;
            }
        }

        //// y 7,8,9
        if (yCoord == 7 || yCoord == 8 || yCoord == 9) {
            // x: 1,2,3
            // is grid 7
            if (xCoord == 1 || xCoord == 2 || xCoord == 3) {
                return 7;
            }

            // x: 4,5,6
            // is grid 8
            if (xCoord == 4 || xCoord == 5 || xCoord == 6) {
                return 8;
            }

            // x: 7,8,9
            // is grid 9
            if (xCoord == 7 || xCoord == 8 || xCoord == 9) {
                return 9;
            }
        }
        throw new RuntimeException("no grid could be determined:" + this.toString());
    }

    public ArrayList<Integer> getPossibleValues() {
        if (getIntValue() != null && possibleValues.size() > 1) {
            for (Integer i : new ArrayList<Integer>(possibleValues)) {
                if(!i.equals(getIntValue())) {
                    possibleValues.remove(i);
                }
            }
        }

        return possibleValues;
    }

    public Cell shallowCopy() {
        return new Cell(this.xCoord, this.yCoord, this.xSiblings, this.ySiblings, this.subGridSiblings, this.possibleValues, this.getText());
    }

    public void performShallowOverwrite(Cell cellToGetWriteValuesFrom) {
        if(cellToGetWriteValuesFrom.xCoord != this.xCoord || cellToGetWriteValuesFrom.yCoord !=  this.yCoord) {
            throw new RuntimeException("Attempt to overwrite a cell with values from a cell with  non matching coords");
        }
        this.xSiblings = cellToGetWriteValuesFrom.xSiblings;
        this.ySiblings = cellToGetWriteValuesFrom.ySiblings;
        this.subGridSiblings = cellToGetWriteValuesFrom.subGridSiblings;
        this.possibleValues = cellToGetWriteValuesFrom.possibleValues;
        this.setText(cellToGetWriteValuesFrom.getText());
    }
}