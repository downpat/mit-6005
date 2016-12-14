package piano;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.sound.midi.MidiUnavailableException;

import midi.Midi;
import midi.Instrument;
import music.Pitch;

import org.junit.Test;

public class PianoMachineTest {
	
	PianoMachine pm = new PianoMachine();
	
    @Test
    public void singleNoteTest() throws MidiUnavailableException {
        String expected0 = "on(61,PIANO) wait(100) off(61,PIANO)";
        
    	Midi midi = Midi.getInstance();

    	midi.clearHistory();
    	
        pm.beginNote(new Pitch(1));
		Midi.wait(100);
		pm.endNote(new Pitch(1));

        System.out.println(midi.history());
        assertEquals(expected0,midi.history());
    }

    @Test
    public void beginNoteTest() throws MidiUnavailableException {
        String onStateExpected = "on(61,PIANO)";

        Midi midi = Midi.getInstance();
        midi.clearHistory();

        pm.beginNote(new Pitch(1));
        
        String history = midi.history();
        assertEquals(onStateExpected, history);
        System.out.println(history);
    }

    @Test
    public void endNoteTest() throws MidiUnavailableException {
        String offStateExpected = "off(61,PIANO)";

        Midi midi = Midi.getInstance();
        midi.clearHistory();

        pm.endNote(new Pitch(1));

        String history = midi.history();
        assertEquals(offStateExpected, history);
        System.out.println(history);
    }

    @Test
    public void changeInstrumentTest() {
        assertEquals(pm.getInstrument(), Instrument.PIANO);
        
        while(pm.getInstrument() != Instrument.GUNSHOT)
        {
            pm.changeInstrument();
            System.out.println(pm.getInstrument());
        }
        assertEquals(pm.getInstrument(), Instrument.GUNSHOT);
        
        pm.changeInstrument();
        assertEquals(pm.getInstrument(), Instrument.PIANO);
    }

    @Test
    public void shiftUpTest() {
        int octave = PianoMachine.DEFAULT_OCTAVE;
        int high = octave + Pitch.OCTAVE;
        int vHigh = octave + Pitch.OCTAVE * 2;

        pm.resetOctave();
        assertEquals(pm.getOctave(), octave);

        pm.shiftUp();
        assertEquals(pm.getOctave(), high);

        pm.shiftUp();
        assertEquals(pm.getOctave(), vHigh);

        pm.shiftUp();
        assertEquals(pm.getOctave(), vHigh);
    }

    @Test
    public void shiftDownTest() {
        int octave = PianoMachine.DEFAULT_OCTAVE;
        int low = octave - Pitch.OCTAVE;
        int vLow = octave - Pitch.OCTAVE * 2;

        pm.resetOctave();
        assertEquals(pm.getOctave(), octave);

        pm.shiftDown();
        assertEquals(pm.getOctave(), low);

        pm.shiftDown();
        assertEquals(pm.getOctave(), vLow);

        pm.shiftDown();
        assertEquals(pm.getOctave(), vLow);
    }

    @Test
    public void getOctave() {
        pm.resetOctave();
        assertEquals(pm.getOctave(), PianoMachine.DEFAULT_OCTAVE);
    }

    @Test public void resetOctaveTest() {
        int octave = PianoMachine.DEFAULT_OCTAVE;
        int high = octave + Pitch.OCTAVE;
        
        pm.resetOctave();
        assertEquals(pm.getOctave(), octave);

        pm.shiftUp();
        assertEquals(pm.getOctave(), high);

        pm.resetOctave();
        assertEquals(pm.getOctave(), octave);
    }
}
