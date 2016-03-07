Ben King
bking11@u.rochester.edu
No Partner

In this project I implemented Donald Knuth's algorithm for playing the game MasterMind as the code-breaker, without the advanced guessing. The code runs as follows:
1. Use recursion to create an array of Guess (which contains String[]) with all possible codes 
2. Pick the first guess which is not flagged as invalid
3. Read the number of Black and White Pegs from the user
	If 4 Black Pegs: unflag all codes, return to step 2
4. Iterate through each code in the array and test it against the guess:
	If: it would have the same Black and White Pegs as the guess do nothing
	Else: it has a different number of Black and White Pegs as the guess flag the Guess as impossible
5. Return to Step 2.


The Code contains 2 files: MasterMind and Guess. Guess contains a code, methods for checking the numbers of black and white pegs, and a boolean flag for an invalid guess. MasterMind contains a constructor and all the methods outlined by the assignment. The main method of MasterMind allows a user to play infinite games of MasterMind against the computer. It is able to work with command line arguments if given, and falls back on a 4 position RGBA code. 