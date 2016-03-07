//@author Ben King
public class Combinatorics2 {

	public static void main(String[] args){
		System.out.println("Count the number of anagrams of the following words:\n\t(a) error  " + anagramCaller("error") + "\n\t(b) street  " + anagramCaller("street") + "\n\t(c) allele  " + anagramCaller("allele") + "\n\t (d)Mississippi  " + anagramCaller("Mississippi") );
		System.out.println("How many ways can we distribute: \n\t(a) six apples to four children  " + indisDist(6,4) + "\n\t(b) four apples to six children  " +indisDist(4,6));
		System.out.println("How many ways can we distribute \n\t(a) six apples and three pears to five children  " + distDist(9,5,"AAAAAAPPP") + "\n\t (b)two apples, five pears and six bananas to three children  " + distDist(13,3,"AAPPPPPBBBBBB"));
	}
	
	
	public static int distDist(int objects, int bins, String items){
		return permutations(bins + objects - 1) / (permutations(bins - 1) * anagram(items));
	}
	
	public static int indisDist(int objects, int bins){
		return (int) unorderedSelection(objects + bins -1, objects);
	}
	
	public static int anagramCaller(String word){
		return permutations(word.length())/anagram(word);
	}
	
	public static int anagram(String word){
		if(word.length() == 0){
			return 1;
		}else{
			String target = word.substring(0,1);
			int beforeLength = word.length();
			String newWord = word.replaceAll(target, "");
			int afterLength = newWord.length();
			return permutations(beforeLength-afterLength) * anagram(newWord);
		}
	}
	
	//below methods from LAB 4
	public static long unorderedSelection(int n, int k) {
		if(k == 0 || n == k)
			return 1;
		else
			return unorderedSelection(n-1,k) + unorderedSelection(n-1,k-1);
	}


	public static int countAssign(int items, int values){
		return (int) Math.pow(values, items);
	}
	
	public static int permutations(int n){
		int total = 1;
		while(n > 1){
			total*= n--;
		}
		return total;
	}
	
	public static long orderedSelection(int n, int k){
		long total = 1;
		for(int i = n; i > n-k; i--){
			total *= i;
		}
		return total;
	}
}
