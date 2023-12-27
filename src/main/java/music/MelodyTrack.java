package music;

public class MelodyTrack extends Track {
    private byte key, bpm, numberOfBars;
    byte[][][] midiSequenceArray;

    public MelodyTrack(byte keyIn, byte bpmIn, byte numberOfBarsIn, byte[][][] midiSequenceArrayIn) {
        super(bpmIn, keyIn, numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);


    }
}