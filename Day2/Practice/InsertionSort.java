package Day2.Practice;
//counts the number of copies and the number of comparisons it makes during a sort and
//displays the totals.
public class InsertionSort {
    public static void printArray(int[] a)
	{
		int j;
		for(j=0; j<a.length; j++)
			System.out.print(a[j] + ", ");
		System.out.println("\n");
	}
	
	public static void insertionSort(int[] a)
	{
		int curr_item_index, insert_index, curr_item,comp=0,copy=0;
		for (curr_item_index=1; curr_item_index<a.length; curr_item_index++)
		{
			
			curr_item = a[curr_item_index];
			for(insert_index=curr_item_index;insert_index>0;insert_index--)
			{
			 comp++;
			if(curr_item<a[insert_index-1])
			{
				a[insert_index] = a[insert_index-1];
				copy++;
			}
			else
				break;
			}	
			a[insert_index] = curr_item;
			System.out.println("Iteration " + (curr_item_index) + "==>");
			
			printArray(a);
		}
		System.out.println("Total Comparisons:"+comp);
		System.out.println("Total Copies:"+copy);
	}
	public static void main(String[] args)
	{
		int[] array1 = {10,9,8,7,6,5,4};
		int[] array = {0,-1,5,4,9,10};
		System.out.println("Almost Sorted Array:");
		System.out.println("Before Sorting===>");
		printArray(array);
		insertionSort(array);
		System.out.println("After Sorting===>");
		printArray(array);
		
		System.out.println("Inversely Sorted Array:");
		System.out.println("Before Sorting===>");
		printArray(array1);
		insertionSort(array1);
		System.out.println("After Sorting===>");
		printArray(array1);
		
		//The efficiency of this algorithm for almost sorted data is less than O(N^2)
		//For reverse array the comparisons and copies are almost same
		
	} 
}
