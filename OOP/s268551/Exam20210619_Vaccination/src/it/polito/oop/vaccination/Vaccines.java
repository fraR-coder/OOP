package it.polito.oop.vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class Vaccines {

    public final static int CURRENT_YEAR = java.time.LocalDate.now().getYear();
    List<Map<String, List<String>>> allocateInWeek=new ArrayList();
    List<CentroVaccinale> centri=new ArrayList<CentroVaccinale>();
    Map<String,Persona> popolazione =new HashMap<String, Persona>();
    List<IntervallAge> intervalli =new ArrayList<>();
    List<Giorno> settimana =new ArrayList<>();
    List<CapacitaVaccinazione> elencoCapacita =new ArrayList<CapacitaVaccinazione>();
    List<String> personeAllocate=new ArrayList<>();
	private BiConsumer<Integer, String> listener;


    // R1
    /**
     * Add a new person to the vaccination system.
     *
     * Persons are uniquely identified by SSN (italian "codice fiscale")
     *
     * @param first first name
     * @param last last name
     * @param ssn italian "codice fiscale"
     * @param year birth year
     * @return {@code false} if ssn is duplicate,
     */
    public boolean addPerson(String first, String last, String ssn, int year) {
    	Persona p=new Persona(ssn, first, last, year);
    	if(popolazione.containsKey(ssn)) return false;
    	popolazione.put(ssn, p);
        return true;
    }

    /**
     * Count the number of people added to the system
     *
     * @return person count
     */
    public int countPeople() {
        return popolazione.keySet().size();
    }

    /**
     * Retrieves information about a person.
     * Information is formatted as ssn, last name, and first name
     * separate by {@code ','} (comma).
     *
     * @param ssn "codice fiscale" of person searched
     * @return info about the person
     */
    public String getPerson(String ssn) {
    	
    	Persona p=popolazione.get(ssn);
    	
        return ssn+","+p.cognome+","+p.nome;
    }

    /**
     * Retrieves of a person given their SSN (codice fiscale).
     *
     * @param ssn "codice fiscale" of person searched
     * @return age of person (in years)
     */
    public int getAge(String ssn) {
    	Persona p=popolazione.get(ssn);
        return CURRENT_YEAR-p.getYear();
    }

    /**
     * Define the age intervals by providing the breaks between intervals.
     * The first interval always start at 0 (non included in the breaks)
     * and the last interval goes until infinity (not included in the breaks).
     * All intervals are closed on the lower boundary and open at the upper one.
     * <p>
     * For instance {@code setAgeIntervals(40,50,60)}
     * defines four intervals {@code "[0,40)", "[40,50)", "[50,60)", "[60,+)"}.
     *
     * @param brks the array of breaks
     */
    public void setAgeIntervals(int... brks) {
    	IntervallAge interval=new IntervallAge(0, brks[0]);
    	intervalli.add(interval);
    	for(int i=0;i<brks.length-1;i++) {
    		intervalli.add(new IntervallAge(brks[i], brks[i+1]));	
    	}
    	intervalli.add(new IntervallAge(brks[brks.length-1],Integer.MAX_VALUE));
    	
    }

    /**
     * Retrieves the labels of the age intervals defined.
     *
     * Interval labels are formatted as {@code "[0,10)"},
     * if the upper limit is infinity {@code '+'} is used
     * instead of the number.
     *
     * @return labels of the age intervals
     */
    public Collection<String> getAgeIntervals() {
    	
    	Collection<String> c=new ArrayList<String>();
    	c=intervalli.stream().map(e->e.toString()).collect(Collectors.toList());
    	
        return c;
    }

    /**
     * Retrieves people in the given interval.
     *
     * The age of the person is computed by subtracting
     * the birth year from current year.
     *
     * @param range age interval label
     * @return collection of SSN of person in the age interval
     */
    public Collection<String> getInInterval(String range) {
    	for(Persona p:popolazione.values()) {
    		p.setEta(CURRENT_YEAR-p.year);
    	}
    	IntervallAge in=new IntervallAge(range);
    	Collection<String> c=new HashSet<>();
    	for(Persona p:popolazione.values()) {
    		if(p.eta>=in.getMinValue() && p.eta<in.getMaxValue()) {
    			if(!c.contains(p.codF)) c.add(p.codF);
    		}
    	}
    	
        return c;
    }

    // R2
    /**
     * Define a vaccination hub
     *
     * @param name name of the hub
     * @throws VaccineException in case of duplicate name
     */
    public void defineHub(String name) throws VaccineException {
    	CentroVaccinale c=new CentroVaccinale(name);
    	if(centri.contains(c)) throw new VaccineException();
    	else centri.add(c);
    	
    }

    /**
     * Retrieves hub names
     *
     * @return hub names
     */
    public Collection<String> getHubs() {
        return centri.stream().map(e->e.toString()).collect(Collectors.toList());
    }

    /**
     * Define the staffing of a hub in terms of
     * doctors, nurses and other personnel.
     *
     * @param name name of the hub
     * @param doctors number of doctors
     * @param nNurses number of nurses
     * @param other number of other personnel
     * @throws VaccineException in case of undefined hub, or any number of personnel not greater than 0.
     */
    public void setStaff(String name, int doctors, int nNurses, int other) throws VaccineException {
    	CentroVaccinale centro=null;
    	for(CentroVaccinale c:centri) {
    		if(c.name.equals(name)) {
    			centro=c;
    			break;
    		}
    	}
    	if(centro==null) throw new VaccineException();
    	if(doctors<=0 || nNurses<=0 || other <=0) throw new VaccineException();
    	
    	centro.setPersonale(new Personale(doctors, nNurses, other));
    	estimateHourlyCapacity(name);
    	
    	
    }

    /**
     * Estimates the hourly vaccination capacity of a hub
     *
     * The capacity is computed as the minimum among
     * 10*number_doctor, 12*number_nurses, 20*number_other
     *
     * @param hubName name of the hub
     * @return hourly vaccination capacity
     * @throws VaccineException in case of undefined or hub without staff
     */
    public int estimateHourlyCapacity(String hubName) throws VaccineException {
    	
    	CentroVaccinale centro=null;
    	for(CentroVaccinale c:centri) {
    		if(c.name.equals(hubName)) {
    			centro=c;
    			break;
    		}
    	}
    	if(centro==null) throw new VaccineException();
    	if(centro.getPersonale()==null) throw new VaccineException();
    	
    	int capacita=centro.getPersonale().calcolaCapacita();
    
		centro.setCapacita(capacita);
        return capacita;
    }

    // R3
    /**
     * Load people information stored in CSV format.
     *
     * The header must start with {@code "SSN,LAST,FIRST"}.
     * All lines must have at least three elements.
     *
     * In case of error in a person line the line is skipped.
     *
     * @param people {@code Reader} for the CSV content
     * @return number of correctly added people
     * @throws IOException in case of IO error
     * @throws VaccineException in case of error in the header
     */
    public long loadPeople(Reader people) throws IOException, VaccineException {
        // Hint:
        BufferedReader br = new BufferedReader(people);
        String line=br.readLine();
        String[] parts= line.split(",");
        int i=0;
        int j=1;
        
        if(parts.length!=4) {
        	if(listener!=null) listener.accept(1, line);
        	throw new VaccineException();
        }
       
        try {
		while(line!=null) {
			j++;
		     line = br.readLine();
		     if(line==null) break;
		    
		    parts= line.split(",");
		    if(parts.length!=4) {
		    	if(listener!=null)listener.accept(j, line);
		    	continue;
		    }
		    Persona p=new Persona(parts[0],parts[2],parts[1],Integer.parseInt(parts[3]));
		    
		    if(popolazione.containsKey(parts[0])) {
		    	if(listener!=null)listener.accept(j, line);
		    	continue;
		    }
		    
		    popolazione.put(parts[0], p);
		    i++;
		    
		}
        }
		catch(IOException e) {
			System.err.println(e.getMessage());
			return -1;
		}
		
        br.close();
        return i;
    }

    // R4
    /**
     * Define the amount of working hours for the days of the week.
     *
     * Exactly 7 elements are expected, where the first one correspond to Monday.
     *
     * @param hs workings hours for the 7 days.
     * @throws VaccineException if there are not exactly 7 elements or if the sum of all hours is less than 0 ore greater than 24*7.
     */
    public void setHours(int... hs) throws VaccineException {
    	if(hs.length!=7) throw new VaccineException();
    	int j=0;
    	for(int i:hs) {
    		if(i>12 || i<=0) throw new VaccineException();
    		Giorno g=new Giorno(i,j);
    		settimana.add(g);
    		j++;
    	}
    	
    }

    /**
     * Returns the list of standard time slots for all the days of the week.
     *
     * Time slots start at 9:00 and occur every 15 minutes (4 per hour) and
     * they cover the number of working hours defined through method {@link #setHours}.
     * <p>
     * Times are formatted as {@code "09:00"} with both minuts and hours on two
     * digits filled with leading 0.
     * <p>
     * Returns a list with 7 elements, each with the time slots of the corresponding day of the week.
     *
     * @return the list hours for each day of the week
     */
    public List<List<String>> getHours() {
    	
    	List<List<String>> lBig=new ArrayList<>();
    	for(int i=0;i<7;i++) {
    		List<String> l=new ArrayList<>();
    		int total=settimana.get(i).ore+9;
    		String s="09:00";
    		l.add(s);
    		int k=15;
    		int h=9;
    		
    		while( h<total) {
    			
    			 
    			 
    			 l.add(String.format("%02d:%02d", h,k));
    			 k+=15;
    			 if(k>=60) {
     				h++; k=0;
     			}	 
    		}
    		
    		lBig.add(l);
    		
    	}
    	
    	
        return lBig;
    }

    /**
     * Compute the available vaccination slots for a given hub on a given day of the week
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the hourly capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public int getDailyAvailable(String hubName, int day) {
    	
    	CentroVaccinale centro=null;
    	for(CentroVaccinale c:centri) {
    		if(c.name.equals(hubName)) {
    			centro=c;
    			break;
    		}
    	}
    	
    	Giorno g=settimana.get(day);
    	elencoCapacita.add(new CapacitaVaccinazione(centro, g,centro.capacita*g.ore));
    	
    	
        return centro.capacita*g.ore;
    }

    /**
     * Compute the available vaccination slots for each hub and for each day of the week
     * <p>
     * The method returns a map that associates the hub names (keys) to the lists
     * of number of available hours for the 7 days.
     * <p>
     * The availability is computed as the number of working hours of that day
     * multiplied by the capacity (see {@link #estimateCapacity} of the hub.
     *
     * @return
     */
    public Map<String, List<Integer>> getAvailable() {
    	Map<String, List<Integer>> m=new HashMap<String, List<Integer>>();
    	for(CentroVaccinale c: centri) {
    		String n=c.getName();
    		List<Integer> l=new ArrayList<>();
    		
    		for(Giorno g:settimana) {
    			l.add(getDailyAvailable(n,g.giorno));
    			
    		}
    			
    			m.put(n, l);
    		
    	}
    	
    	
        return m;
    }

    /**
     * Computes the general allocation plan a hub on a given day.
     * Starting with the oldest age intervals 40%
     * of available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is the list of SSNs (codice fiscale) of the
     * persons allocated to that day
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     *
     * @param hubName name of the hub
     * @param day day of week index (0 = Monday)
     * @return the list of daily allocations
     */
    public List<String> allocate(String hubName, int day) {
    	CentroVaccinale centro=null;
    	for(CentroVaccinale c:centri) {
    		if(c.name.equals(hubName)) {
    			centro=c;
    			break;
    		}
    	}
    	Giorno g=settimana.get(day);
    	int num=getDailyAvailable(hubName, day);

    	List<String> persone=new ArrayList<>();
    	for(int i=intervalli.size()-1;i>=0;i--) {
    		IntervallAge interval=intervalli.get(i);
    		int k=(int) (num*0.4);int j=0;
    		for(String p: getInInterval(interval.toString())) {
    			if(j==k) break;
    			if(!popolazione.get(p).isAllocato()) {
    				popolazione.get(p).setAllocato(true);
    				personeAllocate.add(p);persone.add(p);
    				j++;
    			}
    		}
    		num=num-j;
    	}
    	if(num!=0) {
    		int j=0;
    		for(int i=intervalli.size()-1;i>=0;i--) {
        		IntervallAge interval=intervalli.get(i);
        		for(String p: getInInterval(interval.toString())) {
        			if(j>=num) break;
        			if(!popolazione.get(p).isAllocato()) {
        				popolazione.get(p).setAllocato(true);personeAllocate.add(p);
        				j++;persone.add(p);
        			}
        		}  		
        		if(j>=num) break;
        		}
    	}
        return persone;
    }

    /**
     * Removes all people from allocation lists and
     * clears their allocation status
     */
    public void clearAllocation() {
    	popolazione.values().stream().forEach(e->e.setAllocato(false));}

    /**
     * Computes the general allocation plan for the week.
     * For every day, starting with the oldest age intervals
     * 40% available places are allocated
     * to persons in that interval before moving the the next
     * interval and considering the remaining places.
     * <p>
     * The returned value is a list with 7 elements, one
     * for every day of the week, each element is a map that
     * links the name of each hub to the list of SSNs (codice fiscale)
     * of the persons allocated to that day in that hub
     * <p>
     * <b>N.B.</b> no particular order of allocation is guaranteed
     * but the same invocation (after {@link #clearAllocation}) must return the same
     * allocation.
     *
     * @return the list of daily allocations
     */
    public List<Map<String, List<String>>> weekAllocate() {
    	for(int i=0;i<settimana.size();i++) {
    		Map<String,List<String>> m=new HashMap<>();
    		for(CentroVaccinale c:centri) {
    			m.put(c.name,allocate(c.name, i));
    		}
    		allocateInWeek.add(i,m);
    	}
        return allocateInWeek;
    }

    // R5
    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system
     *
     * @return proportion of allocated people
     */
    public double propAllocated() {
           return (popolazione.values().stream().filter(p->p.isAllocato()).count())/ (double)popolazione.size();
    }

    /**
     * Returns the proportion of allocated people
     * w.r.t. the total number of persons added
     * in the system, divided by age interval.
     * <p>
     * The map associates the age interval label
     * to the proportion of allocates people in that interval
     *
     * @return proportion of allocated people by age interval
     */
    public Map<String, Double> propAllocatedAge() {
    	Map<String, Double> m=new HashMap<>();
    	for(IntervallAge i : intervalli) {
    		m.put(i.toString(),((double)getInInterval(i.toString()).stream().map(e->popolazione.get(e)).filter(p->p.isAllocato()).count()/getInInterval(i.toString()).size()));
    	}
        return m;
    }

    /**
     * Retrieves the distribution of allocated persons
     * among the different age intervals.
     * <p>
     * For each age intervals the map reports the
     * proportion of allocated persons in the corresponding
     * interval w.r.t the total number of allocated persons
     *
     * @return
     */
    public Map<String, Double> distributionAllocated() {
    	Map<String, Double> m=new HashMap<>();
    	for(IntervallAge i : intervalli) {
    		m.put(i.toString(),(getInInterval(i.toString()).stream().map(e->popolazione.get(e)).filter(p->p.isAllocato()).count()/(double)personeAllocate.size()));
    	}
        return m;
    }

    // R6
    /**
     * Defines a listener for the file loading method.
     * The {@ accept()} method of the listener is called
     * passing the line number and the offending line.
     * <p>
     * Lines start at 1 with the header line.
     *
     * @param listener the listener for load errors
     */
    public void setLoadListener(BiConsumer<Integer, String> listener) {
    	this.listener=listener;
    }
}
