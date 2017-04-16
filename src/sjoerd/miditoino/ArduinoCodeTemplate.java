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

/*
This Arduino code template is based on the toneMelody tutorial from arduino.cc
this tutorial can be found at https://www.arduino.cc/en/Tutorial/toneMelody
 */

class ArduinoCodeTemplate {

    static final String emptyCodeBody =
            "int melody[] = {\n" +
                    "{NOTES}\n" +
                    "};\n" +
                    "\n" +
                    "int noteDurations[] = {\n" +
                    "{DURATIONS}\n" +
                    "};\n" +
                    "\n" +
                    "void setup(){\n" +
                    "{RUN_ONCE}\n" +
                    "}\n" +
                    "\n" +
                    "void loop(){\n" +
                    "{LOOP}\n" +
                    "}";

    static final String melodyLoop =
            "  for (int thisNote = 0; sizeof(melody); thisNote++) {\n" +
                    "    \n" +
                    "    int noteDuration = noteDurations[thisNote] {SPEED};\n" +
                    "    tone(8, melody[thisNote],noteDuration);\n" +
                    "\n" +
                    "    int pauseBetweenNotes = noteDuration * 1.30;\n" +
                    "    delay(pauseBetweenNotes);\n" +
                    "  }";

    static final String pitches =
            "#define NOTE_B0  31\n" +
                    "#define NOTE_C1  33\n" +
                    "#define NOTE_CS1 35\n" +
                    "#define NOTE_D1  37\n" +
                    "#define NOTE_DS1 39\n" +
                    "#define NOTE_E1  41\n" +
                    "#define NOTE_F1  44\n" +
                    "#define NOTE_FS1 46\n" +
                    "#define NOTE_G1  49\n" +
                    "#define NOTE_GS1 52\n" +
                    "#define NOTE_A1  55\n" +
                    "#define NOTE_AS1 58\n" +
                    "#define NOTE_B1  62\n" +
                    "#define NOTE_C2  65\n" +
                    "#define NOTE_CS2 69\n" +
                    "#define NOTE_D2  73\n" +
                    "#define NOTE_DS2 78\n" +
                    "#define NOTE_E2  82\n" +
                    "#define NOTE_F2  87\n" +
                    "#define NOTE_FS2 93\n" +
                    "#define NOTE_G2  98\n" +
                    "#define NOTE_GS2 104\n" +
                    "#define NOTE_A2  110\n" +
                    "#define NOTE_AS2 117\n" +
                    "#define NOTE_B2  123\n" +
                    "#define NOTE_C3  131\n" +
                    "#define NOTE_CS3 139\n" +
                    "#define NOTE_D3  147\n" +
                    "#define NOTE_DS3 156\n" +
                    "#define NOTE_E3  165\n" +
                    "#define NOTE_F3  175\n" +
                    "#define NOTE_FS3 185\n" +
                    "#define NOTE_G3  196\n" +
                    "#define NOTE_GS3 208\n" +
                    "#define NOTE_A3  220\n" +
                    "#define NOTE_AS3 233\n" +
                    "#define NOTE_B3  247\n" +
                    "#define NOTE_C4  262\n" +
                    "#define NOTE_CS4 277\n" +
                    "#define NOTE_D4  294\n" +
                    "#define NOTE_DS4 311\n" +
                    "#define NOTE_E4  330\n" +
                    "#define NOTE_F4  349\n" +
                    "#define NOTE_FS4 370\n" +
                    "#define NOTE_G4  392\n" +
                    "#define NOTE_GS4 415\n" +
                    "#define NOTE_A4  440\n" +
                    "#define NOTE_AS4 466\n" +
                    "#define NOTE_B4  494\n" +
                    "#define NOTE_C5  523\n" +
                    "#define NOTE_CS5 554\n" +
                    "#define NOTE_D5  587\n" +
                    "#define NOTE_DS5 622\n" +
                    "#define NOTE_E5  659\n" +
                    "#define NOTE_F5  698\n" +
                    "#define NOTE_FS5 740\n" +
                    "#define NOTE_G5  784\n" +
                    "#define NOTE_GS5 831\n" +
                    "#define NOTE_A5  880\n" +
                    "#define NOTE_AS5 932\n" +
                    "#define NOTE_B5  988\n" +
                    "#define NOTE_C6  1047\n" +
                    "#define NOTE_CS6 1109\n" +
                    "#define NOTE_D6  1175\n" +
                    "#define NOTE_DS6 1245\n" +
                    "#define NOTE_E6  1319\n" +
                    "#define NOTE_F6  1397\n" +
                    "#define NOTE_FS6 1480\n" +
                    "#define NOTE_G6  1568\n" +
                    "#define NOTE_GS6 1661\n" +
                    "#define NOTE_A6  1760\n" +
                    "#define NOTE_AS6 1865\n" +
                    "#define NOTE_B6  1976\n" +
                    "#define NOTE_C7  2093\n" +
                    "#define NOTE_CS7 2217\n" +
                    "#define NOTE_D7  2349\n" +
                    "#define NOTE_DS7 2489\n" +
                    "#define NOTE_E7  2637\n" +
                    "#define NOTE_F7  2794\n" +
                    "#define NOTE_FS7 2960\n" +
                    "#define NOTE_G7  3136\n" +
                    "#define NOTE_GS7 3322\n" +
                    "#define NOTE_A7  3520\n" +
                    "#define NOTE_AS7 3729\n" +
                    "#define NOTE_B7  3951\n" +
                    "#define NOTE_C8  4186\n" +
                    "#define NOTE_CS8 4435\n" +
                    "#define NOTE_D8  4699\n" +
                    "#define NOTE_DS8 4978\n\n";
}
