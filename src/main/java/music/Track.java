package music;

public abstract class Track {
    private byte numberOfBars;

    public byte[][][] midiSequenceArray;

    protected Track(byte numberOfBarsIn){
        numberOfBars = numberOfBarsIn;



    }



    protected void setMidiSequence(byte[][][] midiSequenceArrayIn){

        midiSequenceArray = midiSequenceArrayIn;



    }


}
