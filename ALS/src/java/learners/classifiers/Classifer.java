/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.classifiers;

import java.io.Serializable;
import java.util.HashMap;
import learners.data.DataRow;
import learners.data.DataRowCollection;
import learners.data.DataSet;

/**
 *
 * @author Dv6
 */
public interface Classifer {
    
    public Classifer Train(DataSet dataset);
    
    public String StringSummary();
    
    public HashMap<Object, Double> Classify(DataRow instance);
    
    public Double[] distributionForInstance(DataRowCollection instances);
    
    
    
}
