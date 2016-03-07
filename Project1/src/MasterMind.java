//@author Ben King

import java.util.Scanner;

public class MasterMind {
	
	
	
	public Guess[] guesses;
	public int numLeft;
	
	//constructor creates all code permutations
	public MasterMind(String[] tokencolors, int positions) {
		guesses = new Guess[(int) Math.pow(tokencolors.length,positions)];
		for(int i = 0; i < guesses.length; i++){
			guesses[i] =  new Guess(tokencolors, positions);
		}
		
		fillAllPermutations( tokencolors, positions);
		
	}
	
// The constructor must take an array of strings as input
// For instance, if:
// tokencolors ==
// {"ORANGE", "PINK","RED", "BLUE", "GREEN", "YELLOW"};
// then the engine returns guesses from this set,
// However, it would be just as valid to pass in
// tokencolors ==
// {"SNEZZY", "SLEEPY", "DIRTY", "FILTHY", "GRUMPY", "HAPPY", "DOC"};
// So, the number of elements that your system guesses
// over is determined by the array
 
	 
	 //keep track of our position in the array of possible guesses
	 public int guessIndex = 0;
	 
	 private void fillAllPermutations(String[] tokencolors, int positions) {
		fillPerm(new String[positions], tokencolors, 0);
		
	}
	 //recursively create all code permutations
	 private void fillPerm(String[] base, String[] colors, int index){
		 if(index == base.length -1){
			 for(String c : colors){
				 base[index] = c;
				 guesses[guessIndex++].code = copyArray(base);
			 }
		 }else{
			 for(String c :colors){
				 base[index] = c;
				 fillPerm(copyArray(base), colors, index +1);
			 }
		 }
	 }
	 
	 
	 
	 
	 //utility method for copying array
	 public String[] copyArray(String[] cp){
		 String[] newArray = new String[cp.length];
		 for(int i = 0; i < newArray.length; i++){
			 newArray[i] = cp[i];
		 }
		 return newArray;
	 }



	 //process the player response
	public void response(String[] guess, int colorsRightPositionWrong, int positionsAndColorRight) {
		int blackPegs = positionsAndColorRight;
		int whitePegs = colorsRightPositionWrong;
		//eliminate all impossible codes
		for(Guess g: guesses){
			int testBlack = g.getBlackPegs(guess);
			int testWhite = g.getWhitePegs(guess, testBlack);
			if(testBlack == blackPegs && testWhite == whitePegs){
				//still possible
			}else{
				g.isGuess = false;
			}
		}
		
	}

	 
	 
	 	 public void newGame() {
	 		 
	 		 for(Guess g:guesses)
	 			 g.isGuess = true;
	 
	 	 } // Reset the game
	 
	 
	 public String [] nextMove() {
		 for(Guess g: guesses){
			 if(g.isGuess)
				 return g.code;
		 }
		 return null;
	} // return the next guess

	 
	 
	 
	 
	 //tester method
	 public static void main(String[] args){
		 Scanner in = new Scanner(System.in);
		 MasterMind m;
		 
		 if(args.length > 0){
			 m = new MasterMind(args, args.length);
		 }else{
		 m = new MasterMind( new String[]{"R", "G" , "B","A"}, 4);
		 }
		 
		 //		 for(Guess g:m.guesses){
//			 System.out.println(g.printGuess());
//		 }
//		 
//		 String[] guess1 = new String[]{"R","G","A","B"};
//		 for(Guess g:m.guesses){
//			 System.out.println(g.printGuess() + " " + g.getBlackPegs(guess1) + " " + g.getWhitePegs(guess1, g.getBlackPegs(guess1)));
//		 }
		 
		 
		 System.out.println("Why hello there. Let's play MasterMind.\nPlease think up a code...");
		 while(true){
			 System.out.println("My Guess is: " + printCode(m.nextMove()));
			 System.out.println("What is your response:");
			 System.out.println("How many black pegs?");
			 int blackPegs = in.nextInt();
			 if(blackPegs == 4){
				 System.out.println("I Win! Let's play again.");
				 System.out.println("Think up a code");
				 m.newGame();
				 continue;
			 }
			 System.out.println("How many white pegs?");
			 int whitePegs = in.nextInt();
			 m.response(m.nextMove(), whitePegs, blackPegs);
			 
			 
		 }
		 
		 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public static String printCode(String[] args){
		String s = "";
		 for(String arg:args){
			 s+=arg + " ";
		 }
		 s=s.substring(0, s.length() -1);
		 return s;
	 }
	 
}
