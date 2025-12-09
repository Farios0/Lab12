package it.unibo.es2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * one implementation of the interface.
 */
public class LogicsImpl implements Logics, Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DEF_STRING = " ";
    private static final String OTHER_STRING = "*";
    private final int size;
    private final List<String> cells;

    /**
     * Builds a new object based on the size.
     * 
     * @param size its size
     */
    public LogicsImpl(final int size) {
        this.size = size;
        cells = new ArrayList<>();
        initializeCells();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCellText(final int row, final int col) {
        return cells.get(getCellIndex(row, col));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hitCell(final int row, final int col) {
        final int index = getCellIndex(row, col);
        if (!hasSymbol(index)) {
            cells.set(index, OTHER_STRING);
            return OTHER_STRING;
        } else {
            cells.set(index, DEF_STRING);
            return DEF_STRING;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String hitCell(final Pair<Integer, Integer> position) {
        return hitCell(position.x(), position.y());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean timeToQuit() {
        return rowControl() || colControl();
    }

    private void initializeCells() {
        for (int i = 0; i < size * size + size; i++) {
                cells.add(DEF_STRING);
        }
    }

    private int getCellIndex(final int row, final int col) {
        return row * size + col;
    }

    private boolean hasSymbol(final int index) {
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

    private boolean basicControl(final int start, final int limit, final int gap) {
        for (int i = start; i < limit; i += gap) {
            if (!hasSymbol(i)) {
                return false; 
            }
        }
        return true;
    }

}
