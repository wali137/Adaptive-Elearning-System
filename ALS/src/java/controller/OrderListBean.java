/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;  
import java.util.List;  
  

  
public class OrderListBean {  
  
    private List<String> players;  
      
    private List<String> cities;  
  
    public OrderListBean() {  
          
        //Strings  
        players = new ArrayList<String>();  
        players.add("Ali");  
        players.add("Waleed");  
        players.add("Abeera");  
        //players.add(new String("Xavi", 6, "xavi.jpg"));  
          
        //Cities  
        cities = new ArrayList<String>();  
          
        cities.add("Ali");  
        cities.add("Waleed");  
        cities.add("Abeera");    
    }  
  
    public List<String> getCities() {  
        return cities;  
    }  
    public void setCities(List<String> cities) {  
        this.cities = cities;  
    }  
  
    public List<String> getStrings() {  
        return players;  
    }  
    public void setStrings(List<String> players) {  
        this.players = players;  
    }  
}  