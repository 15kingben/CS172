//@author Ben King


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class WordSearch {

	MyHashTable table;
	MyHashTable outputWords;
	String fileWordList;
	String fileInput;
	String fileOutput;
	String[][] puzzle;
	String[] sortedOutput;
	int puzzleSize;
	
	
	//************SORT OUTPUT*************
	
	//java WordFinder wordlist.txt puzzle.txt foundWords.txt
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		
		WordSearch ws = new WordSearch(args[0], args[1], args[2]);
		ws.readDictionary();
		ws.parseTable();
		ws.searchLines();
		ws.sortOutput();
		//ws.printSortedOutput();
		ws.writeToOutputFile();
		
		//System.out.println("\n\n" + median("c", "b", "a"));
	}
	
	private void writeToOutputFile() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter out = new PrintWriter(fileOutput, "UTF-8");
		for(String s : sortedOutput)
			out.println(s);
			
		out.close();
	}

	private void printSortedOutput() {
		for(String s : sortedOutput){
			System.out.println(s);
		}
		
	}

	private void sortOutput() {
		int c = 0;
		for(String p : outputWords.getTable())
			if(p != null)
				c++;
		
		
		sortedOutput = new String[c];
		
		
		
		int count = 0;
		for(String s : outputWords.getTable()){
			if(s != null)
				sortedOutput[count++] = s;
		}
		quickSort(sortedOutput, 0, sortedOutput.length - 1);
	}

	private void quickSort(String[] arr, int start , int end) {
		if(start >= end)
			return;
		
		int pivot = partition(arr, start , end);
		quickSort(arr, start , pivot - 1);
		quickSort(arr, pivot + 1, end);
		
	}

	private int partition(String[] arr, int start, int end) {
		int med;
		String median = median(arr[start], arr[end], arr[(start + end)/2]);
		if(median.equals(arr[start]))
			med = start;
		else if(median.equals(arr[end]))
			med = end;
		else
			med = (start + end)/2;
		
		swapInArray(arr, med, end);
		med = end;
		int i = start; int j = end - 1;
		while(i <= j){
			while(arr[i].compareTo( arr[med]) <= 0){
				i++;
				if(i > j)
					break;
			}
			while(arr[j].compareTo(arr[med]) >= 0){
				j--;
				if(i > j)
					break;
			}
			if(i > j)
				break;
			swapInArray(arr, i, j);
		}
		
		swapInArray(arr, i, med);
		return i;
		
	}

	
	private void swapInArray(String[] arr, int a, int b){
		String temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	
	private static String median(String a, String b, String c) {
		if(a.compareTo(b) > 0){
			String temp = a;
			a = b;
			b = temp;			
		}
		
		if(b.compareTo(c) > 0){
			String temp = b;
			b = c;
			c = temp;
		}
		
		if(a.compareTo(b) > 0){
			String temp = a;
			a = b;
			b = temp;			
		}
		
		return b;
	}

	private void searchLines() {
		
		String horiz = "";
		String vert = "";
		for(int i = 0; i < puzzleSize; i++){
			for(int j = 0; j < puzzleSize; j++){
				horiz = horiz.concat(puzzle[i][j]);
				vert = vert.concat(puzzle[j][i]);
			}
			findWordsInString(horiz);
			findWordsInString(vert);
			findWordsInString(reverseString(horiz));
			findWordsInString(reverseString(vert));			
			horiz = "";
			vert = "";
		}
		

		for(int d = 0; d < 2* puzzleSize - 1; d++){
			int i = (d < puzzleSize) ? d : puzzleSize - 1;
			int j = (d < puzzleSize) ? 0 : d - (puzzleSize - 1);
			
			int k = (d < puzzleSize) ? (puzzleSize - 1 - d) : 0;
			int l = (d < puzzleSize) ? 0 : d - (puzzleSize - 1);
			
			while( i < puzzleSize && j < puzzleSize && i > -1 && j > -1){
				horiz = horiz.concat(puzzle[i][j]);
				i--;j++;
			}
			
						
			findWordsInString(horiz);
			findWordsInString(reverseString(horiz));
			horiz = "";
			
			while( k < puzzleSize && l < puzzleSize && k > -1 && l > -1){
				vert=vert.concat(puzzle[k][l]);
				k++;l++;
			}
			
					
			findWordsInString(vert);
			findWordsInString(reverseString(vert));
			vert = "";
		}
	}

	public WordSearch(String dict, String input, String output){
		fileWordList = dict;
		fileInput = input;
		fileOutput = output;
		
		
		table = new MyHashTable(479830);//length of linux.words, although it could expand dynamically if need be
		outputWords = new MyHashTable(100);//use a hash table for output words as well b/c it will handle duplicates
	}
	
	public void readDictionary() throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileWordList));
		
		while(input.hasNext()){
			table.insert(input.next());
		}
	}
	
	public void parseTable() throws FileNotFoundException{
		Scanner input = new Scanner(new File(fileInput));
		
		int size = (int)Math.sqrt(new File(fileInput).length());//size of the word search
		
		puzzleSize = size;
		
		puzzle = new String[size][size];
		
		
		
		
		String[] temps = new String[size];
		
		int count = 0;
		while(input.hasNext()){
			temps[count++] = input.next();
		}
		
		
		
		for(int i =0; i < size; i++){
			for(int j = 0; j <size ; j++){
				puzzle[i][j] = Character.toString(temps[i].charAt(j));
			}
		}
		//print2darray(puzzle);
		
		
	}
	
	
	public void findWordsInString(String line){
		for(int i = 0; i < line.length(); i++){
			for(int j = i; j < line.length() + 1; j++){
				if(table.inTable(line.substring(i, j))){
					outputWords.insert(line.substring(i,j));
				}
			}
		}
	}
	
	
	public void print2darray(String[][] arr){
		for(String[] i : arr){
			for(String j : i){
				System.out.print(j + " " );
			}
			System.out.println();
		}
	}
	


	private static String reverseString(String s){
		String out = "";
		for(int i = s.length() - 1; i > -1; i--){
			out = out.concat(Character.toString(s.charAt(i)));
		}
		return out;
	}
}
