/**
 * This class is a LinkedList {@link Queue} implementation that uses
 * {@link Node} that points both to the next Node and to the previous, as well
 * as maintains itself in a sort so that the highest priority {@link Assignment}
 * is removed every time. Implementation requires non-generic due to requiring
 * direct acccess to attributes of Assignment.
 * 
 * @author Aidan Novobilski
 */

/////////////////////////////////////////////////////////////////////////////////////////////////

public class PriorityQueue implements Queue<Assignment>
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
	public PriorityQueue()
	{
		first = null;
		last = null;
		entries = 0;
	}

	// ---------------------------------------------------------------------------------------------------

	public boolean enqueue(Assignment anEntry)
	{
		// I understand that there are multiple ways to implement a priority
		// queue. I could iterate through the queue in dequeue, or iterate in
		// enqueue and maintain a pointer. However, I believe this method is
		// most efficient. This way, in all likelyhood, the queue will probably
		// not be iterated all the way through. While it is still O(n), it is a
		// smaller O(n).

		Node current = last;

		for (int i = entries; i >= 0; i--)
		{
			// insertion sort-esque add allows the priority queue to remain
			// sorted
			if (i == 0 || compare(current.data, anEntry) < 0)
			{
				add(anEntry, current);
				break;
			}

			// iterate left
			current = current.prev;
		}
		return true;
	}

	// ---------------------------------------------------------------------------------------------------

	public Assignment dequeue()
	{
		Assignment temp = null;
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

	/**
	 * This method adds the specified data to the head of the linked list. The
	 * item is added to the front so that order is maintained
	 * 
	 * @param data
	 *            the data to add
	 */
	private void add(Assignment data)
	{
		if (first == null)
		{
			first = new Node(data);
			last = first;
			entries++;
		} else
		{
			Node temp = new Node(data);
			temp.next = first;
			first.prev = temp;
			first = temp;
			entries++;
		}
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * This add method attempts to add data at a specific index. If the
	 * specified index is greater than the number of entries, it will be added
	 * on the end anyway. Used by {@link #enqueue(Assignment)} to sort the array to
	 * maintain priority.
	 * 
	 * @param data
	 *            the data to add
	 * @param prior
	 *            the node before the position to add to
	 */
	private void add(Assignment data, Node prior)
	{
		if (prior == null)
		{
			add(data);
		} else
		{

			Node toAdd = new Node(data);
			toAdd.prev = prior;

			// order matters, don't want to deallocate rest of array
			if (prior.next != null)
			{
				prior.next.prev = toAdd;
				toAdd.next = prior.next;
			} else
				toAdd.next = null;

			prior.next = toAdd;

			if (prior == last)
				last = toAdd;

			entries++;
		}
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * This method acts as a "safe" comparison, handling cases in which
	 * {@link Assignment#compareTo(Assignment)} would result in division by 0.
	 * If one has 0 days left and another does not, the one with zero days
	 * remaining is higher priority. If they both have 0 days left, their pure
	 * value is compared. Otherwise, {@link Assignment#compareTo(Assignment)}
	 * occurs normally.
	 * 
	 * @param inQ
	 *            the assignment in queue to be checked
	 * @param toAdd
	 *            the assignment to be added
	 * @return a number similar to {@link Assignment#compareTo(Assignment)}
	 */
	private int compare(Assignment inQ, Assignment toAdd)
	{
		if (toAdd.getDaysTilDue() == 0)
		{
			if (inQ.getDaysTilDue() == 0)
			{
				return inQ.getValue() - toAdd.getValue();
			} // prevents division by 0, handles comparison case

			return -1; // inQ < toAdd
		} else if (inQ.getDaysTilDue() == 0)
		{
			return 1; // inQ > toAdd
		}

		// Value can be equal for different days 'til, and can result in strange
		// priority as a result of compare returning ints
		if (inQ.getValue() == toAdd.getValue())
		{
			return toAdd.getDaysTilDue() - inQ.getDaysTilDue();
			// reversed order because more days = lower priority
		}

		return inQ.compareTo(toAdd);
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
		private Node next;

		/**
		 * This node's data
		 */
		private Assignment data;

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
		private Node(Assignment data)
		{
			this.data = data;
			prev = null;
			next = null;
		}
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
}
/////////////////////////////////////////////////////////////////////////////////////////////////