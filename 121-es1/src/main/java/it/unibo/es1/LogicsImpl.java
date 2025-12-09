package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final int nButtons;
    private final List<Integer> values;
    private final List<Boolean> states;

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        this.nButtons = size;
        values = new ArrayList<>(4);
        values.addAll(List.of(0, 0, 0, 0));
        states = new ArrayList<>(4);
        states.addAll(List.of(true, true, true, true));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.nButtons;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        return Collections.unmodifiableList(this.values);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        return Collections.unmodifiableList(this.states);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        final int newValue = values.get(elem) + 1;
        if (newValue <= nButtons) {
            values.set(elem, newValue);
            return newValue;
        }
        states.set(elem, false);
        return newValue - 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
        return values.toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return timeToQuit();
    }

    private boolean timeToQuit() {
        for (final Boolean b : states) {
            if (b) {
                return false;
            }
        }
        return true;
    }
}
