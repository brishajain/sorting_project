package edu.grinnell.csc207.soundsofsorting;

import java.awt.BorderLayout;

import javax.swing.JFrame;

/**
 * The SortingVisualizer driver.
 */
public class Main {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
   
    /**
     * The main entry point to the program
     * @param args the command-line arguments
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {        
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new BorderLayout());
        NoteIndices notes = new NoteIndices();
        
        ArrayPanel arrayPanel = new ArrayPanel(notes, WIDTH, HEIGHT);
        ControlPanel controlPanel = new ControlPanel(notes, arrayPanel); 
        
        frame.setTitle("The Sounds of Sorting");
        frame.add(controlPanel, BorderLayout.PAGE_END);
        frame.add(arrayPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
