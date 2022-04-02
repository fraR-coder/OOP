package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystemExt extends HSystem{
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		StringBuffer res = new StringBuffer();
		for(Element e : system){
			if( e != null && e instanceof Source){
				res.append(e.layout(""));
			}
		}
		return res.toString();
	}
	
	/**
	 * Deletes a previously added element with the given name from the system
	 */
	public void deleteElement(String name) {
		
		for(Element e:system) {
			if(e!=null && e.getName().equals(name)) {
				if(e instanceof Split) {
					if(((Split) e).getOutputs().length>1) {
						return;
					}
				}
				
				Element input=e.getInput();
				
				Element output=e.getOutput();
				
				if(input!=null) input.connect(output);
				
				else if(output!=null)
					output.setInput(input);
				
				system.remove(e);
				num_element--;
				return;
				
			}
		}
		
	}
	
	 

	/**
	 * starts the simulation of the system; if enableMaxFlowCheck is true,
	 * checks also the elements maximum flows against the input flow
	 */
	public void simulate(SimulationObserverExt observer, boolean enableMaxFlowCheck) {
		
		for(Element e:system) {
			if(e!=null && e instanceof Source) {
				e.simulate(observer,0,enableMaxFlowCheck);
			}
		}
	}
}
