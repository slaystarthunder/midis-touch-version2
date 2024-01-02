package music;

public abstract class MusicSequencer {
    protected byte numberOfBars;
    protected int noteLength;

    public byte[][][] midiSequenceArray;

    protected MusicSequencer(byte numberOfBarsIn, int noteLengthIn){
        this.numberOfBars = numberOfBarsIn;
        this.noteLength = noteLengthIn;

    }

    protected void setMidiSequence(byte[][][] midiSequenceArrayIn){

        this.midiSequenceArray = midiSequenceArrayIn;

    }

}
