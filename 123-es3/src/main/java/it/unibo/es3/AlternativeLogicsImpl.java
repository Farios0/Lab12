package it.unibo.es3;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * one implementation of the AlternativeLogics Interface.
 */
public class AlternativeLogicsImpl implements AlternativeLogics {
    private final Set<Pair<Integer, Integer>> targeted;
    private final int size;
    private boolean firstTime = true;

    /**
     * the only constructor that creates a new instance of the class.
     * 
     * @param size the size of the square table (size*size)
     */
    public AlternativeLogicsImpl(final int size) {
        this.size = size;
        targeted = getThreeRandom();
    }

    private Set<Pair<Integer, Integer>> getThreeRandom() {
        final Set<Pair<Integer, Integer>> result = new HashSet<>();
        while (result.size() != 3) {
            result.add(new Pair<>((int) (Math.random() * size), (int) (Math.random() * size)));
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Pair<Integer, Integer>> getTargets() {
        if (firstTime) {
            firstTime = false;
        } else {
            updateTargets(); 
        }
        return Collections.unmodifiableSet(targeted);
    }

    private void updateTargets() {
        targeted.addAll(
            targeted.stream()
            .flatMap(p -> Stream.of(new Pair<>(p.x() + 1, p.y()), new Pair<>(p.x(), p.y() + 1),
                                    new Pair<>(p.x() + 1, p.y() + 1), new Pair<>(p.x() - 1, p.y()),
                                    new Pair<>(p.x(), p.y() - 1), new Pair<>(p.x() - 1, p.y() - 1),
                                    new Pair<>(p.x() + 1, p.y() - 1), new Pair<>(p.x() - 1, p.y() + 1)))
            .filter(p -> p.x() < size && p.x() >= 0 && p.y() >= 0 && p.y() < size)
            .collect(Collectors.toSet())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean timeToQuit() {
        return this.targeted.size() == this.size * this.size;
    }

}
