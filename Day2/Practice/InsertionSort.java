package Day2.Practice;
//counts the number of copies and the number of comparisons it makes during a sort and
//displays the totals.
public class InsertionSort {
    public static void insertionSort(int arr[]) { 
    	int n = arr.length, count=0; 
    
    	count += 2; // 2op: i=1, i<n in loop start
    	for (int i = 1; i < n; ++i) {
    
    		int backup = arr[i];    // +1 copy
    		int j = i - 1;  // +1 assign
    		count += 2;
    
            count+=1;   // +1 comp below
    		while (j >= 0) {
    			count+=1;   // +1 comp below
    			if(arr[j] > backup) break;
    
    			arr[j + 1] = arr[j];    // +1 copy
    			j = j - 1;  // +1 assign
    			count += 2;
    		}
    		arr[j + 1] = backup;    // +1 copy
    		count++;
    		
    		//  +1 assign: i++
    		//  +1 comp: i<n (condition for next loop iteration)
    		count += 2;
    	}
    	double N = arr.length;
    	double N2 = Math.pow(N,2);
    	System.out.println("Array size N:"+ N +"    N^2:"+ N2 +"    Count:"+ count);
    }

	public static void main(String args[]) 
	{ 
		int arr[] = {3, 4}; 
		insertionSort(arr);
		int arr2[] = {3, 4, 2, 9, 1, 6, 8, 13}; 
		insertionSort(arr2);
		int arr3[] = {3, 4, 2, 9, 1, 6, 8, 13, 3, 4, 2, 9, 1, 6, 8, 13, 3, 4, 2, 9, 1, 6, 8, 13, 3, 4, 2, 9, 1, 6, 8, 13}; 
		insertionSort(arr3);
		
	} 
}
