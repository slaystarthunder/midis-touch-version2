package utilities;

public class MidiUtils {

    // Method to convert note names to MIDI values
    public static int noteToMidiValue(String noteName) {
        return switch (noteName) {
            case "C" -> 60;
            case "C#" -> 61;
            case "D" -> 62;
            case "D#" -> 63;
            case "E" -> 64;
            case "F" -> 65;
            case "F#" -> 66;
            case "G" -> 67;
            case "G#" -> 68;
            case "A" -> 69;
            case "A#" -> 70;
            case "B" -> 71;
            default -> 60; // Default to Middle C
        };
    }
}
