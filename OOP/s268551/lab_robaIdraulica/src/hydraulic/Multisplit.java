package hydraulic;

/**
 * Represents a multisplit element, an extension of the Split that allows many outputs
 * 
 * During the simulation each downstream element will
 * receive a stream that is determined by the proportions.
 */

public class Multisplit extends Split {
	
	private double[] proportions;
	private int numOutput;
	/**
	 * Constructor
	 * @param name
	 * @param numOutput
	 */
	public Multisplit(String name, int numOutput) {
		super(name, numOutput);
		this.numOutput = numOutput;
	
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
    	//TODO: complete
        return this.outputs;
    }

    /**
     * connect one of the outputs of this split to a
     * downstream component.
     * 
     * @param elem  the element to be connected downstream
     * @param noutput the output number to be used to connect the element
     */
	public void connect(Element elem, int noutput){
		//TODO: complete
		outputs[noutput]=elem;
	}
	
	/**
	 * Define the proportion of the output flows w.r.t. the input flow.
	 * 
	 * The sum of the proportions should be 1.0 and 
	 * the number of proportions should be equals to the number of outputs.
	 * Otherwise a check would detect an error.
	 * 
	 * @param proportions the proportions of flow for each output
	 */
	public void setProportions(double... proportions) {
		// TODO: to be implemented
		this.proportions=proportions;
	}
	
	@Override
	protected
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Split +-> ");
		
		String subPad = pad + blanks(res.length()-4) ;
		
		for(int i=0; i<this.numOutput; ++i) {
			if(i>0) {
				res.append("\n");
				res.append(subPad).append("|\n");
				res.append(subPad + "+-> ");
			}
			res.append( getOutputs()[i].layout(subPad+"|   ") );
		}
		return res;
	}
	
	@Override
	public void simulate(SimulationObserver observer,double flow,boolean check) {
		
		
		double[] outFlow = new double[numOutput];
		for(int i=0; i<numOutput; ++i) {
			outFlow[i] = flow * proportions[i];
		}
		observer.notifyFlow("MultiSplit", getName(), flow, outFlow);
		if(check==true && flow>maxFlow) ((SimulationObserverExt)(observer)).notifyFlowError("MultiSlit", name, flow, maxFlow);
		
		for(int i=0; i<outFlow.length; ++i) {
		    Element e = getOutputs()[i];
			e.simulate(observer,outFlow[i],check);
		}
	}
	
}
