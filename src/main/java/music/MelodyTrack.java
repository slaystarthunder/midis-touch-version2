package music;

public class MelodyTrack extends Track {
    private byte key, numberOfBars;
    byte[][][] midiSequenceArray;

    public MelodyTrack(byte keyIn, byte numberOfBarsIn, byte[][][] midiSequenceArrayIn) {
        super(keyIn, numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);


    }
}