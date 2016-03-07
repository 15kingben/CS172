//@author Ben King
public class TestProgram {

	public static void main(String [] args){
		
		//block from textbook
		//String [] arr = {"ZEBRA", "alligator", "crocodile"};
		//System.out.println(findMax(arr, new CaseInsensitiveCompare()));
		
		Character[] charArr = {'C', 'S' , 'I', 's' ,'F','a'};
		System.out.println(findMax(charArr, new CharacterCompare()));
		
	}
	
	//findMax from Textbook
	public static <AnyType> AnyType findMax(AnyType [] arr, Comparator<? super AnyType> cmp){
		int maxIndex = 0;
		for(int i = 1; i < arr.length; i++){//edit from original text
			if(cmp.compare(arr[i],  arr[maxIndex]) > 0)
				maxIndex = i;
		}
		
		return arr[maxIndex];
		
	}
}



//CaseInsensitiveCompare from Textbook
class CaseInsensitiveCompare implements Comparator<String>
{
	public int compare(String lhs, String rhs){
		return lhs.compareToIgnoreCase(rhs);
	}
}

class CharacterCompare implements Comparator<Character>
{
	public int compare(Character lhs, Character rhs){
		return lhs.compareTo(rhs);
	}
	
}


//Comparator from Textbook
interface Comparator<AnyType>{
	int compare(AnyType lhs, AnyType rhs);
		
}