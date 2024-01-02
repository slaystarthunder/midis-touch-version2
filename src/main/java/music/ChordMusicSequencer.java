package music;

public class ChordMusicSequencer extends MusicSequencer {
    private byte numberOfBars;
    byte[][][] midiSequenceArray;

    public ChordMusicSequencer(byte numberOfBarsIn, byte[][][] midiSequenceArrayIn){
        super(numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);




    }




}