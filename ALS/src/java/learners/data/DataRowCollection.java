package learners.data;

import java.util.ArrayList;
import java.util.Iterator;


public class DataRowCollection implements Iterable<DataRow>
{
	
	private DataSet dataDataSet;
	
	protected ArrayList<DataRow> dataRow;

	
	public DataRowCollection(DataSet dt)
	{
		dataRow = new ArrayList<DataRow>();
		dataDataSet = dt;
	}
	
	public int getCount()
	{
		if (dataRow == null) return 0;
		return dataRow.size();
	}
	
	DataSet getDataDataSet()
	{
		return dataDataSet;
	}
	
	public void Clear()
	{
		dataRow.clear();
	}
	
	public void Add(DataRow dr)
	{
		dataRow.add(dr);
	}
	
	public void Add(Object[] values)
	{
		DataRow dr = new DataRow(values);
		dr.setDataDataSet(dataDataSet);
		dataRow.add(dr);
	}
	
	public boolean Contains(DataRow dr)
	{
		return dataRow.contains(dr);
	}
	
	public void InsertAt(DataRow dr, int pos)
	{
		dataRow.add(pos, dr);
	}
	
	public void Remove(DataRow dr)
	{
		dataRow.remove(dr);
	}
	
	public void RemoveAt(int index)
	{
		dataRow.remove(index);
	}
	
	public DataRow get(int index)
	{
		return (DataRow) dataRow.get(index);
	}
	public Iterator<DataRow> iterator()
	{
		return new Iterator<DataRow>()
		{
			private int index = 0;

			public boolean hasNext()
			{
				return index < getCount();
			}
			public DataRow next()
			{
				return get(index++);
			}
			public void remove()
			{
				RemoveAt(index--);
			}
		};
	}
}
