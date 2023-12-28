package com.example.midistouchmalfunction54;

import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import music.PlaySong;
import music.Track;
import music.TrackGenerator;

public class HelloController {
    @FXML
    private Spinner<Integer> bpmSpinner;
    @FXML
    private Spinner<String> keySpinner;

    // field to store the generated tracks
    private Track[] generatedTracks;

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
        byte bpm = bpmSpinner.getValue().byteValue();
        // converts key string to MIDI note byte value
        byte key = noteToMidiValue(keySpinner.getValue());

        // generate track with bpm and key (NOT YET set, TO DO!), 4 bars assumed right now
        generatedTracks = TrackGenerator.generateTracks((byte) 4);
    }

    @FXML
    protected void onPlayButtonClick() {
        if (generatedTracks != null && generatedTracks.length > 0) {
            byte[][][] chordTrack = generatedTracks[0].midiSequenceArray;
            // this doesn't work yet, but the value will come fom the spinner
            byte bpm = bpmSpinner.getValue().byteValue();

            System.out.println("This is the bpm: " + bpm + "!");
            // play the track with the bpm and chord
            player.playSong(chordTrack, bpm);
        }
    }


    private byte noteToMidiValue(String noteName) {
        // implement this method to convert note names to MIDI values
        // example:
        // if (noteName.equals("C")) return 60;
        // add other notes as needed
        return 60; // default to Middle C octave 4
    }
}
