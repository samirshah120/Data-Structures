package samir;

import java.util.ArrayList;

public class IDLList<E> {
	@SuppressWarnings("hiding")
	class Node<E>{
		private E data;
		private Node<E> prev;
		private Node<E> next;
		
		public Node(E elem)
		{
			data = elem;
			prev = null;
			next = null;
		}
		
		public Node(E elem, Node<E> prev,Node<E> next)
		{
			data = elem;
		}
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	public IDLList()
	{
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
		
	}
	
	public boolean add(int index,E elem) //Adds element to given position
	{
		if(index < 0 || index > size)
			throw  new ArrayIndexOutOfBoundsException();
		else
		{
			if(index == 0)
			{
				Node<E> newnode = new Node<E>(elem);
				if(head == null)
				{
					head = newnode;
					tail = newnode;
					size++;
				}
				else
				{
					head.prev = newnode;
					newnode.next = head;
					head = newnode;
					size++;
				}
				indices.add(0, newnode);
				
				return true;
			}
			if(index == size)
			{
				append(elem);
			}
			else
			{
				Node<E> newnode = new Node<E>(elem);
				Node<E> current = indices.get(index);
				current.prev.next = newnode;
				newnode.prev = current.prev;
				newnode.next = current;
				current.prev = newnode;
				size++;
				indices.add(index, newnode);
				
			}
		}
			
		
		return true;
	}
	
	
	public boolean add(E elem) // Adds element at head
	{
		Node<E> newnode = new Node<E>(elem);
		if(head == null)
		{
			head = newnode;
			tail = newnode;
			size++;
		}
		else
		{
			head.prev = newnode;
			newnode.next = head;
			head = newnode;
			size++;
		}
		indices.add(0, newnode);
		
		return true;
	}
	
	public boolean append(E elem) //Adds element at tail
	{
		if(head == null)
		{
			add(elem);
			return true;
		}
		else
		{
			Node<E> newnode = new Node<E>(elem);
			tail.next = newnode;
			newnode.prev = tail;
			tail = newnode;
			size++;
			indices.add(newnode);
			return true;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public E get (int index) // gets element at index
	{ 
		if(index < 0 || index >= size)
			throw  new ArrayIndexOutOfBoundsException();
		else
		{
			return (E) ("element at index " +index+" is "+indices.get(index).data);
		}
		
	}
	
	
	public E getHead() // gets element at head
	{
		if(size == 0)
			throw  new IndexOutOfBoundsException();
		else
		{
			return indices.get(0).data;
		}
		
	}
	
	public E getTail() // gets element at tail
	{
		if(size == 0)
			throw  new ArrayIndexOutOfBoundsException();
		else
		{
			return  indices.get(size-1).data;
		}
		
		
	}
	public int size() // returns size of list
	{
		return size;
	}
	
	public E remove() //removes element at front of list
	{
		if(size == 0)
			throw new IndexOutOfBoundsException();
		else
		{
			Node<E> current = head;
			if(head == null)// if no element present in linked list
				throw new IndexOutOfBoundsException();
			if(head == tail)// if only one element present in linked list
			{
				head = null;
				tail = null;
				indices.remove(current);
				size = 0;
				return current.data; 
			}
			else
			{
				head = head.next;
				indices.remove(0);
				size--;
				return current.data;
			}
		}
		
	}
	
	public E removeLast() //Removes last element of list and returns the deleted element 
	{
		if(size == 0)
			throw new IndexOutOfBoundsException();
		else
		{
			Node<E> current = tail;
			if(tail == null)
				return null;
			if(head == tail)
			{
				head = null;
				tail = null;
				indices.remove(current);
				size = 0;
				return current.data; 
			}
			else
			{
				tail = tail.prev;
				tail.next = null;
				indices.remove(current);
				size--;
				return current.data;
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public E removeAt (int index) // removes element at index
	{
		if(index < 0 || index >= size)
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		else
		{
			Node<E> current = indices.get(index);
			{
				if(current == head)
				{
					remove();
				}
				else if(current == tail)
				{
					removeLast();
				}
				else
				{
					current.next.prev = current.prev;
					current.prev.next = current.next;
					indices.remove(current);
					size--;
				}
			}
			return current.data;
		}
			
	}
		
	
	public boolean remove (E elem) // remove particular element
	{
		boolean flag = false;
		for(Node<E> current = head;current != null;current = current.next)
		{
			if(current.data.equals(elem))
			{
				if(current == head)
				{
					remove();
					flag = true;
					break;
				}
				else if(current == tail)
				{
					removeLast();
					flag = true;
					break;
				}
				else
				{
					current.next.prev = current.prev;
					current.prev.next = current.next;
					indices.remove(current);
					size--;
					flag = true;
					break;
				}
			}
			
		}
		return flag;
	}
	@Override
	public String toString()
	{
		if(head == null)
		{
			System.out.println("list is empty");
		}
		StringBuilder sb = new StringBuilder();
		for(Node<E> current = head;current != null;current = current.next)
		{
			sb.append(current.data +" ");
		}
		return sb.toString();
		}
}
