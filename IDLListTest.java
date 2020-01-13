package samir;

public class IDLListTest {

	public static void main(String[] args) {
		IDLList<Integer> list = new IDLList<Integer>();
			list.add(0,35); 
			list.append(5);
			list.add(100);
			list.add(1, 200);
			list.add(300);
			list.append(400);
			list.add(1, 500);
			list.add(3, 600);
			list.append(700);
			list.add(2, 800);
			list.add(4, 800);
			try {
				list.add(-1, 12); // Negative index will throw exception
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				System.out.println("Index out of bounds");
			}
			try {
				list.add(12,80); 
			}
			catch(ArrayIndexOutOfBoundsException e) // Index more than size will throw exception
			{
				System.out.println("Index out of bounds");
			}
			list.add(11,55); // Will add at end of List
			System.out.println(list.toString());
			System.out.println(list.remove(800)); // Removes 1st occourance and returns true if not present returns false
			System.out.println(list.toString());
			try {
				System.out.println(list.get(9)); //Negative index will throw exception
			}
			catch(ArrayIndexOutOfBoundsException e) 
			{
				System.out.println("Index out of bounds");
			}
		    
		    try
		    {
		    	System.out.println("Element at head is "+list.getHead());
		    }
		    catch(IndexOutOfBoundsException e) 
			{
				System.out.println("Cannot get element when list size is 0");
			}
		    try
		    {
		    	System.out.println("Element at tail is "+list.getTail());
		    }
		    catch(IndexOutOfBoundsException e) 
			{
				System.out.println("Cannot get element when list size is 0");
			}
			
			System.out.println("Size of List is "+list.size());
			System.out.println(list.toString());
			try
			{
				System.out.println(list.remove());
			}
			catch(IndexOutOfBoundsException e) 
			{
				System.out.println("Cannot get element when list size is 0");
			}
			
			System.out.println(list.toString());
			try {
				System.out.println(list.removeLast());
			}
			catch(IndexOutOfBoundsException e) 
			{
				System.out.println("Cannot get element when list size is 0");
			}
			
			System.out.println(list.toString());
			try {
				System.out.println(list.removeAt(4));
			}
			catch(IndexOutOfBoundsException e) 
			{
				System.out.println("Index out of bounds");
			}
			
			System.out.println(list.toString());
	
	}

}
