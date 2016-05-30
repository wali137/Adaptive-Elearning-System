/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Dv6
 */
public class DataModel {
    
    public Object datamodel = null;
   
    public void WriteDataModelWeb(String ModelQuery,Object Classifer)
    {
        
    }
    
    public Object ReadDataModelWeb(String ModelQuery)
    {
        return null;
    }
    
    public void WriteDataModelFile(String Path,Object Classifer)
    {
          try{
              
        
		FileOutputStream FileOut = new FileOutputStream(Path);
		ObjectOutputStream OutStream = new ObjectOutputStream(FileOut);
                
		OutStream.writeObject(Classifer);
		OutStream.close();
 
	   }catch(Exception ex){
		   ex.printStackTrace();
                   System.out.println(ex.getMessage());
	   }
    }
    
    
    public Object ReadDataModelFile(String Path)
    {
          try{
              
		FileInputStream FileIn = new FileInputStream(Path);
		ObjectInputStream InputStream = new ObjectInputStream(FileIn);   
		datamodel = InputStream.readObject();
		InputStream.close();
                return datamodel;
 
	   }catch(Exception ex){
		   ex.printStackTrace();
                   System.out.println(ex.getMessage());
                   return null;
	   }
    }
    
   
    
    
}
