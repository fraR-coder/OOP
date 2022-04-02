package hydraulic;

import java.util.Arrays;

/**
 * Represents the generic abstract element of an hydraulics system.
 * It is the base class for all elements.
 *
 * Any element can be connect to a downstream element
 * using the method {@link #connect(Element) connect()}.
 */
public abstract class Element {
	
	//private final static int MAX_OUT=2;
	protected String name;
	
	protected Element[] outputs;
	protected double maxFlow;
	private Element input;
	
	


	/**
	 * Constructor
	 * @param name the name of the element
	 */
	
	public Element(String name){
		// TODO: to be implemented
		this.name=name;
		outputs = new Element[1];
		
	}
	
	public Element(String name, int numOutputs){
		this.name=name;
		outputs = new Element[numOutputs];
	}

	/**
	 * getter method
	 * @return the name of the element
	 */
	public String getName(){
		// TODO: to be implemented
		return this.name;
	}
	
	/**
	 * Connects this element to a given element.
	 * The given element will be connected downstream of this element
	 * @param elem the element that will be placed downstream
	 */
	public void connect(Element elem){
		// TODO: to be implemented
		this.outputs[0]=elem;
		if(elem!=null)elem.setInput(this);
		
		
		
	}
	
	/**
	 * Retrieves the element connected downstream of this
	 * @return downstream element
	 */
	public Element getOutput(){
		// TODO: to be implemented
		for(Element e:this.outputs) {
			if(e!=null) return e;
		}
		return null;
	}
	
	
	
	public abstract void simulate(SimulationObserver observer,double flow);
	
	public abstract void simulate(SimulationObserver observer,double flow,boolean check);

	protected abstract StringBuffer layout(String padding);
		
		
	protected static String blanks(int n) {
		char[] res = new char[n];
		Arrays.fill(res, ' ');
		return new String(res);
	}

	public Element getInput() {
		return input;
	}

	public void setInput(Element input) {
		this.input = input;
	}

	
	
	
}
