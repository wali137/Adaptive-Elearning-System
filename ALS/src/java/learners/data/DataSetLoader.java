/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.data;

//import com.sun.xml.internal.ws.message.saaj.SAAJMessage;
//import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
//import javax.sql.rowset.serial.SerialArray;

/**
 *
 * @author Dv6
 */
public class DataSetLoader {
    
 
    // JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static String DATABASE_URL = "jdbc:mysql://localhost:3306/wekatest";
   static final String CLASS_NAME = "com.mysql.jdbc.Driver";
   
   //  Database credentials
   static String USERNAME = "root";
   static String PASSWORD = "";
    
    
   
   
    public static String getDATABASE_URL() {
        return DATABASE_URL;
    }

    public static void setDATABASE_URL(String DATABASE_URL) {
        DataSetLoader.DATABASE_URL = DATABASE_URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static void setUSERNAME(String USERNAME) {
        DataSetLoader.USERNAME = USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void setPASSWORD(String PASSWORD) {
        DataSetLoader.PASSWORD = PASSWORD;
    }
   
   
     public DataSet DatasetLoader(String SQL_Query, DataSet dataset)
    {
        try
            {
                
           Connection connection;
           Statement statement;
           DataColumn column;
           DataRow row;
            
               Class.forName(CLASS_NAME);
               connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
               
               statement = connection.createStatement();
                
               ResultSet result = statement.executeQuery(SQL_Query);
               ResultSetMetaData result_meta = result.getMetaData();
               
               
               for(int i=0;i<result_meta.getColumnCount();i++)
               {
                column = new DataColumn(result_meta.getColumnName(i+1), TypeMapper.JavaConvert(result_meta.getColumnType(i+1)));
                column.setDataSet(dataset);
                dataset.Columns().Add(column);
               }
               
              while(result.next())
              {   
                    row = new DataRow(); 
                    row.setDataDataSet(dataset);
                    for(DataColumn col : dataset.Columns())
                    {
                       row.set(col, result.getObject(col.getColumnName()));
                    }
                  dataset.Rows().Add(row);
              }
              
              dataset.setClassIndex(dataset.Columns().getCount()-1);
              
              return dataset;
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                System.out.println(ex.getMessage());  
                return null;
            }
    }
    
     
     
     public DataSet StoredProcedure_DatasetLoader(String StoredProcedureStatement,List<Object> Parameters, DataSet dataset)
    {
        try
            {
                
           Connection connection;
           DataColumn column;
           DataRow row;
           int ParameterCount;
               Class.forName(CLASS_NAME);
               connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
               CallableStatement cStmt =  connection.prepareCall(StoredProcedureStatement);
               
               if(Parameters != null)
               {
                   ParameterCount = 1;
                   for(Object param : Parameters)
                   {
                       cStmt.setObject(ParameterCount++, param, TypeMapper.SQLConvert(param.getClass()));
                   }
                   
               }
               
               cStmt.execute();
               ResultSet result = cStmt.getResultSet();
               ResultSetMetaData result_meta = result.getMetaData();
               
               
               for(int i=0;i<result_meta.getColumnCount();i++)
               {
                column = new DataColumn(result_meta.getColumnName(i+1), TypeMapper.JavaConvert(result_meta.getColumnType(i+1)));
                column.setDataSet(dataset);
                dataset.Columns().Add(column);
               }
               
              while(result.next())
              {   
                    row = new DataRow(); 
                    row.setDataDataSet(dataset);
                    for(DataColumn col : dataset.Columns())
                    {
                       row.set(col, result.getObject(col.getColumnName()));
                    }
                  dataset.Rows().Add(row);
              }
              
              dataset.setClassIndex(dataset.Columns().getCount()-1);
              
              return dataset;
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                System.out.println(ex.getMessage());  
                return null;
            }
    }
}
