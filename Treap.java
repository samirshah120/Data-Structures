import java.util.Random;
import java.util.Stack;
/**
 * Treap Class
 * @author samir
 *
 * @param <E>
 */
public class Treap<E extends Comparable<E>>{
	/**
	 * Node Class
	 * @author samir
	 *
	 * @param <E>
	 */
	private class Node<E> {
		public E data; // key for the search
		public int priority; // random heap priority
		public Node<E> left;
		public Node<E> right;
		/**
		 * Constructor
		 * @param data
		 * @param priority
		 */
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException();
			} else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}
		/**
		 * Right rotate function
		 * @return
		 */
		Node <E> rotateRight ()
		{
			Node<E> b = this.left;
			Node<E> T3 = b.right;
			b.right = this;
			this.left = T3;
			return b;
		}
		/**
		 * Left rotate function
		 * @return
		 */
		Node <E> rotateLeft ()
		{
			Node<E> b = this.right;
			Node<E> T3 = b.left;
			b.left = this;
			this.right = T3;
			return b;
		}
		
	}
	private Random priorityGenerator;
	private Node<E> root;
	/**
	 * Constructor
	 */
	public Treap() {
		root = null;
		priorityGenerator = new Random();
	}
	/**
	 * Constructor
	 * @param seed
	 */
	public Treap(long seed) {
		root = null;
		priorityGenerator = new Random(seed);
	}
	/**
	 * Adds element to tree
	 * @param key
	 * @return
	 */
	boolean add(E key)
	{
		return add(key ,priorityGenerator.nextInt());
	}
	/**
	 * Adds tree to correct place as per bst rules and reheaps according to priority
	 * @param key
	 * @param priority
	 * @return
	 */
	boolean add(E key , int priority)
	{
		Stack<Node<E>> stk = new Stack<Node<E>>();
		if(root == null)
		{
			root = new Node<E>(key,priority);
			return true;
		}
		else
		{
			Node<E> current = root;
			while(current != null)
			{
				stk.push(current);
				if(key.compareTo(current.data) < 0) //add to left sub tree
				{
					if(current.left == null)
					{
						Node<E> newnode = new Node<E>(key,priority);
						current.left = newnode;
						reheap(newnode,stk);
						return true;
					}
					else
					{
						current = current.left;
					}
				}
				else if(key.compareTo(current.data) > 0) //add to right sub tree
				{
					if(current.right == null)
					{
						Node<E> newnode = new Node<E>(key,priority);
						current.right = newnode;
						reheap(newnode,stk);
						return true;
					}
					else
					{
						current = current.right;
					}
				}
				else
				{
					return false;
				}
			}
				
		}
		return false;
	}
	/**
	 * Reheap is used to adjust the tree in priority while maintaining BST property
	 * @param newnode
	 * @param stk
	 */
	public void reheap(Node<E> newnode,Stack<Node<E>> stk)
	{
		while(!stk.isEmpty())
		{
			Node<E> parent = stk.pop();
			if(parent.priority < newnode.priority)
			{
				
				if(newnode.data.compareTo(parent.data) < 0) //right rotate
				{
					newnode = parent.rotateRight();
					if(stk.isEmpty())
					{
						root = newnode;
					}
					else
					{
						Node<E> t = stk.peek();
						if(newnode.data.compareTo(t.data) < 0)
						{
							t.left = newnode;
						}
						else
						{
							t.right = newnode;
						}
					}
				}
				else //left rotate
				{
					newnode = parent.rotateLeft();
					if(stk.isEmpty())
					{
						root = newnode;
					}
					else
					{
						Node<E> t = stk.peek();
						if(newnode.data.compareTo(t.data) < 0)
						{
							t.left = newnode;
						}
						else
						{
							t.right = newnode;
						}
						
					}
				}
			}
		}
		
	}
	/**
	 * Find function checks if key is present in tree or not
	 * @param root
	 * @param key
	 * @return
	 */
	private boolean find(Node<E> root, E key)
	{
		Node<E> current = root;
		if(current == null || key == null)
			return false;
		if(current.data.compareTo(key) == 0)
			return true;
		if(key.compareTo(current.data) < 0)
			return find(current.left,key);
		else
			return find(current.right,key);		
	}
	/**
	 * Find function
	 * @param key
	 * @return
	 */
	boolean find(E key)
	{
		boolean result = find(root,key);
		return result;
	}
	
	/**
	 * Checks if key is present if no then returns false else goes to delete method
	 * @param key
	 * @return
	 */
	boolean delete(E key)
	{
		if(key == null ||find(key) == false)
			return false;
		else
		{
			root = delete(key,root);
			return true;
		}
		
	}
	/**
	 * Checks if root is equal to key and calls delete recursively
	 * @param key
	 * @param root
	 * @return
	 */
	private Node<E> delete(E key, Node<E> root){
		if(root == null)
			return null;
		else
		{
			
			if(key.compareTo(root.data) == 0) //found
			{
				if(root.left == null && root.right == null) // does not have any child
				{
					root = null;
				}
				else if(root.right == null && root.left != null) //only right child is null
				{
					root = root.rotateRight();
					root.right = delete(key,root.right);
				}
				else if(root.right != null && root.left == null) //only right child is null
				{
					root = root.rotateLeft();
					root.left = delete(key,root.left);
				}
				else //both child are not null
				{
					if(root.right.priority > root.left.priority)
					{
						root = root.rotateLeft();
						root.left = delete(key,root.left);
					}
					else
					{
						root = root.rotateRight();
						root.right = delete(key,root.right);
						
					}
				}
				
			}
			else if(key.compareTo(root.data) < 0) // go to left sub tree
			{
				root.left = delete(key,root.left);
			}
			else //go to right sub tree
			{
				root.right = delete(key,root.right);
			}
			
		}
		return root;
		
	}
	/**
	 * Return tree depth wise(Preorder traversal)
	 * @param temp
	 * @param depth
	 * @return
	 */
	private String toString(Node<E> temp, int depth) {
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<depth;i++) {
			sb.append(" ");
		}
		if (temp==null) {
			sb.append("null");
		} else {
			sb.append("(key = " + temp.data +", priority = " + temp.priority + ")" );
			sb.append("\n");
			sb.append(toString(temp.left, depth+1));
			sb.append("\n");
			sb.append(toString(temp.right, depth+1));
		}
		return sb.toString();
		
	}
	/**
	 * Returns tree 
	 */
	public String toString() {
		return toString(root,0);
	}
	public static void main(String[] args) {
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add (4 ,19);
		testTree.add (2 ,31);
		testTree.add (6 ,70);
		testTree.add (1 ,84);
		testTree.add (3 ,12);
		testTree.add (5 ,83);
		testTree.add (7 ,26);
		testTree.add (7 ,26);
		testTree.delete(1);
		System.out.println(testTree.find(6));
		System.out.println(testTree.find(1));
		System.out.println(testTree.delete(1));
		System.out.println(testTree.toString());
		}
}

