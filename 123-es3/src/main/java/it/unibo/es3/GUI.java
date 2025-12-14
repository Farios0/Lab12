package it.unibo.es3;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * GUI for the game.
 */
public final class GUI extends JFrame {

    @Serial
    private static final long serialVersionUID = 1L;
    //private final List<JButton> cells = new ArrayList<>();
    private final Map<Pair<Integer, Integer>, JButton> cells = new HashMap<>();
    private final AlternativeLogics logic;

    /**
     * Constructor.
     *
     * @param width the size of the grid
     */
    public GUI(final int width) {
        logic = new AlternativeLogicsImpl(width);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        // Create a panel with a grid layout
        final JPanel panel = new JPanel(new GridLayout(width, width));
        final JPanel canvas = new JPanel(new BorderLayout());
        canvas.add(panel, BorderLayout.CENTER);
        this.getContentPane().add(canvas);
        // Create buttons and add them to the panel
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                final JButton button = new JButton(" ");
                //this.cells.add(button);
                this.cells.put(new Pair<>(i, j), button);
                panel.add(button);
            }
        }
        JButton forward = new JButton(">");
        forward.addActionListener(e -> {
                if (logic.timeToQuit()) {dispose();}
                logic.getTargets().stream().forEach(p -> cells.get(p).setText("*"));
            });
        canvas.add(forward, BorderLayout.SOUTH);
        pack();
        
        this.setVisible(true);
    }
}
