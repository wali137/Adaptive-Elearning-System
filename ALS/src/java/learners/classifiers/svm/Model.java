package learners.classifiers.svm;
/**
 * Class for representing a model
 * @author miafranc
 *
 */
public class Model extends Parser{
	/** Array of alpha */
	public double [] alpha;
	/** Bias */
	public double b;
	/** Kernel parameters */
	KernelParams params; 
	
}
