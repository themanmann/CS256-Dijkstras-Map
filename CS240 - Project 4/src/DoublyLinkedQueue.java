/**
 * This class is a LinkedList {@link Queue} implementation that uses
 * {@link Node} that points both to the next Node and to the previous.
 * 
 * @author Aidan Novobilski
 */

/////////////////////////////////////////////////////////////////////////////////////////////////

public class DoublyLinkedQueue<T> implements Queue<T>
{
	/**
	 * Represents the head of the linked list
	 */
	private Node first;

	/**
	 * Represents the tail of the linked list
	 */
	private Node last;

	/**
	 * Represents the number of entries in the linked list
	 */
	private int entries;

	// ---------------------------------------------------------------------------------------------------

	/**
	 * The default constructor. Initializes the {@link #first} to {@code null}
	 * and {@link #entries} to {@code 0}.
	 */
	public DoublyLinkedQueue()
	{
		first = null;
		last = null;
		entries = 0;
	}

	// ---------------------------------------------------------------------------------------------------

	public boolean enqueue(T anEntry)
	{
		if (first == null)
		{
			first = new Node(anEntry);
			last = first;
			entries++;
		} else
		{
			Node temp = new Node(anEntry);
			temp.next = first;
			first.prev = temp;
			first = temp;
			entries++;
		}

		return true;
	}

	// ---------------------------------------------------------------------------------------------------

	public T dequeue()
	{
		T temp = null;
		if (last != null)
		{
			temp = last.data;
			if (first == last)
			{
				first = null;
				last = null;
			} else
			{
				last = last.prev;
				last.next = null;
			}

			entries--;
		}

		return temp;
	}

	// ---------------------------------------------------------------------------------------------------

	public boolean isEmpty()
	{
		return entries == 0;
	}

	// ---------------------------------------------------------------------------------------------------

	/////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * The node class serves as the backbone of the Linked List. This particular
	 * node contains references to both the previous and next node.
	 * 
	 * @author Aidan Novobilski
	 */
	private class Node
	{
		/**
		 * Links to the previous node
		 */
		private Node prev;

		/**
		 * Links to the next node
		 */
		@SuppressWarnings("unused")
		private Node next;
		// Eclipse is yelling at me that I'm not using this when I clearly am

		/**
		 * This node's data
		 */
		private T data;

		// ---------------------------------------------------------------------------------------------------

		/**
		 * The default constructor. Initializes {@link #data} to {@code null}.
		 */
		private Node()
		{
			this(null);
		}

		// ---------------------------------------------------------------------------------------------------

		/**
		 * This constructor initializes {@link #data} to be the argument.
		 * 
		 * @param data
		 *            the node's data
		 */
		private Node(T data)
		{
			this.data = data;
			prev = null;
			next = null;
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
}
/////////////////////////////////////////////////////////////////////////////////////////////////