package learners.data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.SysexMessage;


public class DataSet implements Cloneable
{
	

	private String datasetName = "DataSet";
	
	private DataRowCollection Rows;
	
	private DataColumnCollection Columns;

        private int classIndex = -1;

        
        
        
        
        
    @Override
    public Object clone() {
        try
        {
        return super.clone();
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    

  
       
       
   
	public DataSet()
	{
		Rows = new DataRowCollection(this);
		Columns = new DataColumnCollection(this);
	}
	
	public DataSet(String DataSetName)
	{
		this();
		datasetName = DataSetName;
	}
        
        
        public Integer getClassIndex() 
        {
            return classIndex;
        }

        public void setClassIndex(Integer classIndex) 
        {
            this.classIndex = classIndex;
        }
	
	public String getDataSetName()
	{
		return datasetName;
	}
	
	public void setDataSetName(String DataSetName)
	{
		datasetName = DataSetName;
	}
	
	public DataRowCollection Rows()
	{
		return Rows;
	}
	
	public DataColumnCollection Columns()
	{
		return Columns;
	}
	
	public void Clear()
	{
		Rows.Clear();
	}
	
	public DataRow NewRow()
	{
		DataRow dr = new DataRow();
		dr.setDataDataSet(this);
		return dr;
	}
        
        public <T> List<T> getColumnList(String columnname)
        {
            DataColumn column = this.Columns.get(columnname);
            List<Object> column_data = new ArrayList<>();
            
             for(DataRow row : this.Rows())
               column_data.add(row.get(column));

            
            return (List<T>)column_data;
        }
        
        
          public <T> List<T> getColumnList(int index)
        {
            DataColumn column = this.Columns.get(index);
            List<Object> column_data = new ArrayList<>();
            
             for(DataRow row : this.Rows())
               column_data.add(row.get(column));

            
            return (List<T>)column_data;
        }
          
          
          
          public <T> List<T> getColumnList(DataColumn column)
        {
            List<Object> column_data = new ArrayList<>();
            
             for(DataRow row : this.Rows())
               column_data.add(row.get(column));
            
            return (List<T>)column_data;
        }
        
        
         public DataRow getRow(int row)
        {
            return this.Rows().get(row);
        }
         
         
         public <T> List<T> getRowList(int row)
        {
            return (List<T>) this.Rows().get(row).getAlData();
        }
	
}
