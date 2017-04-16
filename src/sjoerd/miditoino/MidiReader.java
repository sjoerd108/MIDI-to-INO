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

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

class MidiReader {

    TreeMap<Long, Note> createSimpleMidi(File midi, int trackNumber, int maxNotes) throws InvalidMidiDataException, IOException, NullPointerException, ArrayIndexOutOfBoundsException {

        if (midi == null) throw new NullPointerException(); //File was moved or deleted.
        Sequence sequence = MidiSystem.getSequence(midi);
        Track track = sequence.getTracks()[trackNumber];

        long lastNoteOn = 1;

        TreeMap<Long, Note> simplifiedMidiFile = new TreeMap<>(); //Midi tick, Note class
        Note lastNote = null;

        for (int i = 0; i < track.size(); i++) {
            MidiEvent event = track.get(i);
            MidiMessage message = event.getMessage();
            if (message instanceof ShortMessage) {
                ShortMessage sm = (ShortMessage) message;
                if (sm.getCommand() == ShortMessage.NOTE_ON) {
                    int key = sm.getData1();
                    int octave = (key / 12) - 1;
                    int noteInt = key % 12;

                    if(event.getTick() - lastNoteOn <= 1) continue;

                    Note note = new Note(noteInt, octave, event.getTick(), event.getTick() -  lastNoteOn);
                    if(!simplifiedMidiFile.containsKey(event.getTick())) { //The Arduino is unable to play two notes at once. If two notes start at the same time the program will pick one of them.
                        simplifiedMidiFile.put(lastNote.startTick, lastNote);
                        lastNote = note;
                        if(i == maxNotes) break;
                    }

                    lastNoteOn = event.getTick();
                }
            }
        }

        return simplifiedMidiFile;
    }
}