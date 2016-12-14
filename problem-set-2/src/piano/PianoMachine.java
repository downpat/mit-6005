// Patrick Dobson
// Thoughts on This Problem Set
// One of the issues with this problem set is the
// instructions do not clearly specify how state
// changes should affect other states. Should we
// be able to shift the octave up while a note is
// being played? Should we be able to change the
// instrument while a note is being played? Should
// those changes take affect after the current note
// is finished playing? While it's playing? Should
// no changes be registered while a note is playing?
// In this case, I'll be choosing the easiest to
// implement given the structure of this assignment
// and not necessarily what I believe ot be the
// best user experience.
//
// Completed problems 1-3, forgoing 4 and 5 for time
//

package piano;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import midi.Instrument;
import music.Pitch;

public class PianoMachine {
	
    public static final int DEFAULT_OCTAVE = 0;
    private final int MAX_OCTAVE = Pitch.OCTAVE * 2;
    private final int MIN_OCTAVE = -Pitch.OCTAVE * 2;

	private Midi midi;
    private Instrument instrument;
    private int octave;
    private boolean notePlaying = false;
    
	/**
	 * constructor for PianoMachine.
	 * 
	 * initialize midi device and any other state that we're storing.
	 */
    public PianoMachine() {
    	try {
            midi = Midi.getInstance();
        } catch (MidiUnavailableException e1) {
            System.err.println("Could not initialize midi device");
            e1.printStackTrace();
            return;
        }
        this.instrument = Instrument.PIANO;
        this.octave = PianoMachine.DEFAULT_OCTAVE;
    }
  
    
    /**
        Transitions the given note to the playing state
        @param rawPitch pitch of the note the note to begin playing
        requires that this.instrument be set
        The not that's played will be transposed by the current 
    */
    public void beginNote(Pitch rawPitch) {
        this.notePlaying = true;
        Pitch tPitch = rawPitch.transpose(this.octave);
    	midi.beginNote(tPitch.toMidiFrequency(), this.instrument);
    } 
    
    /**
        Transitions the given note to the not playing state.
        @param rawPitch pitch of the note to stop playing
    */
    public void endNote(Pitch rawPitch) {
        this.notePlaying = false;
        Pitch tPitch = rawPitch.transpose(this.octave);
    	midi.endNote(tPitch.toMidiFrequency(), this.instrument);
    }
    
    /**
        Cycles the current instrument to the next one in the list.
        Requires that this.instrument be set to a value in the 
        Instrument enum.
        Has no effect when a note is playing.
    */
    public void changeInstrument() {
        if(!this.notePlaying) {
            this.instrument = this.instrument.next();
        }
    }

    /**
        Retrieves the value of the currently selected instrument
        @returns The value of the Instrument enum matching the 
        currently selected Instrument
        Requires that this.instrument be set to a value in the 
        Instrument enum.
    */
    public Instrument getInstrument() {
        return this.instrument;
    }
    
    /**
        Shifts the PianoMachine's octave up to the next octave.
        Does nothing if the current octave is already the highest.
        Does nothing when a note is playing.
    */
    public void shiftUp() {
        int newOctave = this.octave + Pitch.OCTAVE;
        if(!this.notePlaying && newOctave <= this.MAX_OCTAVE) {
            this.octave = newOctave;
        }
    }
    
    /**
        Shift the PianoMachine's octave down to the next octave.
        Does nothing if the current octave is already on the lowest octave.
        Does nothing if a note is playing.
    */
    public void shiftDown() {
        int newOctave = this.octave - Pitch.OCTAVE;
        if(!this.notePlaying && newOctave >= this.MIN_OCTAVE) {
            this.octave = newOctave;
        }
    }

    /**
        Retrieves the current octave of the PianoMachine.
        Requires that the pitch is set.
    */
    public int getOctave() {
        return this.octave;
    }

    /**
        Resets the octave back to the default.
        Does nothing when a note is playing.
    */
    public void resetOctave() {
        if(!this.notePlaying) {
            this.octave = PianoMachine.DEFAULT_OCTAVE;
        }
    }
    
    /**
        Toggles the recording feature.
    */
    public boolean toggleRecording() {
    	return false;
    	//TODO: implement for question 4
    }
    
    /**
        Plays the most recent recording.
    */
    protected void playback() {    	
        //TODO: implement for question 4
    }

}
