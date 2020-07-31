# Error Correcting Encoder-Decoder

Error Correcting Encoder-Decoder project in Java. From JetBrains Academy (hyperskill.org).

### About

Errors are inevitable both in life and in the digital world. Errors occur here and there and everywhere, and in this 
project you will not only imitate this process, but also learn how to cope with errors. It is a chance to experience 
what early developers had to cope with at the dawn of the computer era. Low-level programming is fun and insightful: 
try it and you’ll see.

##### Stage 1

Write a program that creates errors in the input text, 1 random error per 3 characters.
i.e. "abc" can be "\*bc" or "a\*c" or "ab\*", where * is a random character.

##### Stage 2: Symbol-level correction code
Update program to now take the input text and encode the message by tripling all the symbols. Simulate transmission of
the text by introducing errors into it (as done in stage 1), and finally decoding the message. Output the result of each
step.

##### Stage 3: Bit-level error emulator
Write a program which will read from one file ("send.txt") and write to another ("received.txt") while changing one bit
in every byte. Introduces using Java Streams for more than reading/writing to the console. Also makes use of bit-wise
operators to manipulate the data.