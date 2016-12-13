package piano;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import midi.Instrument;
import music.Pitch;

public class PianoMachine {
	
	private Midi midi;
    private Instrument instrument;
    
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
    }
  
    
    /**
        Transitions the given note to the playing state
        @param rawPitch pitch of the note the note to begin playing
        requires that this.instrument be set
    */
    public void beginNote(Pitch rawPitch) {
    	midi.beginNote(rawPitch.toMidiFrequency(), this.instrument);
    } 
    
    /**
        Transitions the given note to the not playing state.
        @param rawPitch pitch of the note to stop playing
    */
    public void endNote(Pitch rawPitch) {
    	midi.endNote(rawPitch.toMidiFrequency(), this.instrument);
    }
    
    /**
        Cycles the current instrument to the next one in the list.
        Requires that this.instrument be set to a value in the 
        Instrument enum.
    */
    public void changeInstrument() {
        this.instrument = this.instrument.next();
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
    
    //TODO write method spec
    public void shiftUp() {
    	//TODO: implement for question 3
    }
    
    //TODO write method spec
    public void shiftDown() {
    	//TODO: implement for question 3
    }
    
    //TODO write method spec
    public boolean toggleRecording() {
    	return false;
    	//TODO: implement for question 4
    }
    
    //TODO write method spec
    protected void playback() {    	
        //TODO: implement for question 4
    }

}
