package music;

public class MelodyMusicSequencer extends MusicSequencer {
    private byte numberOfBars;
    byte[][][] midiSequenceArray;

    public MelodyMusicSequencer(byte numberOfBarsIn, byte[][][] midiSequenceArrayIn) {
        super(numberOfBarsIn);
        super.setMidiSequence(midiSequenceArrayIn);


    }
}