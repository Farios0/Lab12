package it.unibo.es2;

/**
 * this logics interface is made for this particular exercise and it is supposed to be used to
 * handle the logic part of a program with cells that closes when a row or column of cells have all the correct symbol.
 */
public interface Logics {
    /**
     * returns the text associated to the cell thanks to the row and column arguments.
     * 
     * @param col its column
     * @param row its row
     * @return the associated text
     */
    String getCellText(int col, int row);

    /**
     * this method checks what text symbol has the cell and return the other text you should change it with.
     * 
     * @param row its row
     * @param col its column
     * @return the new text
     */
    String hitCell(int row, int col);

    /**
     * this method checks what text symbol has the cell and return the other text you should change it with thanks to the position provided. 
     * 
     * @param position its position
     * @return the new text
     */
    String hitCell (Pair<Integer, Integer> position);

    /**
     * this method check if the sysmbols complete a row or a column
     * 
     * @return true if it does, false otherwhise
     */
    boolean timeToQuit();
}
