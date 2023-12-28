package music;

public class ChordTrack extends Track {
    private byte key, numberOfBars;
    byte[][][] midiSequenceArray;

    public ChordTrack(byte keyIn, byte numberOfBarsIn, byte[][][] midiSequenceArrayIn){
        super(keyIn,numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);




    }




}