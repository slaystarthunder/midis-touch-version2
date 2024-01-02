package com.example.midistouchmalfunction54;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import music.PlaySong;
import music.MusicSequencer;
import music.TrackGenerator;
import utilities.MidiUtils;

public class HelloController {

    // define constans for note length:

    private static final int MELODY_NOTE_LENGTH = 2;
    private static final int CHORD_NOTE_LENGTH = 16;

    @FXML
    private Spinner<Integer> bpmSpinner;
    @FXML
    private Spinner<String> keySpinner;

    // field to store the generated tracks
    private MusicSequencer[] generatedMusicSequencers;

    // instance of PlaySong class to play the song
    private PlaySong player = new PlaySong();

    @FXML
    public void initialize() {
        bpmSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 200, 120));
        keySpinner.setValueFactory(new SpinnerValueFactory.ListSpinnerValueFactory<>(
                javafx.collections.FXCollections.observableArrayList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")));
        // default value in GUI spinner
        keySpinner.getValueFactory().setValue("C");

    }

    @FXML
    protected void onGenerateButtonClick() {
        int bpm = bpmSpinner.getValue();
        // converts key string to MIDI note byte value
        byte key = (byte) MidiUtils.noteToMidiValue(keySpinner.getValue());

        // generate track with bpm and key (NOT YET set, TO DO!), 4 bars assumed right now
        generatedMusicSequencers = TrackGenerator.generateTracks((byte) 4, CHORD_NOTE_LENGTH, MELODY_NOTE_LENGTH);
    }

    @FXML
    protected void onPlayButtonClick() {
        if (generatedMusicSequencers != null && generatedMusicSequencers.length > 1) {
            byte[][][] chordTrack = generatedMusicSequencers[0].midiSequenceArray;
            byte[][][] melodyTrack = generatedMusicSequencers[1].midiSequenceArray;
            // use the BPM value from the spinner
            int bpm = bpmSpinner.getValue();
            // get base MIDI note from the spinner
            int baseMidiNote = MidiUtils.noteToMidiValue(keySpinner.getValue());


            // play the music using the stored tracks and BPM

            player.playSong(new byte[][][][]{chordTrack, melodyTrack}, bpm, baseMidiNote, CHORD_NOTE_LENGTH, MELODY_NOTE_LENGTH);
            System.out.println("BPM value from GUI: " + bpm + ", Key: " + baseMidiNote);
        }
    }
}
