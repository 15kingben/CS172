//@author Ben King

public class MakeChange {
	public static int[][] table;
	
	public static void main(String[] args){
		int changeTarget = 473;
		
		
		
		if(args.length == 1)
			changeTarget = Integer.parseInt(args[0]);
		
		int[] denominations = { 100000, 5000, 2000, 1000, 500, 100, 25,
				 10, 5, 1 };
		
		int[] tokens = makeChange(changeTarget, denominations);
		for(int i = 0; i < tokens.length; i++)
			System.out.println(tokens[i]);
	}


	public static int [] makeChange(int amount, int[] denominations){
		table = new int[amount + 1][];
		int[] counts = new int[denominations.length + 1];
		recMakeChange(amount, denominations, counts);
		
		int[] tokens = new int[ counts[counts.length - 1] ];
		int count = 0;
		for(int i = 0; i < counts.length -1; i++){
			for(int j = 0; j < counts[i]; j++){
				tokens[count++] = denominations[i];
			}
		}
		return tokens;
		
	}
	
	static boolean recMakeChange(int amount, int[] denominations, int[] counts){
		if(amount == 0){
			return true;
		}
		if(amount < 0)
			return false;
		
		if(table[amount] != null){
			for(int k = 0; k < table[amount].length; k++)
				counts[k] = table[amount][k];
			return true;
		}
		
		int temp[] = new int[counts.length];
		int best[] = new int[counts.length];
		
		best[best.length - 1] = amount + 1;
		boolean b = false;
		for(int d = 0; d < denominations.length; d++){
			for(int i = 0; i < temp.length; i++)
				temp[i] = 0;
			
			b = recMakeChange(amount - denominations[d], denominations, temp);
			if(b){
				if(temp[temp.length - 1] < best[best.length - 1]){
					temp[d]++;
					temp[temp.length - 1]++;
					for(int z = 0; z < best.length; z++)
						best[z] = temp[z];
				}
			}
			
		}
		if(b){
			table[amount] = new int[counts.length];
			for(int z = 0; z < best.length; z++)
				table[amount][z] = counts[z] = best[z];
			return true;
		}
		return false;
		
		
		
	}


}