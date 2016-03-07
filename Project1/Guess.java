//@author Ben King


import java.util.HashMap;

public class Guess {
	boolean isGuess;
	String[] colors;
	public String[] code;
	
	public Guess (String[] colors, int numPositions){
		isGuess = true;
		this.colors = colors;
		code = new String[numPositions];
	}
	
	public String printGuess(){
		String s = "";
		for(String c : code){
			s += c + " " ;
		}
		return s + (isGuess ? "1" : "0");
	}
	
	public int getBlackPegs(String[] newCode){
		int count = 0;
		for(int i = 0; i < code.length; i++){
			if(code[i].equals(newCode[i])){
				count++;
			}
		}
		return count;
	}
	
	public int getWhitePegs(String[] newCode, int blackPegs){
		HashMap<String, Integer> numMatches = new HashMap<>();
		
		for(String c:colors){
			numMatches.put(c, 0);
		}
		
		int count = 0;
		for(String c:code){
			numMatches.put(c,numMatches.get(c) + 1);
		}
		
		for(String c:newCode){
			if(numMatches.get(c) > 0){
				count++;
				numMatches.put(c,numMatches.get(c) -1);
			}
			
		}
		
		
		
		count -= blackPegs;
		
		return count;
	}
}
