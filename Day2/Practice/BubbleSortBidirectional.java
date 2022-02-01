package Day2.Practice;
//Bubble Sort in Two way-Bidirectional
public class BubbleSortBidirectional {
    public static void bubbleSort(int[] a){
    	boolean swapped = true; 
    	int start = 0; 
    	int end = a.length - 1; 
        int tmp;
        
    	while (swapped) {
    		swapped = false; 
    		for (int i = start; i < end; ++i) { 
    			if (a[i] > a[i + 1]) { 
    			    tmp = a[i];
    			    a[i] = a[i+1];
    			    a[i+1] = tmp;
    				swapped = true; 
    			} 
    		}
    		--end;
    
    		if (!swapped) break; 
    		swapped = false; 
            //reverse and carry the smallest item from right to left.
    		for (int i = end - 1; i >= start; --i) { 
    			if (a[i] > a[i + 1]) {  
    			    tmp = a[i];
    			    a[i] = a[i+1];
    			    a[i+1] = tmp;
    				swapped = true; 
    			} 
    		}
    		++start;
    	}
    	
    	for(int i=0;i<a.length;i++)
    	    System.out.print(a[i]+" ");
	System.out.println();
    }

	public static void main(String args[]) 
	{ 
		int arr[] = {3, 4, 2, 9, 1, 6, 8, 13}; 
		bubbleSort(arr);
	} 
}
