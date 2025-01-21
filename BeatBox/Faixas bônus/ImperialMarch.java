import javax.sound.midi.*;

public class ImperialMarch {
    public static void main(String[] args) {
        ImperialMarch march = new ImperialMarch();
        march.play();
    }

    public void play() {
        try {
            Sequencer player = MidiSystem.getSequencer();
            player.open();

            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int[] notes = {
                55, 55, 55, 50, 57, 55, 50, 57, 55
            };
            int[] durations = {
                4, 4, 4, 4, 8, 4, 4, 8, 4 
            };

            int tick = 1;
            for (int i = 0; i < notes.length; i++) {
                track.add(createNoteOnEvent(notes[i], tick));
                tick += durations[i];
                track.add(createNoteOffEvent(notes[i], tick));
            }

            player.setSequence(seq);
            player.start();

            Thread.sleep(5000);  
            player.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private MidiEvent createNoteOnEvent(int note, int tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(144, 1, note, 100);
        return new MidiEvent(message, tick);
    }

    private MidiEvent createNoteOffEvent(int note, int tick) throws InvalidMidiDataException {
        ShortMessage message = new ShortMessage();
        message.setMessage(128, 1, note, 100);
        return new MidiEvent(message, tick);
    }
}