package it.unibo.es2;

import java.util.ArrayList;
import java.util.List;

public class LogicsImpl implements Logics{
    private static final String DEF_STRING = " ";
    private static final String OTHER_STRING = "*";
    private final int size;
    private final List<String> cells;
    
    public LogicsImpl(int size) {
        this.size = size;
        cells = new ArrayList<>();
        initializeCells();
    }

    @Override
    public String getCellText(int row, int col) {
        return cells.get(getCellIndex(row, col));
    }

    @Override
    public String hitCell(int row, int col) {
        final int index = getCellIndex(row, col);
        if (!hasSymbol(index)) {
            cells.set(index, OTHER_STRING);
            return OTHER_STRING;
        } else {
            cells.set(index, DEF_STRING);
            return DEF_STRING;
        }
    }

    @Override
    public String hitCell(Pair<Integer, Integer> position) {
        return hitCell(position.x(), position.y());
    }

    @Override
    public boolean timeToQuit() {
        return rowControl() || colControl();
    }

    private void initializeCells() {
        for (int i = 0; i < size * size + size; i++) {
                cells.add(DEF_STRING);
        }
        
    }

    private int getCellIndex(int row, int col) {
        return row * size + col;
    }

    private boolean hasSymbol(int index) {
        return "*".equals(cells.get(index));
    }

    private boolean rowControl() {
        for (int i = 0; i < (size - 1) * size + size - 1; i += size) {
            if (basicControl(i, i + size, 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean colControl() {
        for (int i = 0; i < size; i++) {
            if (basicControl(i, (size - 1) * size + size - 1, size)) {
                return true;
            }
        
        }
        return false;
    }

    private boolean basicControl(int start, int limit, int gap) {
        for (int i = start; i < limit; i += gap) {
            if (!hasSymbol(i)) {
                return false; 
            }
        }
        return true;
    }

}
