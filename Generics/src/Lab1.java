//@author Ben KIng
public class Lab1 {

	public static void main(String[] args) {
		
		//Block copied from lab
		Integer [] intArry = {1, 2, 3, 4, 5 };
		Double [] doubArry = {1.1, 2.2, 3.3, 4.4};
		Character [] charArray = {'H','E','L', 'L', 'O' };
		String [] strArray = {"once", "upon", "a", "time" };
//		printarray(intArry);
//		printarray(doubArry);
//		printarray(charArray);
//		printarray(strArray);
//		
		System.out.println("max Integer is: " + getMax(intArry));
		System.out.println("max Double is: " + getMax(doubArry));
		System.out.println("max Character is: " + getMax(charArray));
		System.out.println("max String is: " + getMax(strArray));

		//end block copied from lab
	}
		
		//Question 1: Object class
		/*private static void printarray(Object[] arr){
			for(Object obj : arr)
				System.out.print(obj.toString() +" ");
			System.out.println();
			
		}*/
		
		
		//Question 2: Method Overloading
		/*public static void printarray(Integer[] intArry){
			for(Integer i : intArry)
				System.out.print(i.toString() + " " );
			System.out.println();
		}
		
		public static void printarray(Double[] intArry){
			for(Double i : intArry)
				System.out.print(i.toString() + " " );
			System.out.println();
		}
		public static void printarray(Character[] intArry){
			for(Character i : intArry)
				System.out.print(i.toString() + " " );
			System.out.println();
		}
		public static void printarray(String[] intArry){
			for(String i : intArry)
				System.out.print(i.toString() + " " );
			System.out.println();
		}*/
	
		//Question 3: Generic solution
		/*public static <T> void printarray(T[] arr){
			for(T i : arr)
				System.out.print(i.toString() + " " );
			System.out.println();
			
		}*/
		
		//Question 4: Comparable Type
		//Error Messages:
		//Comparable is a raw type. References to generic type Comparable<T> should be parameterized	Lab1.java	/Generics/src	line 67	Java Problem
		//Type safety: The method compareTo(Object) belongs to the raw type Comparable. References to generic type Comparable<T> should be parameterized	Lab1.java	/Generics/src	line 68	Java Problem
		
		/*public static Comparable getMax(Comparable [] arr){
			Comparable max = arr[0];
			for(Comparable i : arr){
				if(i.compareTo(max) > 0){
					max = i;
				}				
			}
			return max;			
		}*/
	
		//Question 5: Comparable Generics
		public static <T extends Comparable<? super T>> T getMax(T[] arr){
			T max = arr[0];
			for(T i : arr){
				if(i.compareTo(max) > 0){
					max = i;
				}
			}
			return max;
			
		}
	
	
		
		
	

}
