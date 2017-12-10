import java.util.ArrayList;

/**
 * This class is a Dynamic Array implementation of {@link Queue}. It uses
 * ArrayList to simulate the queue.
 * 
 * @author Aidan Novobilski
 *
 */

/////////////////////////////////////////////////////////////////////////////////////////////////

public class ArrayQueue<T> implements Queue<T>
{

	/**
	 * This list will represent the queue that the objects will be put into.
	 * Note that the ArrayList implementation is inefficient, since it works by
	 * shifting the entries towards index {@code 0}.
	 */
	private ArrayList<T> queue;

	// ---------------------------------------------------------------------------------------------------

	/**
	 * The default constructor. Initializes the ArrayList.
	 */
	public ArrayQueue()
	{
		queue = new ArrayList<T>();
	}

	// ---------------------------------------------------------------------------------------------------

	public boolean enqueue(T anEntry)
	{
		return queue.add(anEntry);
		// adds to leftmost empty index
	}

	// ---------------------------------------------------------------------------------------------------

	public T dequeue()
	{
		if (queue.size() == 0)
			return null;
		// prevents out of bounds exception

		return queue.remove(0);
		// removes from leftmost index, shifts all entries down
	}

	// ---------------------------------------------------------------------------------------------------

	public boolean isEmpty()
	{
		return queue.isEmpty();
	}

	// ---------------------------------------------------------------------------------------------------
}

/////////////////////////////////////////////////////////////////////////////////////////////////