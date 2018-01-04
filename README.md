# CS171-Projects
These are coding assignments from my Introduction to Computer Science CS171 class. 

## Project 1 - Fahrenheit Celsius Converter 
Create a class called Fahrenheit, containing one static method named toCelsius. The method, toCelsius should take as a parameter one double, degrees in Fahrenheit, and return a double, the equivalent degrees in Celsius. 

See file: *Fahrenheit.java*

## Project 2 - Guessing Game - 
You are to think of a number between from 1000-9999. Let's say you chose 3536.

The program will display - "I guess your number is 1219. How many digits did I guess correctly?".

You are to type how many digits 1219 matches with your number 3536. In this case with the number 1219, there are no matches. So you are to type "0" in the program.

The program may then display - "I guess your number is 6519. How many digits did I guess correctly?".

In this case with the number 6519, the 5 in the hundreds place is the same as in 3536. So you are to type "1" in the program.

Now the program may display "I guess your number is 1516. How many digits did I guess correctly?

In this case with the number 1516, the 5 in the hundreds place and the 6 in the ones place are the same as in 3536. So you are to type "2" in the program.

The program will continue to do these steps until it has finally guessed your number, 3536. The program usually arrives at your specific number in around 8-12 guesses. Hope you have fun with this program! :D

See file: *GuessingGame.java*

## Project 3 - LayOff Problem

In this exercise you implement a solver for a reducing ordered selection problem. 

As an IT engineer in a small company, Mary has just found early that the company plans to lay off one member of her department containing N people every week until only the best is left. In order to find the best logician they are letting everyone choose a position on a list numbered from 1 to N. They will be removing every Kth person, until only one remains. Mary knows she can come up with the best solution to this problem quickly and keep her job, your job is to do likewise.

For example, if N=7 and K=2, then the firing order is 2 4 6 1 5 3 7, so Mary should choose position 7. Or if N=6 and K=3, then the firing order is 3 6 4 2 5 1, and Mary should stand at position 1.

You should write a class Mary containing a method

public static int mary(int N, int K)

which returns the position Mary should choose. For example mary(7,2) should return 7, and mary(6,3) should return 1.

See file: *Mary.java*

## Project 4 - Linked Deque

In this exercise, you finish a doubly-linked list implementation of Deque (see Deque.java). Deque is a double-ended queue interface, a simplification of java.util.Deque.

As a starting point, you are given a partial solution LinkedDeque.java. This file already compiles, but as given it is only a singly-linked list, which is inadequate (a singly-linked list cannot do removeLast in constant time). You need to revise the file to make it a full doubly-linked list implementation.

See file: *Deque.java, LinkedDeque.java*



