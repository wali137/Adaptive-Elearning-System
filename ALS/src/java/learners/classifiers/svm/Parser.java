package learners.classifiers.svm;

import java.io.*;
import java.util.*;
import learners.data.DataRow;
import learners.data.DataSet;

/**
 * Class representing an optimization problem (a data setting);
 * taken from liblinear; "bias" excluded
 * @author miafranc
 *
 */
public class Parser {
        private int count = 0;
        /** The number of training data */
	public int l;
	/** The number of features (including the bias feature if bias &gt;= 0) */
	public int n;
	/** Array containing the target values */
	public int[] y;
	/** Map of categories to allow various ID's to identify classes with. */
	public CategoryMap<Integer> catmap;
	/** Array of sparse feature nodes */
	public FeatureNode[][] x;
        
        public ArrayList<String[]> test = new ArrayList<>();
        
	public Parser() {
		l = 0;
		n = 0;
		catmap = new CategoryMap<Integer>();
	}
        
        
        
        public FeatureNode [] parseRow(String [] row) {
		FeatureNode [] example = new FeatureNode[row.length-1];
		int maxindex = 0;
		for (int i=0; i<row.length-1; i++) {
			String [] iv = row[i].split(":");
			int index = Integer.parseInt(iv[0]);
//			if (index <= maxindex) {
//				throw new IllegalArgumentException("indices must be in increasing order!");
//			}
			maxindex = index;
			double value = Double.parseDouble(iv[1]);
			example[i] = new FeatureNode(index, value);
		}
		if (n < maxindex)
			n = maxindex;
		return example;
	}
        
        public void loadBinaryProblem(DataSet d)
        {
            String row;
		ArrayList<Integer> classes = new ArrayList<Integer>();
		ArrayList<FeatureNode []> examples = new ArrayList<FeatureNode []>();
                
                count = 0;
                for(DataRow rx : d.Rows())
                {
                    count++;
                    Object [] elemx = rx.getItemArray();
                    String [] elems = new String[elemx.length];
                    for(int i=0;i<elemx.length-1;i++)
                    {
                        elemx[i] = elemx[i].toString()+":1";
                    }
                    for(int i=0;i<elemx.length;i++)
                    {
                        elems[i] = elemx[i].toString();
                    }
                    
                  if (count <=  5)//Math.ceil(d.Rows().getCount()/4))
                  {
                   test.add(elems);
                  }
                   	//Category:
				if (elems[elems.length-1].charAt(0) == '+') {//to allow for example +1 as class label
					elems[elems.length-1] = elems[elems.length-1].substring(1);
				}
				Integer cat = Integer.parseInt(elems[elems.length-1]);
				catmap.addCategory(cat);
				if (catmap.size() > 2) {
					throw new IllegalArgumentException("only 2 classes allowed!");
				}
				classes.add(catmap.getNewCategoryOf(cat));
                                //classes.add(cat);
				//Index/value pairs:
				examples.add(parseRow(elems));
			}
			x = new FeatureNode[examples.size()][];
			y = new int[examples.size()];
			for (int i=0; i<examples.size(); i++) {
				x[i] = examples.get(i);
				y[i] =  2*classes.get(i)-1;    //2*classes.get(i)-1; //0,1 => -1,1
			}
			l = examples.size();
                   
                    
                }
       
}
