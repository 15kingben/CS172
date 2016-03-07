//@author Ben King


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MyHashTable {

	
	
	
	String[] table;
	int tableSize;
	int itemCount;
	
	
	
	public static void main(String [] args) throws FileNotFoundException{
		MyHashTable table = new MyHashTable(13);
		
		/*table.insert("Fred");
		table.insert("Anthony");//collision
		table.insert("Cal");
		table.insert("Cal");
		table.insert("Sarah");
		table.insert("John");*/

		
		//punctuation counts for separate words
		Scanner file = new Scanner(new File("input.txt"));
		while(file.hasNext())
			table.insert(file.next());
		
		
		
		table.print();
		System.out.println("\nCapacity: " + table.getCapacity());
		System.out.println("Unique Items: " + table.getUniqueItems());
		System.out.println("Load Factor: " + table.getLoadFactor());
	
	
	}
	
	
	public int getCapacity(){
		return tableSize;
	}
	
	public int getUniqueItems(){
		return itemCount;
	}
	
	public double getLoadFactor(){		
		return (double)itemCount/(double)tableSize;
	}
	
	
	public MyHashTable(int tableSize){
		table = new String[tableSize];
		this.tableSize = tableSize;
	}
	
	public String[] getTable(){
		return table;
	}
	
	public boolean inTable(String ins){
		int hash = hash(ins, tableSize);
		
		while(hash < tableSize && table[hash] != null){
			if(hash >= tableSize){
				hash =0;
				continue;
			}
			if(table[hash++].equals(ins))
				return true;
		}
		return false;
	}
	
	
	public void insert(String ins){
		if(inTable(ins))
			return;
		
		
		
		int hash = hash(ins, tableSize);
		if(table[hash] != null){
			
			int index = hash;
			while(table[index] != null){
				index++;
				if(index >= tableSize){
					index = 0;
					continue;
				}
			}
			hash = index;
		}	
			
		table[hash] = ins;
		
		itemCount++;
		
		if(getLoadFactor() >= .5){
			tableSize = tableSize * 2 + 1;
			String[] newTable = new String[tableSize];
			itemCount = 0;
			cpArray(table, newTable);
		}
	}
	
	
	private void cpArray(String[] from, String[] to){
		//System.out.println("From: " + from.length + " To: " + to.length);
		
		table = to;
		for(int i = 0; i < from.length; i++){
			if(from[i] != null)
				insert(from[i]);
		}
	}
	
	//Weiss 5.2
	public static int hash( String key, int tableSize)
	{
		int hashVal = 0;
		
		for(int i = 0; i < key.length(); i++){
			hashVal = 37* hashVal + key.charAt(i);
		}
		
		hashVal %= tableSize;
		if(hashVal < 0)
			hashVal += tableSize;
		
		return hashVal;
	}
	
	public void print(){
		for(String s: table)
			if(s != null)
				System.out.println(s);
	}
}
