package sjoerd.miditoino;

public class Note {

    public int note;
    public int octave;
    public long startTick;
    public long noteTime;

    public Note(int note, int octave, long startTick, long noteTime) {
        this.note = note;
        this.octave = octave;
        this.startTick = startTick;
        this.noteTime = noteTime;
    }

}
