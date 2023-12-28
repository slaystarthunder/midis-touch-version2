package music;

public class MelodyTrack extends Track {
    private byte numberOfBars;
    byte[][][] midiSequenceArray;

    public MelodyTrack(byte numberOfBarsIn, byte[][][] midiSequenceArrayIn) {
        super(numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);


    }
}