package learners.classifiers.svm;

import java.io.Serializable;
import java.util.*;
import learners.classifiers.Classifer;
import learners.data.DataRow;
import learners.data.DataRowCollection;
import learners.data.DataSet;

/**
 * Main class containing the SVM's train and test metods
 * @author miafranc
 *
 */
public class SVM implements Classifer,Serializable{
	/** Trained/loaded model */
	private Model model;
	/** Regularization parameter */
	private double C = 1;
	/** Tolerance */
	private double tol = 10e-3;
	/** Tolerance */
	private double tol2 = 10e-5;

	
	/** Number of times to iterate over the alpha's without changing; used in SMO_simple */
	private int maxpass = 10;
	
	/*some global variables of the SMO algorithm*/
	private double Ei, Ej;
	private double ai_old, aj_old;//, b_old;
	private double L, H;
	
        KernelParams kernel = new KernelParams();
        Parser p = new Parser();
  
        
	/* ---------------------------------------- */

	public SVM() {
	}
	

	
	/* =====================================================
	 * Simple, probabilistic SMO
	   ===================================================== */
	/**
	 * Probabilistic (random, simple) SMO
	 * @param train Training set
	 * @param p Kernel parameters
	 */
	private void SMO_simple(Parser train, KernelParams p) {
		int pass = 0;
		int alpha_change = 0;
		int i, j;
		double eta;
		//Initialize:
		model = new Model();
                
		model.alpha = new double [train.l];
		model.b = 0;
		model.params = p;
		model.x = train.x;
		model.y = train.y;
		model.l = train.l;
		model.n = train.n;
		//Main iteration:
		while (pass < maxpass) {
			alpha_change = 0;
			for (i=0; i<train.l; i++) {
				Ei = svmTestOne(train.x[i]) - train.y[i];
				if ((train.y[i]*Ei<-tol && model.alpha[i]<C) || (train.y[i]*Ei>tol && model.alpha[i]>0)) {
					j = (int)Math.floor(Math.random()*(train.l-1));
					j = (j<i)?j:(j+1);
					Ej = svmTestOne(train.x[j]) - train.y[j];
					ai_old = model.alpha[i];
					aj_old = model.alpha[j];
					L = computeL(train.y[i], train.y[j]);
					H = computeH(train.y[i], train.y[j]);
					if (L == H) //next i
						continue;
					double kij = kernel(i,j); 
					double kii = kernel(i,i); 
					double kjj = kernel(j,j); 
					eta = 2*kij-kii-kjj;
					if (eta >= 0) //next i
						continue;
					model.alpha[j] = aj_old - (train.y[j]*(Ei-Ej))/eta;
					if (model.alpha[j] > H)
						model.alpha[j] = H;
					else if (model.alpha[j] < L)
						model.alpha[j] = L;
					if (Math.abs(model.alpha[j]-aj_old) < tol2) //next i
						continue;
					model.alpha[i] = ai_old + train.y[i]*train.y[j]*(aj_old-model.alpha[j]);
					computeBias(model.alpha[i], model.alpha[j], train.y[i], train.y[j], kii, kjj, kij);
					alpha_change++;
				}
			}
			if (alpha_change == 0)
				pass++;
			else
				pass = 0;
			//if (alpha_change > 0)
				//System.out.print(".");
			//else
				//System.out.print("*");
		}
//		System.out.println();
	}
	/* =====================================================
	 * ===================================================== */

	private double smoObj(double aj, int yi, int yj, double kij, double kii, double kjj, double vi, double vj) {
		double s = yi*yj;
		double gamma = ai_old + s*aj_old;
		return (gamma + (1-s)*aj - 0.5*kii*(gamma-s*aj)*(gamma-s*aj) - 0.5*kjj*aj*aj +
				- s*kij*(gamma-s*aj)*aj - yi*(gamma-s*aj)*vi - yj*aj*vj);
	}
	
	/**
	 * Computes L
	 * @param yi
	 * @param yj
	 * @return Returns L
	 */
	private double computeL(int yi, int yj) {
		double L = 0;
		if (yi != yj) {
			L = Math.max(0, -ai_old+aj_old);
		} else {
			L = Math.max(0, ai_old+aj_old-C);
		}
		return L;
	}
	/**
	 * Computes H
	 * @param yi
	 * @param yj
	 * @return Returns H
	 */
	private double computeH(int yi, int yj) {
		double H = 0;
		if (yi != yj) {
			H = Math.min(C, -ai_old+aj_old+C);
		} else {
			H = Math.min(C, ai_old+aj_old);
		}
		return H;
	}
	/**
	 * Computes the bias and stores in model.b
	 * @param ai
	 * @param aj
	 * @param yi
	 * @param yj
	 * @param kii
	 * @param kjj
	 * @param kij
	 */
	private void computeBias(double ai, double aj, int yi, int yj, double kii, double kjj, double kij) {
		double b1 = model.b - Ei - yi*(ai-ai_old)*kii - yj*(aj-aj_old)*kij;
		double b2 = model.b - Ej - yi*(ai-ai_old)*kij - yj*(aj-aj_old)*kjj;
		if (0 < ai && ai<C)
			model.b = b1;
		else if (0 < aj && aj < C)
			model.b = b2;
		else
			model.b = (b1+b2)/2;		
	}
        
	/**
	 * Test one example
	 * @param x The test example
	 * @return Class of x: -1 or 1
	 */
	public double svmTestOne(FeatureNode [] x) {
		double f = 0;
		for (int i=0; i<model.l; i++) {
			f += model.alpha[i]*model.y[i]*kernel(x, model.x[i]);
		}
		return f+model.b;
	}
	/**
	 * Based on the kernel parameters/settings of the model,
	 * calculates the kernel value between two points
	 * @param x First point/vector
	 * @param z Second point/vector
	 * @return Kernel value between x and z
	 */
	private double kernel(FeatureNode [] x, FeatureNode [] z) {
		double ret = 0;
		if (model.params == null)
			model.params = new KernelParams(1,1,1,1);
		switch (model.params.kernel) {
		case 0: //user defined
			break;
		case 1: //linear
			ret = Kernel.kLinear(x, z);
			break;
		case 2: //polynomial
			ret = Kernel.kPoly(x, z, model.params.a, model.params.b, model.params.c);
			break;
		case 3: //gaussian
			ret = Kernel.kGaussian(x, z, model.params.a);
			break;
		case 4: //tanh
			ret = Kernel.kTanh(x, z, model.params.a, model.params.b);
			break;
		}
		return ret;
	}
	/**
	 * Based on the kernel parameters/settings of the model,
	 * calculates the kernel value between two points
	 * @param i Index of the first vector in model.x
	 * @param j Index of the second vector in model.x
	 * @return Kernel value between model.x[i] and model.x[j]
	 */
	private double kernel(int i, int j) {
		double ret = 0;
		if (model.params == null)
			model.params = new KernelParams(1,1,1,1);
		switch (model.params.kernel) {
		case 0: //user defined
			break;
		case 1: //linear
			ret = Kernel.kLinear(model.x[i], model.x[j]);
			break;
		case 2: //polynomial
			ret = Kernel.kPoly(model.x[i], model.x[j], model.params.a, model.params.b, model.params.c);
			break;
		case 3: //gaussian
			ret = Kernel.kGaussian(model.x[i], model.x[j], model.params.a);
			break;
		case 4: //tanh
			ret = Kernel.kTanh(model.x[i], model.x[j], model.params.a, model.params.b);
			break;
		}
		return ret;
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model m) {
		model = m;
	}
 
    public KernelParams getKernel() {
        return kernel;
    }

    public void setKernel(KernelParams kernel) {
        this.kernel = kernel;
    }
       
    public ArrayList<String[]> testInstances()
    {
        return p.test;
    }
        
    @Override
    public Classifer Train(DataSet dataset) {
       
        p.loadBinaryProblem(dataset);
        SMO_simple(p, kernel);
        return this;
    }

    @Override
    public String StringSummary() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<Object, Double> Classify(DataRow instance) {
        
        HashMap<Object,Double> classifed = new HashMap<>();
        Object [] elemx = instance.getItemArray();
                    String [] elems = new String[elemx.length];
                    for(int i=0;i<elemx.length-1;i++)
                    {
                        elemx[i] = elemx[i].toString()+":1";
                    }
                    for(int i=0;i<elemx.length;i++)
                    {
                        elems[i] = elemx[i].toString();
                    }
              classifed.put(instance, svmTestOne(p.parseRow(elems)));
          return classifed;
    }

    @Override
    public Double[] distributionForInstance(DataRowCollection instances) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
