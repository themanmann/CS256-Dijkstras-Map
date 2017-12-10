///////////////////////////////////////////////////////////////////////////////
public interface Queue<T>
{
	/**
	 * Adds an entry to the back of a queue.
	 * 
	 * @param anEntry
	 *            the entry to add
	 * @return {@code true} if successfully added, {@code false} otherwise
	 */
	public boolean enqueue(T anEntry);

	/**
	 * Removes an entry from the front of a queue.
	 * 
	 * @return the removed entry
	 */
	public T dequeue();

	/**
	 * Returns whether the queue is empty
	 * 
	 * @return {@code true} if empty, {@code false} otherwise
	 */
	public boolean isEmpty();
}
///////////////////////////////////////////////////////////////////////////////