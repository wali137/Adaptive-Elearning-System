/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package learners.classifiers;

/**
 *
 * @author dell
 */
public class Sets {
    
    String Id;
    double Value;

    public Sets(String Id,double Val){
       
        this.Id = Id;
        this.Value = Val;
        
    }
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double Value) {
        this.Value = Value;
    }
}
