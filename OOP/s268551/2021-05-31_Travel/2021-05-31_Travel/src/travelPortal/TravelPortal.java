package travelPortal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class TravelPortal {
	
	private class Pair<T>{
		T a,b;

		public Pair(T a, T b) {

			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString(){
			return a+":"+b;
		}
		
	};
	
	Map<String,Proposal> proposals=new HashMap<String, Proposal>();
	List<String> elencoTipiAttivita;
	Map<String,Set<String>> travelAgency=new HashMap<>();

//R1
	public List<String> addActivityTypes(String... names) {
		elencoTipiAttivita=new ArrayList<String>();
		for(String s:names) {
			elencoTipiAttivita.add(s);
		}
		elencoTipiAttivita.sort(null);
		
		return elencoTipiAttivita;
	}

	public int AddTravelAgency(String name, String... activityTypes) throws TPException {
		if(travelAgency.containsKey(name))
			throw new TPException();
		travelAgency.put(name, new HashSet<>());
		for(String s:activityTypes) {
			if(!elencoTipiAttivita.contains(s)) {
				throw new TPException();
			}
			travelAgency.get(name).add(s);

		}
		return travelAgency.get(name).size();
	}

	public SortedMap<String, List<String>> getAgenciesForActivityTypes() {
		
		/*TreeMap<String,List<String>> afa=new TreeMap<>();
		for(String s:travelAgency.keySet()) {
			for(String a:travelAgency.get(s)) {
				if(!afa.containsKey(a)) {
					afa.put(s,new ArrayList<>());
					afa.get(s).add(a);
				}

			}
		}
		for(String a:afa.keySet()) {
			Collections.sort(afa.get(a));
		}
		
		return afa;
		*/
		
		return travelAgency.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
		.flatMap(e->e.getValue().stream().map(a->new Pair<String>(a,e.getKey())))
		.collect(Collectors.groupingBy(p->p.a,TreeMap::new,Collectors.mapping(p->p.b,Collectors.toList())));
		
	}

//R2
	public int addProposal(String code, String agency, String destination, String period, int minNP, int maxNP,
			int price) throws TPException {
		if(proposals.containsKey(code))
			throw new TPException();
		if(!travelAgency.containsKey(agency))
			throw new TPException();
		String[] elements=destination.split(":");
		String[] days=elements[1].split("-");
		
		Proposal p=new Proposal(code, agency, destination, Integer.parseInt(elements[0]),Integer.parseInt( days[0]),Integer.parseInt( days[1]), minNP, maxNP, price);
		proposals.put(code, p);
		
		return p.getDayLast()-p.getDayFirst();
	}

	public int addActivity(String code, String activityType, String description, int price) throws TPException {
		if(!proposals.containsKey(code))
			throw new  TPException();
		
		String agency=proposals.get(code).getAgency() ;
		if(travelAgency.get(agency))
			
		
		return 0;
	}

	public int getProposalPrice(String code) throws TPException {
		return 0;
	}

//R3
public List<String> addParticipants (String code, String... names) throws TPException {
	return null;
}

	public int getIncome(String code) {
		return 0;
	}

//R4
	public String addRatings(String code, int... evaluations) throws TPException {
		return "";
	}

	public SortedMap<String, Integer> getTotalRatingsForParticipants() {
		return null;
	}

//R5
	public SortedMap<String, Integer> incomeForActivityTypes() {
		return null;
	}

	public SortedMap<Integer, List<String>> participantsWithSameNofProposals() {
		return null;
	}
}
