//@author Ben King
import java.util.Arrays;
public class SortTest {
	
	private static int count;
	
	public static void main(String args[]) {
		
		
		
		
		long startTime, endTime, elapsedTime;
		
		int size = Integer.parseInt(args[0]);
		
		Integer [] a = new Integer[size];
		Integer [] b = new Integer[size];

		for (int i = 0; i < size; i++) {
			a[i] = b[i] = (int)(Math.random() * 100);
		}


		System.out.println(Arrays.toString(a));
		
		count = 0;
		startTime = System.currentTimeMillis();
		quickSort(a);
		endTime = System.currentTimeMillis();
		elapsedTime = endTime - startTime;
		
		System.out.println(Arrays.toString(a));
		System.out.println("quick sort took "+ count + " moves to sort "
				+ size + " items");
		System.out.println("\t in : "+ elapsedTime + " millesec"); 

		// Reset count and array
		count = 0;
		for (int i = 0; i < size; i++)
			a[i] = b[i];
		
		}
	
	
	public static <AnyType extends Comparable<? super AnyType>>
	void bubblesort(AnyType[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - 1; j++) {
				if (a[j].compareTo(a[j + 1]) > 0) {
					AnyType tmp = a[j];
					count++;
					a[j] = a[j + 1];
					count++;
					a[j + 1] = tmp;
					count++;
				}
			}
		}
	}
	
	//Insertion Sort from Weiss p. 273
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType [] a )
	{
		int j;
		
		for(int p = 1; p < a.length; p++)
		{
			AnyType tmp = a[p];
			count++;
			for( j = p; j > 0 && tmp.compareTo(a [j - 1] ) < 0; j--){
				a[j] = a[j-1];
				count++;
			}
			a[j] = tmp;
			count++;
		}
		
		
	}
	
	
	//Shell Sort from Weiss pg. 275
	public static <AnyType extends Comparable<? super AnyType>> void shellSort( AnyType [] a)
	{
		int j;
		
		for(int gap = a.length / 2-1; gap > 0; gap = (gap + 1) / 2 - 1){
			for(int i = gap; i < a.length; i++)
			{
				AnyType tmp = a[i];
				count++;
				for(j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap){
					count++;
					a[j] = a[j - gap];
				}
				count++;
				a[j] = tmp;
			}
		}
	}
	
	
	public static <AnyType extends Comparable<? super AnyType>> void javaSort (AnyType [] a){
		Arrays.sort(a);
	}
	

	
	
	
	
	
	//quick sort adapted from project 3
	public static <AnyType extends Comparable<? super AnyType>> void quickSort (AnyType [] a){
	
	
		quickSort(a, 0, a.length - 1);

	}

	private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType[] a, int start , int end) {
		if(start >= end)
			return;
	
		int pivot = partition(a, start , end);
		quickSort(a, start , pivot - 1);
		quickSort(a, pivot + 1, end);
	
	}

	private static <AnyType extends Comparable<? super AnyType>> int partition(AnyType[] arr, int start, int end) {
		int med;
		AnyType median = median(arr[start], arr[end], arr[(start + end)/2]);
		if(median.equals(arr[start]))
			med = start;
		else if(median.equals(arr[end]))
			med = end;
		else
			med = (start + end)/2;
		
		swapInArray(arr, med, end);
		count++;
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
			count++;
		}
		
		swapInArray(arr, i, med);
		count++;
		return i;
		
	}

	
	private static <AnyType extends Comparable<? super AnyType>> void swapInArray(AnyType[] arr, int a, int b){
		AnyType temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
		count += 3;
	}


private static <AnyType extends Comparable<? super AnyType>> AnyType median(AnyType a, AnyType b, AnyType c) {
	if(a.compareTo(b) > 0){
		AnyType temp = a;
		a = b;
		b = temp;
		count += 3;
	}
	
	if(b.compareTo(c) > 0){
		AnyType temp = b;
		b = c;
		c = temp;
		count += 3;
	}
	
	if(a.compareTo(b) > 0){
		AnyType temp = a;
		a = b;
		b = temp;	
		count += 3;
	}
	
	return b;
}
	
	
	
}