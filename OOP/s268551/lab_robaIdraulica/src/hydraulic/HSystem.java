package hydraulic;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	
	//private final static int MAXEL=20;
	protected List<Element> system=new ArrayList<Element>();
	protected int num_element;
	
	
	
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		// TODO: to be implemented
		system.add(elem);
		num_element++;
	}
	
	/**
	 * returns the element added so far to the system.
	 * If no element is present in the system an empty array (length==0) is returned.
	 * 
	 * @return an array of the elements added to the hydraulic system
	 */
	public Element[] getElements(){
		// TODO: to be implemented
		Element[] e=system.toArray(new Element[system.size()]);
		
		return e;
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(SimulationObserver observer){
		// TODO: to be implemented
		for(Element e:system) {
			if(e!=null && e instanceof Source) {
				e.simulate(observer,0);
			}
		}
	}

}
