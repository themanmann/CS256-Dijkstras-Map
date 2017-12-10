///////////////////////////////////////////////////////////////////////////////
public class Assignment implements Comparable<Assignment>
{
	private String title;
	private int value;
	private int daysTilDue;

	// -----------------------------------------------------------------------------
	public Assignment(String title, int value, int daysTilDue)
	{
		this.title = title;
		this.value = value;
		this.daysTilDue = daysTilDue;
	}

	// -----------------------------------------------------------------------------
	public String getTitle()
	{
		return title;
	}

	// -----------------------------------------------------------------------------
	public void setTitle(String title)
	{
		this.title = title;
	}

	// -----------------------------------------------------------------------------
	public int getValue()
	{
		return value;
	}

	// -----------------------------------------------------------------------------
	public void setValue(int value)
	{
		this.value = value;
	}

	// -----------------------------------------------------------------------------
	public int getDaysTilDue()
	{
		return daysTilDue;
	}

	// -----------------------------------------------------------------------------
	public void setDaysTillDue(int daysTilDue)
	{
		this.daysTilDue = daysTilDue;
	}

	// -----------------------------------------------------------------------------
	public int compareTo(Assignment that)
	{
		return this.value / this.daysTilDue - that.value / that.daysTilDue;
	}

	// -----------------------------------------------------------------------------
	public String toString()
	{
		return "Title: " + this.title + "\nValue: " + this.value + "\nDays Until Due: " + this.daysTilDue;
	}
	// -----------------------------------------------------------------------------
}
///////////////////////////////////////////////////////////////////////////////