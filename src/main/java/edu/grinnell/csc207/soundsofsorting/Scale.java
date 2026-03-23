package edu.grinnell.csc207.soundsofsorting;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/** A musical scale. */
public class Scale {
    /** The (MIDI) note values of this scale. */
    private int[] notes;
    
    private static final int REGULAR_VELOCITY = 60;
    private static final int EMPHASIZED_VELOCITY = 120;
    
    ///// Initialization for the Midi sub-system. /////
    @SuppressWarnings("unused")
    private static Synthesizer synth;
    private static MidiChannel instrument;
    static {
        Synthesizer synth;
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            instrument = synth.getChannels()[0];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * @param notes the (MIDI) note values of this scale, assumed to be
     *        in ascending order
     */
    public Scale(int[] notes) {
        this.notes = notes;
    }
    
    /**
     * @return the number of notes in the scale
     */
    public int size() {
        return notes.length;
    }
    
    /**
     * Plays a note of the scale through Swing's MIDI library.
     * @param index the index of the note to play within the scale
     * @param emphasized true if this note be emphasized
     */
    public void playNote(int index, boolean emphasized) {
        instrument.noteOn(
            notes[index],
            emphasized ? EMPHASIZED_VELOCITY : REGULAR_VELOCITY);
    }
    
    /** The MIDI note values for the B minor pentatonic scale */
    public static final Scale bMinorPentatonic = new Scale(new int[] {
        46, 49, 51, 53, 56, 58, 61, 63, 65, 68, 70, 73, 75, 78, 82, 85, 87
    });

    /** The MIDI note values for the chromatic scale */
    public static final Scale chromatic = new Scale(new int[] {
        40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
        50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
        60, 61, 62, 63, 64, 65, 66, 67, 68, 69,
        70, 71, 72, 73, 74, 75, 76, 77, 78, 79
    });
}
