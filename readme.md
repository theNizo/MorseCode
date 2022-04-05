(abandoned until further notice)

## What is it?

This is a program that can read and write files in a binary morse code format that's currently run via a terminal.
It's also able to convert text to morse code and backwards.

## But why?

The intention was to create an even smaller format to store simple text with a limited character set. No fancy characters,
no emojis (imo who needs them anyway), you get the idea. The files should be able to give text in the smallest format possible.

## The morse code format

### What doesn't work great

It's a binary format. One idea to do it is to say 1 equals beep, 0 equals pause, so 1 is dot, 11 is dash, 0 is space between
characters, 00 is space between words. I found out that this is actually longer than the solution I took.

### What I did

We need to resemble dots, dashes and pauses. Pauses were split into spaces between characters and spaces between words.
That gets us:

Characters | Binary
--- | ---
Dot | 00
Dash | 01
Character space | 10
Word space | 11

## Next steps

The next step would be to make a gui for it.

I'm pretty sure it can be ported to a script (it has only one class), but I can't write scripts as of now.
