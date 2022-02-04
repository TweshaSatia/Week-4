package Day5.Practice;
//Instead of using a linked list to resolve collisions, as in separate chaining, using a
//binary search tree.Create a hash table that is an array of trees.
import java.io.*;
////////////////////////////////////////////////////////////////
class Node
{
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child
	
   public Node(int d)
   {
	   iData = d;
   }   
   public void displayNode()      // display ourself
    {
      System.out.print('{');
      System.out.print(iData);
      System.out.print(", ");
      System.out.print(dData);
      System.out.print("} \n ");
    }
}  // end class Node
////////////////////////////////////////////////////////////////
class Tree
{
   public Node root;             // first node of tree
// -------------------------------------------------------------
   public Tree()                  // constructor
    { root = null; }            // no nodes in tree yet
// -------------------------------------------------------------
   public Node find(int key)      // find node with given key
    {     
   	  Node current = root;               // start at root
	  if(current == null)
		  return null;
      while(current.iData != key)        // while no match,
        {
         if(key < current.iData)         // go left?
            current = current.leftChild;
         else                            // or go right?
            current = current.rightChild;
         if(current == null)             // if no child,
            return null;                 // didn't find it
        }
      return current;                    // found it
    }  // end find()
// -------------------------------------------------------------
   public void insert(Node newNode)
    {
      
      if(root==null)                // no node in root
         root = newNode;
      else                          // root occupied
      {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
         {
            parent = current;
            if(newNode.iData < current.iData)  // go left?
            {
               current = current.leftChild;
               if(current == null)  // if end of the line,
                {                 // insert on left
                  parent.leftChild = newNode;
                  return;
                }
            }  // end if go left
            else if(newNode.iData > current.iData)                    // or go right?
            {
               current = current.rightChild;
               if(current == null)  // if end of the line
                {                 // insert on right
                  parent.rightChild = newNode;
                  return;
                }
            }  // end else go right
            else    //if key is already present
			{
				System.out.println("Key already present in the tree");
				return;
			}
		  }  // end while
       }  // end else not root
    }  // end insert()
// -------------------------------------------------------------
   public void inOrder(Node localRoot)
    {
      if(localRoot != null)
       {
         inOrder(localRoot.leftChild);
         System.out.print(localRoot.iData + " ");
         inOrder(localRoot.rightChild);
       }
    }
// -------------------------------------------------------------
 
}  // end class Link
////////////////////////////////////////////////////////////////
class HashTable
{
	private Tree[] hashArray; // array of trees
	private int arraySize;
// -------------------------------------------------------------
	public HashTable(int size) // constructor
	{
		arraySize = size;
		hashArray = new Tree[arraySize]; // create array
		for(int j=0; j<arraySize; j++) // fill array
			hashArray[j] = new Tree(); // with lists
	}
// -------------------------------------------------------------5

	public void displayTable()
	{
		for(int j=0; j<arraySize; j++) // for each cell,
		{
			System.out.print("\n" + j + ". "); // display cell number
			hashArray[j].inOrder(hashArray[j].root); // display list
		}
	}
// -------------------------------------------------------------
	public int hashFunc(int key) // hash function
	{
		return key % arraySize;
	}
// -------------------------------------------------------------
	public void insert(Node node) // insert a node
	{
		int key =  node.iData;
	
		int hashVal = hashFunc(key); // hash the key
		hashArray[hashVal].insert(node); // insert at hashVal
	} // end insert()

// -------------------------------------------------------------
	public Node find(Node theNode) // find link
	{
		int key = theNode.iData;
		int hashVal = hashFunc(key); // hash the key
		Node node = hashArray[hashVal].find(theNode.iData);
	
		return node; // return link
	}
// -------------------------------------------------------------
} // end class HashTable
////////////////////////////////////////////////////////////////
class HashTable_BST
{
	public static void main(String[] args) throws IOException
	{
		int aKey;
		Node aDataItem;
		int size, n, keysPerCell = 100,ch;
		// get sizes
		System.out.print("Enter size of hash table: ");
		size = getInt();
		System.out.print("Enter initial number of items: ");
		n = getInt();
		// make table
		HashTable theHashTable = new HashTable(size);
		for(int j=0; j<n; j++) // insert data
		{
			aKey = (int)(java.lang.Math.random() *
			keysPerCell * size);
			aDataItem = new Node(aKey);
			theHashTable.insert(aDataItem);
		}
		while(true) // interact with user
		{
			System.out.print("\nEnter first letter of ");
			System.out.print("show, insert, find or quit: ");
			char choice = getChar();
			switch(choice)
			{
			case 's':
				theHashTable.displayTable();
				break;
			case 'i':
			
					System.out.println("Enter key value to insert:");
					aKey = getInt();
					aDataItem = new Node(aKey);
				
				theHashTable.insert(aDataItem);
				break;
			
			case 'f':
				
					System.out.println("Enter key value to find:");
					aKey = getInt();
					aDataItem = new Node(aKey);
				
				Node dataItem = theHashTable.find(aDataItem);
				if(dataItem != null)
					System.out.println("Found ");
				else
					System.out.println("Could not find ");
				break;
			case 'q':
				System.exit(0);
				break;
			default:
				System.out.print("Invalid entry\n");
			} // end switch
		} // end while
	} // end main()
	//--------------------------------------------------------------
	public static String getString() throws IOException
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	//-------------------------------------------------------------
	public static char getChar() throws IOException
	{
		String s = getString();
		return s.charAt(0);
	}
	//-------------------------------------------------------------
	public static int getInt() throws IOException
	{
		String s = getString();
		return Integer.parseInt(s);
	}
//--------------------------------------------------------------
}
