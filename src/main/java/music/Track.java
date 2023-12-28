package music;

public abstract class Track {
    private byte key, numberOfBars;

    public byte[][][] midiSequenceArray;

    protected Track(byte keyIn, byte numberOfBarsIn){
        key = keyIn;
        numberOfBars = numberOfBarsIn;



    }



    protected void setMidiSequence(byte[][][] midiSequenceArrayIn){

        midiSequenceArray = midiSequenceArrayIn;



    }


}
