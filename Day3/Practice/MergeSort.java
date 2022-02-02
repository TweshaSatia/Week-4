package Day3.Practice;

public class MergeSort {
    public static void printArray(int[] c)
	{
		int j;
		for(j=0; j<c.length; j++)
			System.out.print(c[j] + ", ");
		System.out.print("\n");
	}
	
	public void merge(int[] c, int lower, int mid, int upper)
	{
		System.out.println("Lower: " + lower + ", Mid: " + mid + ", Upper: " + upper + "==>");
		printArray(c);
		int n, m, i, j, k;
		// decide size of subarrays
		n = mid - lower + 1;
		m = upper - mid;
		// define subarrays arrays
		int[] a = new int[n];
		int[] b = new int[m];
		// copy data from main array to subarray
		for(i=0; i<n; i++)
			a[i] = c[lower+i];
		for(j=0; j<m; j++)
			b[j] = c[mid+1+j];
		// perform merging
		i=0;j=0;k=lower;
		// copy element from both array until
		// either one reaches the end
		while(i<n && j<m)
		{
			if (a[i] < b[j])
			{
				c[k] = a[i];
				i++;
			}
			else
			{
				c[k] = b[j];
				j++;
			}
			k++;
		}
		// has b array reached the end
//		if (j==n)
		{
			// copy remaining elements from a to c
			while(i<n)
			{
				c[k] = a[i];
				k++; i++;
			}
		}
		// has a array reached the end
//		if (i==m)
		{
			// copy remaining elements from b to c
			while(j<m)
			{
				c[k] = b[j];
				k++; j++;
			}
		}
		System.out.println("Lower: " + lower + ", Mid: " + mid + ", Upper: " + upper + "==>");
		printArray(c);
	}
	public void mergeSort(int[] c, int lower, int upper)
	{
		if (lower < upper)
		{ 	
			int mid = (lower+upper)/2;
			mergeSort(c, lower, mid);
			mergeSort(c, mid+1, upper);
			merge(c, lower, mid, upper);
			//System.out.println("Lower: " + lower + ", Mid: " + mid + ", Upper: " + upper + "==>");
			//printArray(c);
		}
	}
	public static void main(String[] args)
	{
		int[] c = {2, -1, 5, 0, 7, -4, 6, 3};
		System.out.println("Before Sorting===>");
		printArray(c);
		MergeSort ms = new MergeSort();
		ms.mergeSort(c, 0, c.length-1);
		System.out.println("After Sorting===>");
		printArray(c);
		
	}
}
