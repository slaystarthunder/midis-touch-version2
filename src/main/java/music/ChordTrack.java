package music;

public class ChordTrack extends Track {
    private byte numberOfBars;
    byte[][][] midiSequenceArray;

    public ChordTrack(byte numberOfBarsIn, byte[][][] midiSequenceArrayIn){
        super(numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);




    }




}