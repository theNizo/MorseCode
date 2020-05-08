package com.nizo.morse;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length<2) {
            printTutorial();
        }
        File f = new File(args[1]);
        StringBuilder s = new StringBuilder();
        switch(args[0]) {
            case "w":
                for(int i = 2; i<args.length; i ++) {
                    s.append(args[i] + " ");
                }
                writeFile(f, toByte(textToBin(s.toString().trim())));
                break;
            case "r":
                System.out.println(morseToText(binToMorse(readfile(f))));
                break;
            case "rm":
                for(int i = 2; i<args.length; i ++) {
                    s.append(args[i] + " ");
                }
                System.out.println(binToMorse(readfile(f)));
                break;
            case "wm":
                for(int i = 2; i<args.length; i ++) {
                    s.append(args[i] + " ");
                }
                writeFile(f, toByte(morseToBin(s.toString().trim())));
                break;
            case "tm":
                for(int i = 1; i<args.length; i ++) {
                    s.append(args[i] + " ");
                }
                System.out.println(textToMorse(s.toString().trim()));
                break;
            case "mt":
                for(int i = 1; i<args.length; i ++) {
                    s.append(args[i] + " ");
                }
                System.out.println(morseToText(s.toString().trim()));
                break;
            default:
                printTutorial();
                break;
        }
    }

    public static void printTutorial() {
        System.out.println("Note: This will give back errors if used intentionally wrong. At the current stage, it's a prove of concept.\n\n" +
                "Usage:\n" +
                "<jar> <operation> <file/path/if/needed> <text>" +
                "\n\nOperations:\n" +
                "\tw - write - write text to file\n" +
                "\tr - read - basically a \'cat\' for .mc files\n" +
                "\trm - read morse - cat the files in morse code \n" +
                "\twm - writemorse - write morse code that's written to the file\n" +
                "\t\tDot:                  .\n" +
                "\t\tDash:                 -\n" +
                "\t\tCharacter seperation: [Space]\n" +
                "\t\tWord seperation:      /\n" +
                "\ttm - text > morse - output text in morse code\n" +
                "\tmt - morse > text - enter morse code that's converted to text");
    }

    public static String textToBin(String text) {
        StringBuilder out = new StringBuilder();
        for(char c : text.toLowerCase().toCharArray()) {
            switch (c) {
                case 'a':
                    out.append("000110");
                    break;
                case 'b':
                    out.append("0100000010");
                    break;
                case 'c':
                    out.append("0100010010");
                    break;
                case 'd':
                    out.append("01000010");
                    break;
                case 'e':
                    out.append("0010");
                    break;
                case 'f':
                    out.append("0000010010");
                    break;
                case 'g':
                    out.append("01010010");
                    break;
                case 'h':
                    out.append("0000000010");
                    break;
                case 'i':
                    out.append("000010");
                    break;
                case 'j':
                    out.append("0001010110");
                    break;
                case 'k':
                    out.append("01000110");
                    break;
                case 'l':
                    out.append("0001000010");
                    break;
                case 'm':
                    out.append("010110");
                    break;
                case 'n':
                    out.append("010010");
                    break;
                case 'o':
                    out.append("01010110");
                    break;
                case 'p':
                    out.append("0001010010");
                    break;
                case 'q':
                    out.append("0101000110");
                    break;
                case 'r':
                    out.append("00010010");
                    break;
                case 's':
                    out.append("00000010");
                    break;
                case 't':
                    out.append("0110");
                    break;
                case 'u':
                    out.append("00000110");
                    break;
                case 'v':
                    out.append("0000000110");
                    break;
                case 'w':
                    out.append("00010110");
                    break;
                case 'x':
                    out.append("0100000110");
                    break;
                case 'y':
                    out.append("0100010110");
                    break;
                case 'z':
                    out.append("0101000010");
                    break;
                case '1':
                    out.append("000101010110");
                    break;
                case '2':
                    out.append("000001010110");
                    break;
                case '3':
                    out.append("000000010110");
                    break;
                case '4':
                    out.append("000000000110");
                    break;
                case '5':
                    out.append("000000000010");
                    break;
                case '6':
                    out.append("010000000010");
                    break;
                case '7':
                    out.append("010100000010");
                    break;
                case '8':
                    out.append("010101000010");
                    break;
                case '9':
                    out.append("010101010010");
                    break;
                case '0':
                    out.append("010101010110");
                    break;
                case ',':
                    out.append("01010000010110");
                    break;
                case '.':
                    out.append("00010001000110");
                    break;
                case '!':
                    out.append("01000100010110");
                    break;
                case '?':
                    out.append("00000101000010");
                    break;
                case '-':
                    out.append("01000000000110");
                    break;
                case ' ':
                    out.append("1110");
                    break;
            }
        }
        return out.toString();
    }

    public static String binToMorse(String bits) {
        char[] c = bits.toCharArray();
        StringBuilder out = new StringBuilder();
        for(int i = 0; i < c.length-1; i+=2) {
            switch (c[i]) {
                case '0':
                    switch(c[i+1]) {
                        case '0':
                            out.append('.');
                            break;
                        case '1':
                            out.append('-');
                            break;
                    }
                    break;
                case '1':
                    switch(c[i+1]) {
                        case '0':
                            out.append(' ');
                            break;
                        case '1':
                            out.append('/');
                            break;
                    }
                    break;
            }
        }
        return out.toString();
    }

    public static String morseToText(String text) {
        String[] in = text.split(" ");
        StringBuilder out = new StringBuilder();
        for(String s : in) {
            char[] c = s.toCharArray();
            switch(c.length) {
                case 1:
                    switch (c[0]) {
                        case '.': // .
                            out.append('E');
                            break;
                        case '-': // -
                            out.append('T');
                            break;
                        case '/': // /
                                out.append(' ');
                                break;
                    }
                    break;
                case 2:
                    switch(c[0]) {
                        case '.': // .
                            switch (c[1]) {
                                case '.': // ..
                                    out.append('I');
                                    break;
                                case '-': //
                                    out.append('A');
                                    break;
                            }
                            break;
                        case '-': // -
                            switch ((c[1])) {
                                case '.': // -.
                                    out.append('N');
                                    break;
                                case '-': // --
                                    out.append('M');
                                    break;
                            }
                            break;
                    }
                    break;
                case 3:
                    switch(c[0]) {
                        case '.': // .
                            switch (c[1]) {
                                case '.': // ..
                                    switch (c[2]) {
                                        case '.': // ...
                                            out.append('S');
                                            break;
                                        case '-': // ..-
                                            out.append('U');
                                            break;
                                    }
                                    break;
                                case '-': // .-
                                    switch (c[2]) {
                                        case '.': // .-.
                                            out.append('R');
                                            break;
                                        case '-': // .--
                                            out.append('W');
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case '-': // -
                            switch ((c[1])) {
                                case '.': // -.
                                    switch (c[2]) {
                                        case '.': // -..
                                            out.append('D');
                                            break;
                                        case '-': // -.-
                                            out.append('K');
                                            break;
                                    }
                                    break;
                                case '-': // --
                                    switch (c[2]) {
                                        case '.': // --.
                                            out.append('G');
                                            break;
                                        case '-': // ---
                                            out.append('O');
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
                case 4:
                    switch (c[0]) {
                        case '.': // .
                            switch (c[1]) {
                                case '.': // ..
                                    switch (c[2]) {
                                        case '.': // ...
                                            switch (c[3]) {
                                                case '.': // ....
                                                    out.append('H');
                                                    break;
                                                case '-': // ...-
                                                    out.append('V');
                                                    break;
                                            }
                                            break;
                                        case '-': // ..-
                                            out.append('F');
                                            break;
                                    }
                                    break;
                                case '-': // .-
                                    switch (c[2]) {
                                        case '.': // .-.
                                            out.append('L');
                                            break;
                                        case '-': // .--
                                            switch (c[3]) {
                                                case '.': // .--.
                                                    out.append('P');
                                                    break;
                                                case '-': // .---
                                                    out.append('J');
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case '-': // -
                            switch (c[1]) {
                                case '.': // -.
                                    switch (c[2]) {
                                        case '.': // -..
                                            switch (c[3]) {
                                                case '.': // -...
                                                    out.append('B');
                                                    break;
                                                case '-': // -..-
                                                    out.append('X');
                                                    break;
                                            }
                                            break;
                                        case '-': // -.-
                                            switch (c[3]) {
                                                case '.': // -.-.
                                                    out.append('C');
                                                    break;
                                                case '-': // -.--
                                                    out.append('Y');
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                                case '-': // --
                                    switch (c[3]) {
                                        case '.': // --..
                                            out.append('Z');
                                            break;
                                        case '-': // --.-
                                            out.append('Q');
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
                case 5:
                    switch (c[0]) {
                        case '.': // .
                            switch (c[1]) {
                                case '.': // ..
                                    switch (c[2]) {
                                        case '.': // ...
                                            switch (c[3]) {
                                                case '.': // ....
                                                    switch (c[4]) {
                                                        case '.': // .....
                                                            out.append('5');
                                                            break;
                                                        case '-': // ....-
                                                            out.append('4');
                                                            break;
                                                    }
                                                    break;
                                                case '-': // ...--
                                                    out.append('3');
                                                    break;
                                            }
                                            break;
                                        case '-': // ..---
                                            out.append('2');
                                            break;
                                    }
                                    break;
                                case '-': // .----
                                    out.append('1');
                                    break;
                            }
                            break;
                        case '-': // -
                            switch (c[1]) {
                                case '.': // -..
                                    switch (c[3]) {
                                        case '.': // -....
                                            out.append('6');
                                            break;
                                        case '-': // -..-.
                                            out.append('/');
                                            break;
                                    }
                                    break;
                                case '-': // --
                                    switch (c[2]) {
                                        case '.': // --...
                                            out.append('7');
                                            break;
                                        case '-': // ---
                                            switch (c[3]) {
                                                case '.': // ---..
                                                    out.append('8');
                                                    break;
                                                case '-': // ----
                                                    switch (c[4]) {
                                                        case '.': // ----.
                                                            out.append('9');
                                                            break;
                                                        case '-': // -----
                                                            out.append('0');
                                                            break;
                                                    }
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
                case 6:
                    switch (c[0]) {
                        case '.': // .
                            switch (c[1]) {
                                case '.': // ..--..
                                    out.append('?');
                                    break;
                                case '-': // .-
                                    switch (c[2]) {
                                        case '.': // .-.
                                            switch (c[3]) {
                                                case '.': // .-..-.
                                                    out.append('\"');
                                                    break;
                                                case '-': // .-.-.-
                                                    out.append('.');
                                                    break;
                                            }
                                            break;
                                        case '-': // .--
                                            switch (c[3]) {
                                                case '.': // .--.-.
                                                    out.append('@');
                                                    break;
                                                case '-': // .----.
                                                    out.append('\'');
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                            }
                            break;
                        case '-': // -
                            switch (c[1]) {
                                case '.': // -.
                                    switch (c[2]) {
                                        case '.': // -....-
                                            out.append('-');
                                            break;
                                        case '-': // -.-
                                            switch (c[3]) {
                                                case '.': // -.-.--
                                                    out.append('!');
                                                    break;
                                                case '-': // -.--.-
                                                    out.append(')');
                                                    break;
                                            }
                                            break;
                                    }
                                    break;
                                case '-': // --
                                    switch (c[2]) {
                                        case '.': // --..--
                                            out.append(',');
                                            break;
                                        case '-': // ---...
                                            out.append(':');
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        return out.toString();
    }

    public static String textToMorse(String text) {
        char[] ch = text.toLowerCase().toCharArray();
        StringBuilder out = new StringBuilder();
        for(char c : ch) {
            switch(c) {
                case 'a':
                    out.append(".- ");
                    break;
                case 'b':
                    out.append("-... ");
                    break;
                case 'c':
                    out.append("-.-. ");
                    break;
                case 'd':
                    out.append("-.. ");
                    break;
                case 'e':
                    out.append(". ");
                    break;
                case 'f':
                    out.append("..-. ");
                    break;
                case 'g':
                    out.append("--. ");
                    break;
                case 'h':
                    out.append(".... ");
                    break;
                case 'i':
                    out.append(".. ");
                    break;
                case 'j':
                    out.append(".--- ");
                    break;
                case 'k':
                    out.append("-.- ");
                    break;
                case 'l':
                    out.append(".-.. ");
                    break;
                case 'm':
                    out.append("-- ");
                    break;
                case 'n':
                    out.append("-. ");
                    break;
                case 'o':
                    out.append("--- ");
                    break;
                case 'p':
                    out.append(".--. ");
                    break;
                case 'q':
                    out.append("--.- ");
                    break;
                case 'r':
                    out.append(".-. ");
                    break;
                case 's':
                    out.append("... ");
                    break;
                case 't':
                    out.append("- ");
                    break;
                case 'u':
                    out.append("..- ");
                    break;
                case 'v':
                    out.append("...- ");
                    break;
                case 'w':
                    out.append(".-- ");
                    break;
                case 'x':
                    out.append("-..- ");
                    break;
                case 'y':
                    out.append("-.-- ");
                    break;
                case 'z':
                    out.append("--.. ");
                    break;
                case '1':
                    out.append(".---- ");
                    break;
                case '2':
                    out.append("..--- ");
                    break;
                case '3':
                    out.append("...-- ");
                    break;
                case '4':
                    out.append("....- ");
                    break;
                case '5':
                    out.append("..... ");
                    break;
                case '6':
                    out.append("-.... ");
                    break;
                case '7':
                    out.append("--... ");
                    break;
                case '8':
                    out.append("---.. ");
                    break;
                case '9':
                    out.append("----. ");
                    break;
                case '0':
                    out.append("----- ");
                    break;
                case ' ':
                    out.append("/ ");
                    break;
                case '.':
                    out.append(".-.-.- ");
                    break;
                case ',':
                    out.append("--..-- ");
                    break;
                case '?':
                    out.append("..--.. ");
                    break;
                case '!':
                    out.append("-.-.-- ");
                    break;
                case '-':
                    out.append("-....-");
                    break;
            }
        }
        return out.toString();
    }

    public static String morseToBin(String morse) {
        char[] in = morse.toCharArray();
        StringBuilder out = new StringBuilder();
        for(char c : in) {
            switch (c) {
                case '.':
                    out.append("00");
                    break;
                case '-':
                    out.append("01");
                    break;
                case ' ':
                    out.append("10");
                    break;
                case '/':
                    out.append("11");
                    break;
            }
        }
        return out.toString();
    }
    public static byte[] toByte(String binary) {
        String[] in = binary.split("(?<=\\G........)");
        byte[] out = new byte[in.length];
        for(int i = 0; i < in.length; i++) {
            //out[i] = Byte.parseByte(in[i], 2);
            char[] c = in[i].toCharArray();
            if (c[0] == '1')
                out[i] += 128;
            if (c[1] == '1')
                out[i] += 64;
            try {
                if (c[2] == '1')
                    out[i] += 32;
            } catch (IndexOutOfBoundsException e) {
                out[i]+= 42;
                break;
            }
            if (c[3] == '1')
                out[i] += 16;
            try {
                if (c[4] == '1')
                    out[i] += 8;
            } catch (IndexOutOfBoundsException e) {
                out[i]+=10;
                break;
            }
            if (c[5] == '1')
                out[i] += 4;
            try {
                if (c[6] == '1')
                    out[i] += 2;
            } catch (IndexOutOfBoundsException e) {
                out[i]+=2;
                break;
            }
            if (c[7] == '1')
                out[i] += 1;

        }
        return out;
    }

    public static void writeFile(File file, byte[] bin) throws IOException {
        FileOutputStream out = new FileOutputStream(file);
        out.write(bin, 0, bin.length);
        out.flush();
        out.close();
    }

    public static String readfile(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        byte[] out = new byte[(int) file.length()];
        in.read(out, 0, out.length);
        in.close();
        StringBuilder s = new StringBuilder();
        for(byte b : out) {
            s.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return s.toString();
    }
}