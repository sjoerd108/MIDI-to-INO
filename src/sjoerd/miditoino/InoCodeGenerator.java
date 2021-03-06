/*
MIT License

Copyright (c) 2017 Sjoerd Dal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

*/

package sjoerd.miditoino;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.TreeMap;

class InoCodeGenerator {

    private TreeMap<Long, Note> simplifiedMidiFile;
    private Component component;
    private static final String[] NOTE_NAMES = {"NOTE_C", "NOTE_CS", "NOTE_D", "NOTE_DS", "NOTE_E", "NOTE_F", "NOTE_FS", "NOTE_G", "NOTE_GS", "NOTE_A", "NOTE_AS", "NOTE_B"};

    InoCodeGenerator(TreeMap<Long, Note> simplifiedMidiFile, Component component) {
        this.simplifiedMidiFile = simplifiedMidiFile;
        this.component = component;
    }

    void generateCode(boolean loop, int speedMultiplier) throws StringIndexOutOfBoundsException, IOException {

        StringBuilder noteSequence = new StringBuilder();
        StringBuilder noteDurations = new StringBuilder();
        StringBuilder arduinoCode = new StringBuilder();
        String finalInoCode;

        if(speedMultiplier == 0) speedMultiplier = 1;

        for(Map.Entry<Long, Note> entry : simplifiedMidiFile.entrySet()){
            Note note = entry.getValue();
            noteSequence.append(NOTE_NAMES[note.note]).append(note.octave).append(",");
            noteDurations.append(note.noteTime).append(",");
        }

        arduinoCode.append(ArduinoCodeTemplate.pitches);
        arduinoCode.append(ArduinoCodeTemplate.emptyCodeBody
                .replace("{NOTES}", noteSequence.toString())
                .replace("{DURATIONS}", noteDurations.toString()));

        noteSequence.setLength(noteSequence.length() - 1); //Remove the last comma to create a valid array.
        noteDurations.setLength(noteDurations.length() - 1); //Remove the last comma to create a valid array.

        if(loop) finalInoCode = arduinoCode.toString().replace("{LOOP}", ArduinoCodeTemplate.melodyLoop).replace("{RUN_ONCE}", "");
        else finalInoCode = arduinoCode.toString().replace("{RUN_ONCE}", ArduinoCodeTemplate.melodyLoop).replace("{LOOP}", "");

        if ( speedMultiplier < 0) finalInoCode = finalInoCode.replace("{SPEED}", " / " + -speedMultiplier);
        else finalInoCode = finalInoCode.replace("{SPEED}", " * " + speedMultiplier);

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Arduino Code Files", "ino"));
        int result = jFileChooser.showSaveDialog(component);

        if (result == JFileChooser.APPROVE_OPTION) {
            File saveLocation = jFileChooser.getSelectedFile();
            if(!saveLocation.getName().endsWith(".ino"))
                saveLocation = new File(saveLocation.getPath() + ".ino");
            if (saveLocation.exists()) {
                int dialogResult = JOptionPane.showConfirmDialog(component, "Do you want to overwrite the file?", "File already exist", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if(dialogResult == JOptionPane.OK_OPTION) {
                    saveLocation.delete();
                    Files.write(Paths.get(saveLocation.getPath()), finalInoCode.getBytes(), StandardOpenOption.CREATE);
                }
            } else {
                Files.write(Paths.get(saveLocation.getPath()), finalInoCode.getBytes(), StandardOpenOption.CREATE);
            }
        }
    }
}
