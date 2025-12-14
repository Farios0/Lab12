package it.unibo.es3;

import java.util.Set;

/**
 * A class that manages to face all the logics problem related to the GUI.
 */
public interface AlternativeLogics {

    /**
     * returns the cells that are supposed to be targeted.
     * 
     * @return a Set of Pair containing the target positions
     */
    Set<Pair<Integer, Integer>> getTargets();

    /**
     * checks if all the cells are already been targeted.
     * 
     * @return true if they are, false otherwhise
     */
    boolean timeToQuit();
}
