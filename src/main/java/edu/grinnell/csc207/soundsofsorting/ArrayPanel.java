package edu.grinnell.csc207.soundsofsorting;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * A drawing panel for visualizing the contents of an integer array. It is
 * assumed that this array contains the elements 0 through n, exclusive. These
 * numbers correspond to scale degrees (indices) of a Scale object.
 */
public class ArrayPanel extends JPanel {
    private NoteIndices indices;
   
    /**
     * Create a new <code>ArrayPanel</code> with the given notes and dimensions.
     * @param notes the note indices 
     * @param width the width of the panel
     * @param height the height of the panel
     */
    public ArrayPanel(NoteIndices indices, int width, int height) {
        this.indices = indices;
        this.setPreferredSize(new Dimension(width, height));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
        Integer[] notes = indices.getNotes();
        int barWidth = getWidth() / notes.length;
        for (int i = 0; i < notes.length; i++) {
            int x = i * getWidth() / notes.length;
            double heightRatio = (notes[i] + 1) / (double) notes.length;
            int height = (int) (heightRatio * getHeight());
            int y = getHeight() - height;
            if (indices.isHighlighted(i)) {
                g.setColor(Color.ORANGE);
            } else {
                g.setColor(new Color(75, (int) (heightRatio * 255), 128));
            }
            g.fillRect(x, y, barWidth, height);
        }
        // Now that we've highlighted the affected indices, clear them so they
        // aren't highlighted again on the next step.
        indices.clearAllHighlighted();
    }
}