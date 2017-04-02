package sjoerd.miditoino;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.util.TreeMap;

public class MidiReader {

    public TreeMap<Long, Note> createSimpleMidi (File midi, int trackNumber, int maxNotes) throws InvalidMidiDataException, IOException, NullPointerException, ArrayIndexOutOfBoundsException {

        if (midi == null) throw new NullPointerException(); //File was moved or deleted.
        Sequence sequence = MidiSystem.getSequence(midi);
        Track track = sequence.getTracks()[trackNumber];

        long lastNoteOn = 1;

        TreeMap<Long, Note> simplifiedMidiFile = new TreeMap<>(); //Midi tick, Note class

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
                        simplifiedMidiFile.put(event.getTick(), note);
                        if(i == maxNotes) break;
                    }

                    lastNoteOn = event.getTick();
                }
            }
        }

        return simplifiedMidiFile;
    }
}