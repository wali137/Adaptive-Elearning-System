package learners.classifiers.neural;



public class Synapse {
    
    private double weight = 1.0;
    private Neuron neuron;
    
    
    public Synapse(Neuron neuron) {
        this.neuron = neuron;
    }

   
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

  
    public Neuron getNeuron() {
        return neuron;
    }
    
}
