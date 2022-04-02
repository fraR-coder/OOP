package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends ElementExt {
	
	
	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	@Override
	public void connect(Element elem) {
		//does nothing
	}

	
	public void simulate(SimulationObserver observer,double flow) {
		simulate(observer, flow, false);
		
		
	}
	
	@Override
	protected
	StringBuffer layout(String pad) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("]Sink *");
		return res;
	}

	@Override
	public void simulate(SimulationObserver observer, double flow, boolean check) {
		
		observer.notifyFlow("Sink", this.getName(), flow,SimulationObserver.NO_FLOW);
		if(check==true && flow>maxFlow) ((SimulationObserverExt)observer).notifyFlowError("Tap", name, flow, maxFlow);
		
		
	}
}
