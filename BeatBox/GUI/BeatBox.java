import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.*;

public class BeatBox {

    JPanel mainPanel;
    ArrayList<JCheckBox> checkBoxList;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    JFrame theFrame;

    String[] instrumentNames = {
            "Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", 
            "Crash Cymbal", "Hand Clap", "High Tom", "Hi Bongo", 
            "Maracas", "Whistle", "Low Conga", "Cowbell", 
            "Vibraslap", "Low-mid Tom", "High Agogo", "Open Hi Conga"
    };
    int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

    public static void main(String[] args) {
        new BeatBox().buildGUI();
    }

    public void buildGUI() {
        theFrame = new JFrame("Cyber BeatBox");
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();
        JPanel background = new JPanel(layout);
        background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        checkBoxList = new ArrayList<>();
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("Start");
        start.addActionListener(new MyStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("Stop");
        stop.addActionListener(new MyStopListener());
        buttonBox.add(stop);

        JButton upTemp = new JButton("Temp Up");
        upTemp.addActionListener(new MyUpTempListener());
        buttonBox.add(upTemp);

        JButton downTemp = new JButton("Temp Down");
        downTemp.addActionListener(new MyDownTempListener());
        buttonBox.add(downTemp);

        JButton clear = new JButton("Clear");
        clear.addActionListener(new MyClearListener());
        buttonBox.add(clear);

        JButton serializeIt = new JButton("Save");
        serializeIt.addActionListener(new MySendListener());
        buttonBox.add(serializeIt);

        JButton restore = new JButton("Load");
        restore.addActionListener(new MyReadInListener());
        buttonBox.add(restore);

        Box nameBox = new Box(BoxLayout.Y_AXIS);
        for (String instrumentName : instrumentNames) {
            nameBox.add(new Label(instrumentName));
        }

        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.WEST, nameBox);

        theFrame.getContentPane().add(background);

        GridLayout grid = new GridLayout(16, 16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);
        background.add(BorderLayout.CENTER, mainPanel);

        for (int i = 0; i < 256; i++) {
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }

        theFrame.setBounds(50, 50, 300, 300);
        theFrame.pack();
        theFrame.setVisible(true);

        setUpMidi();
    }

    public void setUpMidi() {
        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ, 4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTrackAndStart() {
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for (int i = 0; i < 16; i++) {
            int[] trackList = new int[16];
            int key = instruments[i];

            for (int j = 0; j < 16; j++) {
                JCheckBox jc = checkBoxList.get(j + (16 * i));
                trackList[j] = jc.isSelected() ? key : 0;
            }

            makeTracks(trackList);
            track.add(makeEvent(176, 1, 127, 0, 16));
        }

        track.add(makeEvent(192, 9, 1, 0, 15));
        try {
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class MyStartListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            buildTrackAndStart();
        }
    }

    public class MyStopListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            sequencer.stop();
        }
    }

    public class MyUpTempListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    public class MyDownTempListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 0.97));
        }
    }

    public class MyClearListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            for (JCheckBox checkbox : checkBoxList) {
                checkbox.setSelected(false);
            }
        }
    }

    public class MySendListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(theFrame);
            saveFile(fileSave.getSelectedFile());
        }
    }

    public class MyReadInListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(theFrame);
            loadFile(fileOpen.getSelectedFile());
        }
    }

    private void saveFile(File file) {
        boolean[] checkboxState = new boolean[256];

        for (int i = 0; i < 256; i++) {
            JCheckBox check = checkBoxList.get(i);
            checkboxState[i] = check.isSelected();
        }

        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            os.writeObject(checkboxState);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void loadFile(File file) {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            boolean[] checkboxState = (boolean[]) is.readObject();

            for (int i = 0; i < 256; i++) {
                JCheckBox check = checkBoxList.get(i);
                check.setSelected(checkboxState[i]);
            }

            sequencer.stop();
            buildTrackAndStart();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void makeTracks(int[] list) {
        for (int i = 0; i < 16; i++) {
            int key = list[i];
            if (key != 0) {
                track.add(makeEvent(144, 9, key, 100, i));
                track.add(makeEvent(128, 9, key, 100, i + 1));
            }
        }
    }

    public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
        MidiEvent event = null;
        try {
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return event;
    }
}