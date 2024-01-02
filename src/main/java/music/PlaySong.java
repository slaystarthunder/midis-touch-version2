package music;

import javax.sound.midi.*;

public class PlaySong {

    public void playSong(byte[][][][] tracks, int bpm, int baseMidiNote, int chordNoteLength, int melodyNoteLength) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            Sequence sequence = new Sequence(Sequence.PPQ, 4);

            // loop through each track and add it to the sequence
            for (int i = 0; i < tracks.length; i++) {
                Track musicSequencer = sequence.createTrack();
                // use chord length for first track, melody length for others
                int noteLength = i == 0 ? chordNoteLength : melodyNoteLength;
                addNotesToTrack(musicSequencer, tracks[i], baseMidiNote, noteLength);
            }

            // set tempo based on BPM
            setTempo(sequence, bpm);

            // start playback
            sequencer.setSequence(sequence);
            sequencer.start();

            // wait for playback to finish
            while (sequencer.isOpen()) {
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNotesToTrack(Track musicSequencer, byte[][][] notes, int baseMidiNote, int noteLength) {
        int tick = 0;
        for (byte[][] bar : notes) {
            for (byte[] chord : bar) {
                for (byte note : chord) {
                    int midiNote = note + baseMidiNote;
                    musicSequencer.add(createNoteEvent(ShortMessage.NOTE_ON, midiNote, 100, tick));
                    musicSequencer.add(createNoteEvent(ShortMessage.NOTE_OFF, midiNote, 100, tick + 16));
                }
                tick += noteLength;
            }
        }
    }

    private void setTempo(Sequence sequence, int bpm) {
        try {
            MetaMessage tempoMeta = new MetaMessage();
            int tempo = 60000000 / bpm;
            tempoMeta.setMessage(0x51, new byte[]{(byte) (tempo >> 16), (byte) (tempo >> 8), (byte) tempo}, 3);
            sequence.getTracks()[0].add(new MidiEvent(tempoMeta, 0));
        } catch (InvalidMidiDataException e) {
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
