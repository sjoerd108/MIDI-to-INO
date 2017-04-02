package sjoerd.miditoino;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class Menu extends JFrame implements ActionListener{

    private JPanel jPanel;
    private JButton openMidiFileButton;
    private JButton convertButton;
    private JSpinner track;
    private JSpinner notes;
    private JSlider speed;
    private JCheckBox loopSongCheckBox;
    private JFrame jFrame;
    private JFileChooser jFileChooser;
    private File midi;

    public void setupGui(){
        jFrame = new JFrame(); // Create frame.

        jFrame.add(jPanel);
        jFrame.setTitle("Midi to Ino");
        jFrame.requestFocus();
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(500, 400);

        notes.setValue(250);

        openMidiFileButton.addActionListener(this);
        convertButton.addActionListener(this);

        jFrame.setVisible(true); //Call after all components are added to frame
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == openMidiFileButton){
            jFileChooser = new JFileChooser();
            jFileChooser.setFileFilter(new FileNameExtensionFilter("MIDI files","mid", "midi"));
            int result = jFileChooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION) {
                midi = jFileChooser.getSelectedFile();
            }
        } else if(actionEvent.getSource() == convertButton) {
            convert();
        }
    }

    private void convert(){
        MidiReader midiReader = new MidiReader();
        try {
            TreeMap<Long, Note> simpleMidi = midiReader.createSimpleMidi(midi, (int) track.getValue(), (int) notes.getValue());
            InoCodeGenerator inoCodeGenerator = new InoCodeGenerator(simpleMidi, this);
            inoCodeGenerator.generateCode(loopSongCheckBox.isSelected(), speed.getValue());
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(this,"You did not specify any file.");
        } catch (InvalidMidiDataException e) {
            JOptionPane.showMessageDialog(this,"Invalid/Corrupt MIDI file.","Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,"The specified file does not exist.");
        } catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this,"Specified track does not contain any notes, try another one.","Error", JOptionPane.ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(this,"Specified track does not exist, try another one.","Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
