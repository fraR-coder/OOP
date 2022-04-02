package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * The status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends ElementExt {
	
	private double flow;

	public Source(String name) {
		super(name);
		
		//TODO: complete
		
	}

	/**
	 * defines the flow produced by the source
	 * 
	 * @param flow
	 */
	public void setFlow(double flow){
		//TODO: complete
		this.flow=flow;
		this.maxFlow=flow;
		
	}
	
	

	@Override
	public void simulate(SimulationObserver observer,double flow) {
		simulate(observer,flow,false);
		
		
		
	}
	
	
	@Override
	protected
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Source -> ");
		res.append( getOutput().layout(blanks(res.length())) );
		return res;
	}
	
	@Override
	public void setMaxFlow(double maxFlow) {
		//nothing
		
	}

	@Override
	public void simulate(SimulationObserver observer, double flow, boolean check) {
		
		
		observer.notifyFlow("Source", this.getName(),observer.NO_FLOW,this.flow);
		if(check==true && flow>maxFlow ) ((SimulationObserverExt)observer).notifyFlowError("Source",name, flow,maxFlow);
		getOutput().simulate(observer,this.flow,check);
		
	}
	
}
