package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends ElementExt {
	
	private boolean open;
	
	public Tap(String name) {
		super(name);
		
		//TODO: complete
	}
	
	/**
	 * Defines whether the tap is open or closed.
	 * 
	 * @param open  opening level
	 */
	public void setOpen(boolean open){
		//TODO: complete
		this.open=open;
		
		
	}
	
	
	
	public void simulate(SimulationObserver observer,double flow) {
		simulate(observer,flow,false);
		
	}
	
	@Override
	protected
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Tap -> ");
		res.append( getOutput().layout( pad + blanks(res.length()) ) );
		return res;
	}

	@Override
	public void simulate(SimulationObserver observer, double flow, boolean check) {
		double outflow;
		
		if(this.open) outflow=flow;
		else outflow=0;
		
		observer.notifyFlow("Tap", this.getName(), flow, outflow);
		if(check==true && flow>maxFlow) ((SimulationObserverExt)observer).notifyFlowError("Tap", name, flow, maxFlow);
		this.outputs[0].simulate(observer,outflow,check);
		
	}

}
