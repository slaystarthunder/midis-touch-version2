package music;

public class ChordTrack extends Track {
    private byte key, bpm, numberOfBars;
    byte[][][] midiSequenceArray;

    public ChordTrack(byte keyIn, byte bpmIn, byte numberOfBarsIn, byte[][][] midiSequenceArrayIn){
        super(bpmIn,keyIn,numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);




    }




}