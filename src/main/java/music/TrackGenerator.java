package music;

import java.util.Arrays;
import java.util.Random;


public class TrackGenerator {

    // generates an array of Tracks (ChordTrack and MelodyTrack) based on bpm, key, and numberOfBars
    public static MusicSequencer[] generateTracks(byte numberOfBars) {
        return new MusicSequencer[]{
                new ChordMusicSequencer(numberOfBars, TrackGenerator.makeChordTrack(numberOfBars)),
                new MelodyMusicSequencer(numberOfBars, TrackGenerator.makeMelodyTrack(numberOfBars))
        };
    }

    // generates a chord progression track
    private static byte[][][] makeChordTrack(byte numberOfBars) {
        // defining the intervals of a major chord progression
        byte[] majorScaleIntervals = {2, 2, 1, 2, 2, 2, 1};

        // array to select root note for chords from index in scale array
        byte[] majorChordProgression = {0, 3, 4};

        // scale array covering 1 octave
        byte[] scaleArray = new byte[7];

        // define a root note at 0 which is transposed upon play
        scaleArray[0] = 0;

        // filling the scale array according to the pattern defined in majorScaleIntervals
        for (int i = 1; i < scaleArray.length; i++) {
            scaleArray[i] = (byte) (scaleArray[i - 1] + majorScaleIntervals[i - 1]);
        }

        // number of bars, 4 chords per bar, 3 notes in a chord
        byte[][][] midiSequenceArray = new byte[numberOfBars][4][3];

        // generating chords for each bar
        for (int bar = 0; bar < numberOfBars; bar++) {
            for (int beat = 0; beat < 4; beat++) {
                int chordIndex = majorChordProgression[beat % majorChordProgression.length];
                byte root = scaleArray[chordIndex % scaleArray.length];
                byte third = scaleArray[(chordIndex + 2) % scaleArray.length];
                byte fifth = scaleArray[(chordIndex + 4) % scaleArray.length];

                midiSequenceArray[bar][beat] = new byte[]{root, third, fifth};

            }
        }

        System.out.println(Arrays.deepToString(midiSequenceArray));
        return midiSequenceArray;
    }

    // generates a melody track
    private static byte[][][] makeMelodyTrack(byte numberOfBars) {
        // initialize random object for easy melody creation
        Random rand = new Random();

        // using an agnostic major scale which is transposed upon play
        byte[] majorScale = {12, 14, 16, 17, 19, 21, 23};

        // initializing 3d byte array to store the melody in, [noOfBars][beatsInBar][notesPerBar]
        byte[][][] midiSequenceArray = new byte[numberOfBars][4][1];

        // generate melody notes for each bar, inner loop generates notes for each beat
        for (int bar = 0; bar < numberOfBars; bar++) {
            for (int beat = 0; beat < 4; beat++) {
                byte note = majorScale[rand.nextInt(majorScale.length)];
                midiSequenceArray[bar][beat][0] = note;
            }
        }

        System.out.println(Arrays.deepToString(midiSequenceArray));
        return midiSequenceArray;
    }

    // checks if a note is part of the scale
    private static boolean checkIfValid(byte[] scaleArray, byte note) {
        boolean isValid = false;
        int counter = 0;

        while (!isValid && counter < scaleArray.length) {
            isValid = (note == scaleArray[counter]);
            counter++;
        }

        return isValid;
    }

    // checks if a note needs transposition
    private static boolean needTransposition(byte[] scaleArray, byte note) {
        return (note > scaleArray[scaleArray.length - 1]);
    }

    // transposes the scale
    private static byte[] transposeScale(byte[] scaleArray, byte currentKey) {
        byte[] transposedScaleArray = new byte[scaleArray.length];
        for (int k = 0; k < scaleArray.length; k++) {
            transposedScaleArray[k] = (byte) (scaleArray[k] + currentKey);
        }

        return transposedScaleArray;
    }

}