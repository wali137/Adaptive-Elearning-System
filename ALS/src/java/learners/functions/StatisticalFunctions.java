
package learners.functions;

import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Vector;


public class StatisticalFunctions {
    
  
  public static Double[] v2a(Vector v) {
    Double[] d = new Double[v.size()];
    int i = 0;
    for (Enumeration e = v.elements(); e.hasMoreElements();)
      d[i++] = ((Number) e.nextElement()).doubleValue();
    return d;
  }

  
  public static Double sqr(Double x) {
    return x * x;
  }

  
  public static Double mean(Double[] v) {
    Double tot = 0.0;
    for (int i = 0; i < v.length; i++)
    {    if(v[i] != null)
            {
              tot += v[i];
            }
    }
    
    return tot / v.length;
  }

  
  public static Double mean(int[] v) {
    Double tot = 0.0;
    for (int i = 0; i < v.length; i++)
      tot += v[i];
    return tot / v.length;
  }

  
  public static Double sdev(Double[] v) {
    return Math.sqrt(variance(v));
  }


  public static Double stderr(Double[] v) {
    return sdev(v) / Math.sqrt(v.length);
  }


  public static Double variance(Double[] v) {
    Double mu = mean(v);
    Double sumsq = 0.0;
    for (int i = 0; i < v.length; i++)
      sumsq += sqr(mu - v[i]);
    return sumsq / (v.length);
    
  }


  private static Double variance2(Double[] v) {
    Double mu = mean(v);
    Double sumsq = 0.0;
    for (int i = 0; i < v.length; i++)
      sumsq += sqr(v[i]);
    System.out.println(sumsq + " : " + mu);
    Double diff = (sumsq - v.length * sqr(mu));
    System.out.println("Diff = " + diff);
    return diff / (v.length);
  }

 

  public static Double covar(Double[] v1, Double[] v2) {
    Double m1 = mean(v1);
    Double m2 = mean(v2);
    Double sumsq = 0.0;
    for (int i = 0; i < v1.length; i++)
      sumsq += (m1 - v1[i]) * (m2 - v2[i]);
    return sumsq / (v1.length);
  }

  public static Double correlation(Double[] v1, Double[] v2) {
    // an inefficient implementation!!!
    return covar(v1, v2) / (sdev(v1) * sdev(v2));
  }

  public static Double correlation2(Double[] v1, Double[] v2) {
    // an inefficient implementation!!!
    return sqr(covar(v1, v2)) / (covar(v1, v1) * covar(v2, v2));
  }

  
  public static Double max(Double[] v) {
    Double m = v[0];
    for (int i = 1; i < v.length; i++)
      m = Math.max(m, v[i]);
    return m;
  }

 
  public static Double min(Double[] v) {
    Double m = v[0];
    for (int i = 1; i < v.length; i++)
      m = Math.min(m, v[i]);
    return m;
  }

    
}
