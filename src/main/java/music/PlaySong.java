package music;

import javax.sound.midi.*;
import javax.sound.midi.Track;

public class PlaySong {

    public void playSong(byte[][][] chords, int bpm) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            int tick = 0;
            // 60 = middle C in the fourth octave as base note (will be changeable later)
            int baseMidiNote = 60;
            for (byte[][] bar : chords) {
                for (byte[] chord : bar) {
                    for (byte note : chord) {
                        // conversion to correct octave in MIDI table
                        int midiNote = note + baseMidiNote;
                        // add NOTE_ON event for each note in the chord
                        track.add(createNoteEvent(ShortMessage.NOTE_ON, midiNote, 100, tick));
                    }
                    // tick increment based on duration of chord
                    tick += 4;
                    for (byte note : chord) {
                        // conversion to correct octave in MIDI table
                        int midiNote = note + baseMidiNote;
                        // adding NOTE_OFF event for each note in chord
                        track.add(createNoteEvent(ShortMessage.NOTE_OFF, midiNote, 100, tick));
                    }
                }
            }

            // set tempo based on BPM with MIDI calculation
            MetaMessage tempoMeta = new MetaMessage();
            int tempo = 60000000 / bpm;
            tempoMeta.setMessage(0x51, new byte[]{(byte) (tempo >> 16), (byte) (tempo >> 8), (byte) tempo}, 3);
            track.add(new MidiEvent(tempoMeta, 0));

            sequencer.addMetaEventListener(meta -> {
                if (meta.getType() == 47) {
                    sequencer.close();
                }
            });

            sequencer.setSequence(sequence);
            sequencer.start();

            while (sequencer.isOpen()) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static MidiEvent createNoteEvent(int command, int note, int velocity, int tick) {
        try {
            ShortMessage message = new ShortMessage();
            message.setMessage(command, 0, note, velocity);
            return new MidiEvent(message, tick);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
            return null;
        }
    }
}
