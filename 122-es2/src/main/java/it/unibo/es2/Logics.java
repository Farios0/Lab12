package it.unibo.es2;

public interface Logics {
    String getCellText(int col, int row);
    String hitCell(int row, int col);
    String hitCell (Pair<Integer, Integer> position);
    boolean timeToQuit();
}
