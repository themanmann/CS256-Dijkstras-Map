import java.util.Scanner;

import com.sun.corba.se.impl.io.TypeMismatchException;

/**
 * The main driver class of the project. Forms a UI to manipulate queues.
 * 
 * @author Aidan Novobilski
 */

/////////////////////////////////////////////////////////////////////////////////////////////////

public class AssignmentQueueDriver
{

	public static Scanner sc = new Scanner(System.in);

	// ---------------------------------------------------------------------------------------------------

	/**
	 * The main method. Calls {@link #selectQueue()}.
	 * 
	 * @param args
	 *            unused
	 */
	public static void main(String[] args)
	{
		selectQueue();
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * This method allows the user to select a queue type to use with
	 * {@link #queueMenu(Queue, String)}, or to view {@link #testCases()}.
	 */
	public static void selectQueue()
	{
		String input;
		while (true)
		{
			System.out.println("Select your queue:");
			System.out.println("[1] - Array FIFO");
			System.out.println("[2] - Doubly Linked FIFO");
			System.out.println("[3] - (Doubly Linked) Priority Queue");
			System.out.println("[4] - View Test Cases");

			input = sc.nextLine();

			// Array
			if (input.equals("1"))
			{
				if (queueMenu(new ArrayQueue<Assignment>(), input))
					break;
				continue;
			}

			// Linked
			if (input.equals("2"))
			{
				if (queueMenu(new DoublyLinkedQueue<Assignment>(), input))
					break;
				continue;
			}

			// Priority
			if (input.equals("3"))
			{
				if (queueMenu(new PriorityQueue(), input))
					break;
				continue;
			}

			// Test Cases
			if (input.equals("4"))
			{
				testCases();
				continue;
			}

			System.out.println("Invalid input.\n");
			// only reached if invalid option selected
		}

		System.out.println("Goodbye!");
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * This menu allows the user to interface with their selected {@link Queue}.
	 * 
	 * @param queue
	 *            the queue implementation to work with
	 * @param input
	 *            the input string, saves memory
	 * @return {@code true} if exiting, {@code false} otherwise
	 */
	public static boolean queueMenu(Queue<Assignment> queue, String input)
	{
		while (true)
		{
			System.out.println("Select a function:");
			System.out.println("[1] - Enqueue Assignment");
			System.out.println("[2] - Dequeue Assignment");
			System.out.println("[3] - Choose another queue (NOTE: This will delete your current queue.)");
			System.out.println("[0] - Quit");

			input = sc.nextLine();

			if (input.equals("1"))
			{
				queue.enqueue(buildAssignment());
				System.out.println("Assignment queued.");
				promptContinue();
				continue;
			}
			if (input.equals("2"))
			{
				Assignment temp = queue.dequeue();
				if (temp == null)
				{
					System.out.println("No assignment currently on queue.");
				} else
				{
					System.out.println("Assignment dequeued:");
					System.out.println(temp);
				}
				promptContinue();
				continue;
			}
			if (input.equals("3"))
			{
				System.out.println();
				break;
			}
			if (input.equals("0"))
			{
				return true;
			}

			System.out.println("Invalid input.\n");
			// only reached if invalid option selected
		}

		return false;
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * This method guides the user through creating an {@link Assignment} to
	 * enqueue.
	 * 
	 * @return the assignment
	 */
	public static Assignment buildAssignment()
	{
		System.out.println("Input a title for the assignment:");
		String title = sc.nextLine();

		int value = 0;
		while (true)
		{
			System.out.println("Input a value for the assignment:");

			try
			{
				value = sc.nextInt();
				sc.nextLine();
				if (value < 0)
					throw new NullPointerException();
				// exception here is just to differentiate error messages
			} catch (TypeMismatchException e)
			{
				System.out.println("Please input an integer value.");
				promptContinue();
				continue;
			} catch (NullPointerException e)
			{
				System.out.println("Value cannot be negative.");
				promptContinue();
				continue;
			}

			break;
		}

		int daysTilDue = 0;
		while (true)
		{
			System.out.println("Input the days until due for the assignment:");

			try
			{
				daysTilDue = sc.nextInt();
				sc.nextLine();
				if (daysTilDue < 0)
					throw new NullPointerException();
			} catch (TypeMismatchException e)
			{
				System.out.println("Please input an integer value.");
				promptContinue();
				continue;
			} catch (NullPointerException e)
			{
				System.out.println("Days until due cannot be negative.");
				promptContinue();
				continue;
			}

			break;
		}

		return new Assignment(title, value, daysTilDue);
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * Honestly you should know what this one does
	 */
	public static void promptContinue()
	{
		System.out.println("Press ENTER to continue.");
		sc.nextLine();
		System.out.println();
	}

	// ---------------------------------------------------------------------------------------------------

	/**
	 * This method runs through all test cases, demonstrating the functionality
	 * and edge cases of the queues.
	 */
	public static void testCases()
	{
		ArrayQueue<Assignment> aq = new ArrayQueue<>();
		DoublyLinkedQueue<Assignment> dq = new DoublyLinkedQueue<>();
		PriorityQueue pq = new PriorityQueue();

		System.out.println("Test Case 1: All queues enqueue successfully");
		{
			System.out.println("Enqueueing Assignment: \"this is an assignment\"");
			Assignment temp = new Assignment("this is an assignment", 0, 0);
			System.out.println("ArrayQueue: " + aq.enqueue(temp));
			System.out.println("DoublyLinkedQueue: " + dq.enqueue(temp));
			System.out.println("PriorityQueue: " + pq.enqueue(temp));
			promptContinue();
		} // no longer need temp

		System.out.println("Test Case 2: All queues dequeue correctly");
		System.out.println("Dequeing from all queues. Expected: \"this...\" dequeued:");
		System.out.println("Array Queue: ");
		System.out.println(aq.dequeue());
		System.out.println("Doubly Linked Queue: ");
		System.out.println(dq.dequeue());
		System.out.println("Priority Queue: ");
		System.out.println(pq.dequeue());
		promptContinue();

		System.out.println("Test Case 3: Empty queues return null when dequeueing while empty");
		System.out.println("Array Queue: ");
		System.out.println(aq.dequeue());
		System.out.println("Doubly Linked Queue: ");
		System.out.println(dq.dequeue());
		System.out.println("Priority Queue: ");
		System.out.println(pq.dequeue());
		promptContinue();

		System.out.println("Test Case 4: FIFO Queues maintain order when enqueueing and dequeueing multiple items");
		{
			System.out.println("Enqueueing Assignment: \"first\"");
			Assignment temp = new Assignment("first", 0, 0);
			aq.enqueue(temp);
			dq.enqueue(temp);

			System.out.println("Enqueueing Assignment: \"second\"");
			temp = new Assignment("second", 0, 0);
			aq.enqueue(temp);
			dq.enqueue(temp);

			System.out.println("Enqueueing Assignment: \"third\"");
			temp = new Assignment("third", 0, 0);
			aq.enqueue(temp);
			dq.enqueue(temp);

			System.out.println("Dequeuing. Expected: first, second, then third");
			System.out.println("Array Queue: ");
			System.out.println(aq.dequeue());
			System.out.println(aq.dequeue());
			System.out.println(aq.dequeue());
			System.out.println("Doubly Linked Queue: ");
			System.out.println(dq.dequeue());
			System.out.println(dq.dequeue());
			System.out.println(dq.dequeue());
			promptContinue();
		}

		System.out.println("Test Case 5: FIFO Queues implemented generically");
		{
			System.out.println("Initializing Integer FIFO Queues");
			ArrayQueue<Integer> aqt = new ArrayQueue<>();
			DoublyLinkedQueue<Integer> dqt = new DoublyLinkedQueue<>();

			System.out.println("Adding 1, 2, 3, 4, 5");
			for (int i = 1; i <= 5; i++)
			{
				aqt.enqueue(i);
				dqt.enqueue(i);
			}

			System.out.println("Dequeueing. Expected: 1, 2, 3, 4, 5");
			System.out.println("Array Queue: ");
			System.out.println(aqt.dequeue());
			System.out.println(aqt.dequeue());
			System.out.println(aqt.dequeue());
			System.out.println(aqt.dequeue());
			System.out.println(aqt.dequeue());
			System.out.println("Doubly Linked Queue: ");
			System.out.println(dqt.dequeue());
			System.out.println(dqt.dequeue());
			System.out.println(dqt.dequeue());
			System.out.println(dqt.dequeue());
			System.out.println(dqt.dequeue());
			promptContinue();
		}

		System.out.println("Test Case 6: Priority Queue dequeues highest priority first");
		System.out.println("Test Case 7: Properly handles an assignment with 0 days remaining (due today)");
		System.out.println("Enqueing the following:");
		System.out.println("Assignment: \"first\"; value: 1; days until due: 1 (effective priority = 1)");
		pq.enqueue(new Assignment("first", 1, 1));
		System.out.println("Assignment: \"second\"; value: 1; days until due: 4 (effective priority = 0)");
		pq.enqueue(new Assignment("second", 1, 4));
		System.out.println(
				"Assignment: \"third\"; value: 1; days until due: 0 (effective priority = above all not due today)");
		pq.enqueue(new Assignment("third", 1, 0));

		System.out.println("Dequeueing. Expected: third, first, second");
		System.out.println(pq.dequeue());
		System.out.println(pq.dequeue());
		System.out.println(pq.dequeue());
		promptContinue();

		System.out.println(
				"Test Case 8: Differentiates priority between assignments with value lower than days remaining (int restraints)");
		System.out.println("Enqueing the following:");
		System.out.println("Assignment: \"first\"; value: 1; days until due: 4 (lower priority)");
		pq.enqueue(new Assignment("first", 1, 4));
		System.out.println("Assignment: \"second\"; value: 1; days until due: 3 (higher priority)");
		pq.enqueue(new Assignment("second", 1, 3));
		System.out.println("Dequeueing. Expected: second, first");
		System.out.println(pq.dequeue());
		System.out.println(pq.dequeue());
		promptContinue();
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////