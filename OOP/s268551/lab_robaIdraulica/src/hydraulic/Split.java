package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends ElementExt {
	
	
	
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
		//TODO: complete
	}
    
	public Split(String name, int numOutput) {
		super(name,numOutput);
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
		if(noutput==0) this.outputs[0]=elem;
		if(noutput==1) this.outputs[1]=elem;
		elem.setInput(this);
		
		
		
	}

	@Override
	public void simulate(SimulationObserver observer,double flow) {
		
		simulate(observer, flow, false);
		
		
	}
	
	@Override
	protected
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Split +-> ");
		
		
		String subPad = pad + blanks(res.length()-4) ;

		res.append( getOutputs()[0].layout(subPad+"|   ") );
		res.append("\n");
		
		res.append(subPad).append("|\n");
		
		res.append(subPad + "+-> ");
		res.append( getOutputs()[1].layout( subPad + "    ") );
		return res;
	}

	@Override
	public void simulate(SimulationObserver observer, double flow, boolean check) {
		
		if(this.outputs[1]==null && this.outputs[0]==null) {
			observer.notifyFlow("Split", this.getName(), flow,0,0);
			
			return;
		}
		
		double outflow=0;
		
		if(this.outputs[1]!=null && outputs[0]!=null) outflow=flow/2;
		
		observer.notifyFlow("Split", this.getName(), flow, outflow,outflow);
		if(check==true && flow>maxFlow) ((SimulationObserverExt)observer).notifyFlowError("Split", name,flow, maxFlow);
		
		if(this.outputs[1]!=null) {
			
			this.outputs[1].simulate(observer,outflow,check);
		}
		if(this.outputs[0]!=null) {
			
			this.outputs[0].simulate(observer, outflow,check);
		}
		
		
	}
	
	
	
	
}
