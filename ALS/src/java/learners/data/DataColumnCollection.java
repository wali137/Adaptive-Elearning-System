package learners.data;

import java.util.ArrayList;
import java.util.Iterator;


public class DataColumnCollection implements Iterable<DataColumn>
{
	
	private DataSet dataDataSet;
	
	private ArrayList<DataColumn> dataColumn;

	public DataColumnCollection(DataSet dt)
	{
		dataColumn = new ArrayList<DataColumn>();
		dataDataSet = dt;
	}
	
	public int getCount()
	{
		if (dataColumn == null) return 0;
		return dataColumn.size();
	}
	
	DataSet getDataDataSet()
	{
		return dataDataSet;
	}
	
	public void Add(DataColumn dc)
	{
		dc.setDataSet(this.dataDataSet);
		dataColumn.add(dc);
	}
	
	public void Add(String ColumnName)
	{
		DataColumn dc = new DataColumn(ColumnName);
		dataColumn.add(dc);
	}
	
	public void Add(String ColumnName, Class<?> DataType)
	{
		DataColumn dc = new DataColumn(ColumnName, DataType);
		dataColumn.add(dc);
	}
	
	public boolean Contains(DataColumn dc)
	{
		return dataColumn.contains(dc);
	}
	
	public boolean Contains(String columnname)
	{
		int index = this.IndexOf(columnname);
		if (index > -1)
			return true;
		else
			return false;
	}
	
	public int IndexOf(DataColumn dc)
	{
		return dataColumn.indexOf(dc);
	}
	
	public int IndexOf(String columnname)
	{
		for (int i = 0; i < dataColumn.size(); i++)
		{
			DataColumn dc = (DataColumn) dataColumn.get(i);
			if (dc.getColumnName().equals(columnname)) return i;
		}
		return -1;
	}
	
	public void Remove(DataColumn dc)
	{
		dataColumn.remove(dc);
	}
	
	public void Remove(String columnname)
	{
		int index = this.IndexOf(columnname);
		if (index > -1) this.RemoveAt(index);
	}
	
	public void RemoveAt(int index)
	{
		dataColumn.remove(index);
	}
	
	public DataColumn get(int index)
	{
		return (DataColumn) dataColumn.get(index);
	}
	
	public DataColumn get(String columnname)
	{
		int index = this.IndexOf(columnname);
		if (index > -1) return (DataColumn) dataColumn.get(index);
		return null;
	}
	public Iterator<DataColumn> iterator()
	{
		return new Iterator<DataColumn>()
		{
			private int index = 0;

			public boolean hasNext()
			{
				return index < getCount();
			}
			public DataColumn next()
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
