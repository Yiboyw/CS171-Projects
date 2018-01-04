/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS. ___Yibo Wang___
*/

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class GuessingGame {
		
	public int numberGuesses; //this stores the number of guesses
	public int theGuess; //this stores the random number that is guessed
	ArrayList<Integer> allNumbers; //this is an arraylist that stores all numbers from 1000-9999
	
	
	public GuessingGame() {
		//initialization
		
		numberGuesses = 0; //set the number of guesses to zero
		
		allNumbers = new ArrayList<Integer>(); //creates an ArrayList of integers

			for(int i=1000; i<=9999; i++){ //adds numbers from 1000-9999 to the ArrayList
				allNumbers.add(i);
			}				
	}

	public int myGuessIs() { // this method returns guesses
		
		if(!allNumbers.isEmpty()){ //If the arrayList is not empty, generate random numbers
	
		Random rand = new Random();
		Integer randomInt = allNumbers.get(rand.nextInt(allNumbers.size())); //randomly selects a number from the ArrayList
		
		numberGuesses++; //each time you update the guess, add one to the number of times myGuessIs() is called
		theGuess = randomInt;
		return randomInt; //returns a number randomly selected from the ArrayList
		}
		else {
			return -1; //if the arraylist is empty, return -1
		}
	}
	
	public int totalNumGuesses() { 
		return numberGuesses;
		//this returns the total number of guesses taken -  the total number of calls to myGuessIs()
	}
 
	public void updateMyGuess(int nmatches) {
	
		int a = theGuess/1000; //this gets the thousands digit of the random number generated
		int b = (theGuess/100)%10; //this gets the hundreds digit of the random number generated
		int c = (theGuess/10)%10; //this gets the tenths digit of the random number generated
		int d = theGuess%10;//this gets the ones digit of the random number generated
		
		
		int count = 0;
		// updates the guess based on the number of matching digits claimed by the user

			for(int i=0; i < allNumbers.size() ; i++){
				
					if( (allNumbers.get(i)/1000) == a){ // if the thousands digit of the number in the ArrayList matches, increase count. 
						count=count+1;
					}
			
					if( ((allNumbers.get(i)/100)%10) == b){ // if the hundreds digit of the number in the ArrayList matches, increase count. 
						count=count+1;	
					}
					
					if( ((allNumbers.get(i)/10)%10) == c){ // if the tenths digit of the number in the ArrayList matches, increase count. 
						count=count+1;
					}
					
					if ( ((allNumbers.get(i)%10) == d) ){ // if the ones digit of the number in the ArrayList matches, increase count. 
						count=count+1;
					}
					
					if(count != nmatches){
						allNumbers.remove(i); //if the number in the ArrayList does not have the same number of matches that the user inputed, remove this number. 
						//System.out.println(allNumbers.remove(i));
					i--;//move the pointer back after the number in the ArrayList is removed. 
					
					}
					count = 0; //reset the counter
		}
	}
	
	// you shouldn't need to change the main function
	public static void main(String[] args) {

		GuessingGame gamer = new GuessingGame( );
  
		JOptionPane.showMessageDialog(null, "Think of a number between 1000 and 9999.\n Click OK when you are ready...", "Let's play a game", JOptionPane.INFORMATION_MESSAGE);
		int numMatches = 0;
		int myguess = 0;
		
		do {
			myguess = gamer.myGuessIs();
			if (myguess == -1) {
				JOptionPane.showMessageDialog(null, "I don't think your number exists.\n I could be wrong though...", "Mistake", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
			String userInput = JOptionPane.showInputDialog("I guess your number is " + myguess + ". How many digits did I guess correctly?");
			// quit if the user input nothing (such as pressed ESC)
			if (userInput == null)
				System.exit(0);
			// parse user input, pop up a warning message if the input is invalid
			try {
				numMatches = Integer.parseInt(userInput.trim());
			}
			catch(Exception exception) {
				JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			// the number of matches must be between 0 and 4
			if (numMatches < 0 || numMatches > 4) {
				JOptionPane.showMessageDialog(null, "Your input is invalid. Please enter a number between 0 and 4", "Warning", JOptionPane.WARNING_MESSAGE);
				continue;
			}
			if (numMatches == 4)
				break;
			// update based on user input
			gamer.updateMyGuess(numMatches);
			
		} while (true);
		
		// the game ends when the user says all 4 digits are correct
		System.out.println("Aha, I got it, your number is " + myguess + ".");
		System.out.println("I did it in " + gamer.totalNumGuesses() + " turns.");
	}
}
