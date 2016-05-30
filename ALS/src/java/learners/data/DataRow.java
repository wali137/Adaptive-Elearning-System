package learners.data;

import java.util.ArrayList;


public class DataRow
{
	
	private ArrayList<Object> alData;

  
	
	private DataSet dataDataSet;

	
	public DataRow(Object[] values)
	{
		this();
		for (int i = 0; i < values.length; i++)
		{
			alData.add(values[i]);
		}
	}
	
	public DataRow()
	{
		alData = new ArrayList<Object>();
	}
	
	public DataSet getDataDataSet()
	{
		return dataDataSet;
	}
	
	public void setDataDataSet(DataSet DataDataSet)
	{
		int count = DataDataSet.Columns().getCount();
		while (alData.size() < count)
		{
			alData.add(null);
		}
		if (alData.size() > count) alData.trimToSize();
		//
		dataDataSet = DataDataSet;
	}
        
        public ArrayList<Object> getAlData() 
        {
                return alData;
        }
	
	public Object[] getItemArray()
	{
		return alData.toArray();
	}
	
	public boolean IsNull(DataColumn dc)
	{
		int index = dc.getOrdinal();
		return IsNull(index);
	}
	
	public boolean IsNull(int index)
	{
		if (index == -1) return false;
		return alData.get(index) == null ? true : false;
	}
	
	public boolean IsNull(String columnname)
	{
		int index = dataDataSet.Columns().IndexOf(columnname.toUpperCase());
		return IsNull(index);
	}
	
	public Object get(int index)
	{
		return alData.get(index) != null ? alData.get(index) : "";
	}
	
	public void set(int index, Object value)
	{
		if (alData.size() <= index)
		{
			while (alData.size() <= index)
				alData.add(null);
		}
		alData.set(index, value);
	}
	
	public Object get(String columnname)
	{
		int index = dataDataSet.Columns().IndexOf(columnname.toUpperCase());
		return index == -1 ? null : get(index);
	}
	
	public void set(String columnname, Object value)
	{
		int index = dataDataSet.Columns().IndexOf(columnname.toUpperCase());
		set(index, value);
	}
	
	public Object get(DataColumn dc)
	{
		int index = dataDataSet.Columns().IndexOf(dc);
		return get(index);
	}
	
	public void set(DataColumn dc, Object value)
	{
		int index = dataDataSet.Columns().IndexOf(dc);
		set(index, value);
	}
        
        
        
}
