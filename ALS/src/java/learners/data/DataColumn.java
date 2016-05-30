package learners.data;


public class DataColumn
{
	private String columnName;
	private Class<?> dataType;
	private DataSet dataset;
        private String description;
        
	public DataColumn()
	{
	}
	
	public DataColumn(String ColumnName)
	{
		columnName = ColumnName.toUpperCase();
	}
	
	public DataColumn(String ColumnName, Class<?> DataType)
	{
		this(ColumnName);
		dataType = DataType;
	}
	
	public String getColumnName()
	{
		return columnName;
	}
	
	public void setColumnName(String ColumnName)
	{
		columnName = ColumnName;
	}
        
	public Class<?> getDataType()
	{
		return dataType;
	}
	
	public void setDataType(Class<?> DataType)
	{
		dataType = DataType;
	}
        
	
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String Description)
	{
		description = Description;
	}
	
	public DataSet getDataSet()
	{
		return dataset;
	}
	
	public void setDataSet(DataSet DataSet)
	{
		this.dataset = DataSet;
	}
	
	public int getOrdinal()
	{
		if (this.dataset == null) {
                return -1;
            }
		return this.dataset.Columns().IndexOf(this);
	}
}
