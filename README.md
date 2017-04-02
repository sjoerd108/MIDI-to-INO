# MIDI-to-INO
A program that allows you to convert a track from a MIDI file to Arduino code.

This program allows you to take a MIDI file and convert it into Arduino code. The code generated can run stand alone on a Arduino and is able to play a chiptune based on the notes in the specified MIDI file. 

The inspiration to make this program came from this tutorial: ahttps://www.arduino.cc/en/Tutorial/toneMelody

If you want to try it out then here is how you setup your circuit:
Take a speaker and connect its positive terminal to pin 8 and its negative terminal to GND.
https://www.arduino.cc/en/uploads/Tutorial/Tone_Fritzing.png

The Arduino is really limited when it comes to playing tunes this way so if you are browsing the Internet for midi files then make sure you pick simple files that have the melody you want on the same track. This means not all songs will sound good. If your song is very long you might also run into a problem which is the limited memory for global variables. The program lets you select a maximum number of notes before cutting out to stay within the memory boundaries of the Arduino memory.

This was a quick weekend project to get my mind off my other work. 
I normally don't share these sketches but I am pretty happy with this project and I do plan to update it.
