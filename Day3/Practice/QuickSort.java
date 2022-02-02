package Day3.Practice;
import java.util.Arrays;
//Finding Median
public class QuickSort{
static int partition(int arr[], int low, int high) 
	{ 
		int pivot = arr[high]; 
		int i = (low-1);
		for (int j=low; j<high; j++) 
		{ 
			if (arr[j] < pivot) 
			{ 
				i++; 
				int temp = arr[i]; 
				arr[i] = arr[j]; 
				arr[j] = temp; 
			} 
		} 
		int temp = arr[i+1]; 
		arr[i+1] = arr[high]; 
		arr[high] = temp; 
		return i+1; 
	}

	static int quickSort(int arr[], int low, int high, int median, int m2)
	{
		int pi = partition(arr, low, high);			

		//----------
		if(pi == median) {
			if(m2!=-1) return (arr[pi] + quickSort(arr, pi + 1, high, m2, -1));
			return arr[pi];
		}else if(pi == m2) {
			return (quickSort(arr, low, pi - 1, median, -1) + arr[pi]);
		}
		else if(pi < median)
			return quickSort(arr, pi + 1, high, median, m2);
		/* else if(pi >= median) */
		return quickSort(arr, low, pi - 1, median, m2);
		//----------

	}

	public static void main(String args[]) 
	{ 
		int arr[] = {3, 4, 2, 9, 1, 6, 8, 13}; 
		
		int n = arr.length;
		int m1 = (n-1)/2;
		int m2 = n%2 == 0 ? m1+1 : -1;
		int m3 = m2 == -1 ? m1 : m2;

		System.out.println("m1:"+m1+" m2:"+m2);
		int op = quickSort(arr, 0, n - 1, m1, m2);
		double median = m2 == -1 ? op : op/2;
		System.out.println("Median : " + median);

		Arrays.sort(arr);
		double actualMedian = (arr[m1]+arr[m3])/2;
		System.out.println("Actual Median : " + actualMedian);
	} 
} // end class QuickSort