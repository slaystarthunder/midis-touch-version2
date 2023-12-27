package music;

public abstract class Track {
    private byte key, bpm, numberOfBars;

    public byte[][][] midiSequenceArray;

    protected Track(byte keyIn, byte bpmIn, byte numberOfBarsIn){
        key = keyIn;
        bpm = bpmIn;
        numberOfBars = numberOfBarsIn;



    }



    protected void setMidiSequence(byte[][][] midiSequenceArrayIn){

        midiSequenceArray = midiSequenceArrayIn;



    }


}
