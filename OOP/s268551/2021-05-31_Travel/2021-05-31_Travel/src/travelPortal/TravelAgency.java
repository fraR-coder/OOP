package travelPortal;

import java.util.ArrayList;
import java.util.List;

class TravelAgency {
	String name;
	List<String> tipiAttivita;
	public TravelAgency(String name,String... activityTypes) {
		this.name = name;
		tipiAttivita=new ArrayList<String>();
		for(String s:activityTypes) {
			tipiAttivita.add(s);
		}
	}
	
	
}
