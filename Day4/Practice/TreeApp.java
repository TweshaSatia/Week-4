package Day4.Practice;
//Demonstration of binary tree.It provides methods for binary search tree classes for display, insert, find successor,
//delete,traversal,printing path,min node,max node,height of tree, diameter of tree and number of greater keys.
import java.io.*;
import java.util.*;               // for Stack class
////////////////////////////////////////////////////////////////
class Node
   {
   public int iData;              // data item (key)
   public double dData;           // data item
   public Node leftChild;         // this node's left child
   public Node rightChild;        // this node's right child

   public void displayNode()      // display ourself
      {
      System.out.print('{');
      System.out.print(iData);
      System.out.print(", ");
      System.out.print(dData);
      System.out.print("} ");
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
      {                           // (assumes non-empty tree)
      Node current = root;               // start at root
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
   public void insert(int id, double dd)
      {
      Node newNode = new Node();    // make new node
      newNode.iData = id;           // insert data
      newNode.dData = dd;
      if(root==null)                // no node in root
         root = newNode;
      else                          // root occupied
         {
         Node current = root;       // start at root
         Node parent;
         while(true)                // (exits internally)
            {
            parent = current;
            if(id < current.iData)  // go left?
               {
               current = current.leftChild;
               if(current == null)  // if end of the line,
                  {                 // insert on left
                  parent.leftChild = newNode;
                  return;
                  }
               }  // end if go left
            else                    // or go right?
               {
               current = current.rightChild;
               if(current == null)  // if end of the line
                  {                 // insert on right
                  parent.rightChild = newNode;
                  return;
                  }
               }  // end else go right
            }  // end while
         }  // end else not root
      }  // end insert()
// -------------------------------------------------------------
   // -------------------------------------------------------------
   public boolean delete(int key) // delete node with given key
      {                           // (assumes non-empty list)
      Node current = root;
      Node parent = root;
      boolean isLeftChild = true;

      while(current.iData != key)        // search for node
         {
         parent = current;
         if(key < current.iData)         // go left?
            {
            isLeftChild = true;
            current = current.leftChild;
            }
         else                            // or go right?
            {
            isLeftChild = false;
            current = current.rightChild;
            }
         if(current == null)             // end of the line,
            return false;                // didn't find it
         }  // end while
      // found node to delete

      // if no children, simply delete it
      if(current.leftChild==null &&
                                   current.rightChild==null)
         {
         if(current == root)             // if root,
            root = null;                 // tree is empty
         else if(isLeftChild)
            parent.leftChild = null;     // disconnect
         else                            // from parent
            parent.rightChild = null;
         }

      // if no right child, replace with left subtree
      else if(current.rightChild==null)
         if(current == root)
            root = current.leftChild;
         else if(isLeftChild)
            parent.leftChild = current.leftChild;
         else
            parent.rightChild = current.leftChild;

      // if no left child, replace with right subtree
      else if(current.leftChild==null)
         if(current == root)
            root = current.rightChild;
         else if(isLeftChild)
            parent.leftChild = current.rightChild;
         else
            parent.rightChild = current.rightChild;

      else  // two children, so replace with inorder successor
         {
         // get successor of node to delete (current)
         Node successor = getSuccessor(current);

         // connect parent of current to successor instead
         if(current == root)
            root = successor;
         else if(isLeftChild)
            parent.leftChild = successor;
         else
            parent.rightChild = successor;

         // connect successor to current's left child
         successor.leftChild = current.leftChild;
         }  // end else two children
      // (successor cannot have a left child)
      return true;                                // success
      }  // end delete()
// -------------------------------------------------------------
// -------------------------------------------------------------
   // returns node with next-highest value after delNode
   // goes to right child, then right child's left descendents
   private Node getSuccessor(Node delNode)
      {
      Node successorParent = delNode;
      Node successor = delNode;
      Node current = delNode.rightChild;   // go to right child
      while(current != null)               // until no more
         {                                 // left children,
         successorParent = successor;
         successor = current;
         current = current.leftChild;      // go to left child
         }
                                           // if successor not
      if(successor != delNode.rightChild)  // right child,
         {                                 // make connections
         successorParent.leftChild = successor.rightChild;
         successor.rightChild = delNode.rightChild;
         }
      return successor;
      }
// -------------------------------------------------------------
   public void traverse(int traverseType)
      {
      switch(traverseType)
         {
         case 1: System.out.print("\nPreorder traversal: ");
                 preOrder(root);
                 break;
         case 2: System.out.print("\nInorder traversal:  ");
                 inOrder(root);
                 break;
         case 3: System.out.print("\nPostorder traversal: ");
                 postOrder(root);
                 break;
         }
      System.out.println();
      }
// -------------------------------------------------------------
   private void preOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         System.out.print(localRoot.iData + " ");
         preOrder(localRoot.leftChild);
         preOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void inOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         inOrder(localRoot.leftChild);
         System.out.print(localRoot.iData + " ");
         inOrder(localRoot.rightChild);
         }
      }
// -------------------------------------------------------------
   private void postOrder(Node localRoot)
      {
      if(localRoot != null)
         {
         postOrder(localRoot.leftChild);
         postOrder(localRoot.rightChild);
         System.out.print(localRoot.iData + " ");
         }
      }
// -------------------------------------------------------------
   public void displayTree()
      {
      Stack globalStack = new Stack();
      globalStack.push(root);
      int nBlanks = 32;
      boolean isRowEmpty = false;
      System.out.println(
      "......................................................");
      while(isRowEmpty==false)
         {
         Stack localStack = new Stack();
         isRowEmpty = true;

         for(int j=0; j<nBlanks; j++)
            System.out.print(' ');

         while(globalStack.isEmpty()==false)
            {
            Node temp = (Node)globalStack.pop();
            if(temp != null)
               {
               System.out.print(temp.iData);
               localStack.push(temp.leftChild);
               localStack.push(temp.rightChild);

               if(temp.leftChild != null ||
                                   temp.rightChild != null)
                  isRowEmpty = false;
               }
            else
               {
               System.out.print("--");
               localStack.push(null);
               localStack.push(null);
               }
            for(int j=0; j<nBlanks*2-2; j++)
               System.out.print(' ');
            }  // end while globalStack not empty
         System.out.println();
         nBlanks /= 2;
         while(localStack.isEmpty()==false)
            globalStack.push( localStack.pop() );
         }  // end while isRowEmpty is false
      System.out.println(
      "......................................................");
      }  // end displayTree()
// -------------------------------------------------------------
/*
 Given a binary tree, print out all of its root-to-leaf
 paths, one per line. Uses a recursive helper to do the work.
*/
	void printPaths()
	{
		int[] path = new int[1000];
		printPathsRecur(root, path, 0);
	}
	
	/*
	Recursive helper function -- given a node, and an array containing
	the path from the root node up to but not including this node,
	print out all the root-leaf paths.
	*/
	void printPathsRecur(Node node, int[] path, int pathLen) {
		if (node==null) return;

		// append this node to the path array
		path[pathLen] = node.iData;
		pathLen++;

		  // it's a leaf, so print the path that led to here
		if (node.leftChild==null && node.rightChild==null) 
		{
			printArray(path, pathLen);
		}
		else
		{
		  // otherwise try both subtrees
			printPathsRecur(node.leftChild, path, pathLen);
			printPathsRecur(node.rightChild, path, pathLen);
		}
	}

	// Utility that prints out an array on a line.
	void printArray(int[] ints, int len)
	{
		int i;
		for (i=0; i<len; i++)
		{
			System.out.print(ints[i]);
		}
		System.out.print("\n");
	}
	Node min()
	{
		Node x=this.root;
		if(x!=null)
		{
			while(x.leftChild!=null)
			{
				x=x.leftChild;
			}
		}
		return x;
	}
	Node max()
	{
		Node x=this.root;
		if(x!=null)
		{
			while(x.rightChild!=null)
			{
				x=x.rightChild;
			}
		}
		return x;
	}
    public static int height(Node root)
	{
		// Base case: empty tree has height -1
		if (root == null) {
			return -1;
		}

		// recur for left and right subtree and consider maximum depth
		return 1 + Math.max(height(root.leftChild), height(root.rightChild));
	}

    int ans=0;
    public int diameterOfBinaryTree() {
        depth(root);
        return ans;
    } 
    
     int depth(Node root){
        if(root ==null)
            return 0;
        int left=depth(root.leftChild);
        int right=depth(root.rightChild);
        ans =Math.max(ans,left+right);
        return Math.max(left,right)+1;
    }
        
	public int numLarger(int k) {

        int count = 0;
        Stack globalStack = new Stack();
        globalStack.push(root);
        boolean isRowEmpty = false;

        while (isRowEmpty == false) {
            Stack localStack = new Stack();
            isRowEmpty = true;

            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    if (temp.iData > k)
                        count++;
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null ||
                            temp.rightChild != null)
                        isRowEmpty = false;
                } else {
                    localStack.push(null);
                    localStack.push(null);
                }

            }  // end while globalStack not empty

            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop());
        }  // end while isRowEmpty is false


        return count;
    }

// -------------------------------------------------------------
   }  // end class Tree
////////////////////////////////////////////////////////////////
class TreeApp
   {
   public static void main(String[] args) throws IOException
      {
      int value;
      Tree theTree = new Tree();
	  Node min,max;

      theTree.insert(50, 1.5);
      theTree.insert(25, 1.2);
      theTree.insert(75, 1.7);
      theTree.insert(12, 1.5);
      theTree.insert(37, 1.2);
      theTree.insert(43, 1.7);
      theTree.insert(30, 1.5);
      theTree.insert(33, 1.2);
      theTree.insert(87, 1.7);
      theTree.insert(93, 1.5);
      theTree.insert(97, 1.5);

      while(true)
         {
         System.out.println("Enter first letter of show:");
         System.out.print("printpath, insert, find, delete, traverse, minimum, largest,height of tree,DiameterOfTree,num of greater keys,exit: ");
         int choice = getChar();
         switch(choice)
            {
			case 'p':
			   theTree.printPaths();
               break;
            case 's':
               theTree.displayTree();
               break;
            case 'i':
               System.out.print("Enter value to insert: ");
               value = getInt();
               theTree.insert(value, value + 0.9);
               break;
            case 'f':
               System.out.print("Enter value to find: ");
               value = getInt();
               Node found = theTree.find(value);
               if(found != null)
                  {
                  System.out.print("Found: ");
                  found.displayNode();
                  System.out.print("\n");
                  }
               else {
                  System.out.print("Could not find ");
                  System.out.print(Integer.toString(value) + '\n');
               }
               break;
            case 'd':
               System.out.print("Enter value to delete: ");
               value = getInt();
               boolean didDelete = theTree.delete(value);
               if(didDelete)
                  System.out.print("Deleted " + value + '\n');
               else {
                  System.out.print("Could not delete ");
                  System.out.print(Integer.toString(value) + '\n');
               }
               break;
            case 't':
               System.out.print("Enter type 1, 2 or 3: ");
               value = getInt();
               theTree.traverse(value);
               break;
			case 'm':
				min=theTree.min();
				if(min==null)
				{
					System.out.print("The tree is empty");
				}
				else
					System.out.print("Smallest node is:");
					min.displayNode();
				break;
			case 'l':
				max=theTree.max();
				if(max==null)
				{
					System.out.print("The tree is empty");
				}
				else
					System.out.print("Largest node is:");
					max.displayNode();
				break;
                case 'h':
				int h=theTree.height(theTree.root);
				System.out.println("Height of tree is:"+h);
				break;

                case 'D':
				int d=theTree.diameterOfBinaryTree();
				System.out.println("The diameter of tree is:"+d);
				break;

                case 'n':
				int num=theTree.numLarger(25);
				System.out.println("The number of keys in the tree greater than the key passed are:"+num);
				break;

			case 'e':
				return;
            default:
               System.out.print("Invalid entry\n");
            }  // end switch
         }  // end while
      }  // end main()
// -------------------------------------------------------------
   public static String getString() throws IOException
      {
      InputStreamReader isr = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(isr);
      String s = br.readLine();
      return s;
      }
// -------------------------------------------------------------
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
// -------------------------------------------------------------
   }  // end class TreeApp
////////////////////////////////////////////////////////////////