package music;

public abstract class MusicSequencer {
    private byte numberOfBars;

    public byte[][][] midiSequenceArray;

    protected MusicSequencer(byte numberOfBarsIn){
        numberOfBars = numberOfBarsIn;



    }



    protected void setMidiSequence(byte[][][] midiSequenceArrayIn){

        midiSequenceArray = midiSequenceArrayIn;



    }


}
