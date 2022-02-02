package Day3.Practice;

public class SelectionSort {
    void sort(int arr[]) 
	{ 
		int n = arr.length; 

		// One by one move boundary of unsorted subarray 
		for (int i = 0; i < n-1; i++) 
		{ 
			// Find the minimum element in unsorted array 
			int smallest = i; 
			for (int j = i+1; j < n; j++) 
				if (arr[j] < arr[smallest]) 
					smallest = j; 

			// Swap the found minimum element with the first 
			// element 
			int temp = arr[smallest]; 
			arr[smallest] = arr[i]; 
			arr[i] = temp; 
			
			System.out.println("Iteration " + (i+1) + "==>");
			this.printArray(arr);
		} 
	} 


	// Prints the array 
	void printArray(int arr[]) 
	{ 
		int n = arr.length; 
		for (int i=0; i<n; ++i) 
			System.out.print(arr[i]+" "); 
		System.out.println(); 
	} 

	// Driver code to test above 
	public static void main(String args[]) 
	{ 
		SelectionSort ob = new SelectionSort(); 
		//int arr[] = {-4, 10, 5, 17, 0, -9, 25, 12};
		int arr[] = {2, -1, 5, 0};
		ob.sort(arr); 
		System.out.println("Sorted array"); 
		ob.printArray(arr); 
	} 
}
