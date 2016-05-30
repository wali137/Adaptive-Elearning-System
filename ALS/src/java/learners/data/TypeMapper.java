/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.data;


import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;

/**
 *
 * @author Dv6
 */
public class TypeMapper {


   
    public static Class<?> JavaConvert( int type ) {
  
    switch( type ) {
      case Types.CHAR:
      case Types.VARCHAR:
      case Types.LONGVARCHAR:
          return String.class;
        

      case Types.NUMERIC:
      case Types.DECIMAL:
        return BigDecimal.class;
        
      case Types.BIT:
        return Boolean.class;
        
      case Types.TINYINT:
        return Byte.class;
        
      case Types.SMALLINT:
        return Short.class;
        
      case Types.INTEGER:
        return Integer.class;
        
      case Types.BIGINT:
        return Long.class;
        
      case Types.REAL:
        return Number.class;
        
      case Types.FLOAT:
      case Types.DOUBLE:
        return Double.class;
        
      case Types.BINARY:
      case Types.VARBINARY:
      case Types.LONGVARBINARY:
        return Byte[].class;
        
      case Types.DATE:
        return Date.class;
        
      case Types.TIME:
        return String.class;
        
      case Types.TIMESTAMP:
        return String.class;
     
    }
        return null;
  }
    
    
    
    public static int SQLConvert(Class<?> type ) {
  
     {
         if(type.equals(String.class))
         {
             return Types.VARCHAR;          
         }
         else if( type.equals(Integer.class) || type.equals(BigDecimal.class) || type.equals(Short.class) || type.equals(Number.class))
         {
             return Types.INTEGER;          
         }
         else if( type.equals(Date.class))
         {
             return Types.DATE;          
         }
        
    }
        return -1;
  }
}
