//@author Ben King
public class Combinatorics {

	public static void main(String[] args){
		System.out.println("How many ways can we paint three houses in any of four colors? " + countAssign(3,4));
		System.out.println("Suppose a computer password consists of eight to ten letters and/or digits. How many different passwords are there? Remember that an upper-case letter is different from a lowercase one. " + countAssign(10, (10+2*26) ));
		System.out.println("If we have 9 players for a baseball team, how many possible batting orders are there? " + permutations(9));
		System.out.println("In a class of 200 students, we wish to elect a President, Vice President, Secretary and Treasurer. In haw many ways can these four officers be selected? " + orderedSelection(200,4));
		System.out.println("How many ways are there to form a sequence of m letters out of the 26 letters, if no letter is allowed to appera more than once for (a) m=3, (b) m=5. " + orderedSelection(26,3));
		System.out.println("How many ways are there to form a sequence of m letters out of the 26 letters, if no letter is allowed to appera more than once for (a) m=3, (b) m=5. " + orderedSelection(26,5));
		System.out.println("In poker, each player is dealt five cards from a 52 card deck. How many different possible hands are there? " + unorderedSelection(52,5));
	}
	
	
	private static long unorderedSelection(int n, int k) {
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
