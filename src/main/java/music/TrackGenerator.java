package music;

import java.util.Arrays;
import java.util.Random;

/*What do we do?


    This is how we get the tones in the next chord
    We try to take a perfect third, if that´s not in our scale go down halfstep.
    Then perfect fifth, -||- ascend a half step.

    The next chord is generated looking at chord progressions. This tells us where
    the next basenote lies. From this we use the conventional way to get the actual
    chord notes.

    Mode major chord progressions
    1 0-3-4-0 -> Distances from basetone, changes in chords for chord progression
    2 0-4-5-3
    3 0-5-3-4
    4 1-4-0-0
    5 0-4-3-0
   A scale has notes. Those notes each have a corresponding chord.
   Each of those chords has to be comprised of notes from the scale (-> Movement if not in scale)



        b/t = bpm => 4*numberOfBars / t = bpm

       4*numberOfBars/ bpm = t (minutes)

        We get a key and a mode (major), from this we obtain our origin point via the key
        and then the movement distances from that origin point from the mode.

        C  D E F G A B
        Check if you can go 4 steps and land on a scale note if not you
        descend 1. Then check from OP and try and go 7 steps, if ascend 1 step.

       Major Mode: OP+2+2+1+2+2+2*

       1) OP (Key) -> Scale is obtained from *. C-D-E-F-G-A-B
       2) I try to take a +4-note from OP, if that´s not in the scale I take OP+3 instead
       3)I try to go +7 from OP. If that´s not in scale (op +7 is not part of (scale as midinumbers)) ascend 1
       <=> op + 8
       4)Use chord progression with basenote as originpoint
       op + 3 -> New base tone
       (op+3) +4 if() -> 4|3 (op+7)
       (op+3) + 7 if () ->
        over and over til filled all bars


      Melody: Take key
      Make scale (same mode as ChordTrack)
      opForChordTrack + 12 = op
      Randomizer with interval (0,12)
      op + rand -> Some MIDI-index -> Must be contained in scale
      Generate 4 singular such tones for 1 bar
     */
public class TrackGenerator {

    // generates an array of Tracks (ChordTrack and MelodyTrack) based on bpm, key, and numberOfBars
    public static Track[] generateTracks(byte numberOfBars) {
        return new Track[]{
                new ChordTrack(numberOfBars, TrackGenerator.makeChordTrack(numberOfBars)),
                new MelodyTrack(numberOfBars, TrackGenerator.makeMelodyTrack(numberOfBars))
        };
    }

    // generates a chord progression track
    private static byte[][][] makeChordTrack(byte numberOfBars) {
        // defining the intervals of a major chord progression
        byte[] majorScaleIntervals = {2, 2, 1, 2, 2, 2, 1};

        // array to select root note for chords from index in scale array
        byte[] majorChordProgression = new byte[]{0, 3, 4};

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
        byte[] majorScale = {0, 2, 4, 5, 7, 11};

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