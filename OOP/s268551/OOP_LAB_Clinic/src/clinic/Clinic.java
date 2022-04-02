package clinic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a clinic with patients and doctors.
 * 
 */
public class Clinic {
	
	Map<String,Patient> elencoPazienti=new HashMap<>();
	Map<Integer,Doctor> elencoDottori=new HashMap<>();

	
	/**
	 * Add a new clinic patient.
	 * 
	 * @param first first name of the patient
	 * @param last last name of the patient
	 * @param ssn SSN number of the patient
	 */
	public void addPatient(String first, String last, String ssn) {
		// TODO Complete method
		elencoPazienti.put(ssn, new Patient(first, last, ssn));
		
	}


	/**
	 * Retrieves a patient information
	 * 
	 * @param ssn SSN of the patient
	 * @return the object representing the patient
	 * @throws NoSuchPatient in case of no patient with matching SSN
	 */
	public String getPatient(String ssn) throws NoSuchPatient {
		// TODO Complete method
		Patient p=elencoPazienti.get(ssn);
		if(p!= null) {
			return p.toString();
		}
		
		throw new NoSuchPatient();
	}

	/**
	 * Add a new doctor working at the clinic
	 * 
	 * @param first first name of the doctor
	 * @param last last name of the doctor
	 * @param ssn SSN number of the doctor
	 * @param docID unique ID of the doctor
	 * @param specialization doctor's specialization
	 */
	public void addDoctor(String first, String last, String ssn, int docID, String specialization) {
		// TODO Complete method
		elencoDottori.put(docID, new Doctor(first, last, ssn, docID, specialization));
		
		
	}

	/**
	 * Retrieves information about a doctor
	 * 
	 * @param docID ID of the doctor
	 * @return object with information about the doctor
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public String getDoctor(int docID) throws NoSuchDoctor {
		Doctor p=elencoDottori.get(docID);
		if(p!= null) {
			return p.toString();
		}
		
		throw new NoSuchDoctor();
	}
	
	/**
	 * Assign a given doctor to a patient
	 * 
	 * @param ssn SSN of the patient
	 * @param docID ID of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor exists with a matching ID
	 */
	public void assignPatientToDoctor(String ssn, int docID) throws NoSuchPatient, NoSuchDoctor {
		// TODO Complete method
		Doctor d=elencoDottori.get(docID);
		Patient p=elencoPazienti.get(ssn);
		if(p==null) throw new NoSuchPatient();
		if(d==null) throw new NoSuchDoctor();
		p.setDoctor(d);
		d.assegnaPaziente(p);


	}
	
	/**
	 * Retrieves the id of the doctor assigned to a given patient.
	 * 
	 * @param ssn SSN of the patient
	 * @return id of the doctor
	 * @throws NoSuchPatient in case of not patient with matching SSN
	 * @throws NoSuchDoctor in case no doctor has been assigned to the patient
	 */
	public int getAssignedDoctor(String ssn) throws NoSuchPatient, NoSuchDoctor {
		Patient p=elencoPazienti.get(ssn);
		if(p==null) throw new NoSuchPatient();
		Doctor d=p.getDoctor();
		if(d==null) throw new NoSuchDoctor();
		
		return d.getDocID();
	}
	
	/**
	 * Retrieves the patients assigned to a doctor
	 * 
	 * @param id ID of the doctor
	 * @return collection of patient SSNs
	 * @throws NoSuchDoctor in case the {@code id} does not match any doctor 
	 */
	public Collection<String> getAssignedPatients(int id) throws NoSuchDoctor {
		Doctor d=elencoDottori.get(id);
		if(d==null) throw new NoSuchDoctor();
		
		Collection<String> l = new ArrayList<String>();
		for(Patient p:d.getPazientiAssegnati()) {
			String s=p.getSsn();
			l.add(s);
		}
		
		//List<String> a=d.getPazientiAssegnati().stream().map(p->p.getSsn()).collect(Collectors.toList());
		
		return l;
	}


	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and specialization.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method should be able
	 * to ignore the row and skip to the next one.<br>

	 * 
	 * @param readed linked to the file to be read
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader) throws IOException {
		int i=0;
		
		try {
		BufferedReader breader = new BufferedReader(reader);
		String line="";
		while(line!=null) {
		     line = breader.readLine();
		     if(line==null) break;
		    
		   String[] parts= line.split(";");
		   
		   
		   switch(parts[0].trim()) {
		   	case "P":
		   		if(parts.length==4) {
		   		elencoPazienti.put(parts[3].trim(),new Patient(parts[1].trim(), parts[2].trim(),parts[3].trim()));
		   		i++;
		   		}
		   		break;
			case "M":
				if(parts.length==6) {
				elencoDottori.put(Integer.parseInt(parts[1]), new Doctor(parts[2].trim(),parts[3].trim(),parts[4].trim(),Integer.parseInt(parts[1]),parts[5].trim()));
				i++;
				}
				break;
				
				
			default:break;
		   }
		   
		   
		   
		    
		}
		}
		 catch (IOException e) {
			 System.out.println(e.getMessage());
		}
		
		
		return i;		
	}



	/**
	 * Loads data about doctors and patients from the given stream.
	 * <p>
	 * The text file is organized by rows, each row contains info about
	 * either a patient or a doctor.</p>
	 * <p>
	 * Rows containing a patient's info begin with letter {@code "P"} followed by first name,
	 * last name, and SSN. Rows containing doctor's info start with letter {@code "M"},
	 * followed by badge ID, first name, last name, SSN, and speciality.<br>
	 * The elements on a line are separated by the {@code ';'} character possibly
	 * surrounded by spaces that should be ignored.</p>
	 * <p>
	 * In case of error in the data present on a given row, the method calls the
	 * {@link ErrorListener#offending} method passing the line itself,
	 * ignores the row, and skip to the next one.<br>

	 * 
	 * @param reader reader linked to the file to be read
	 * @param listener listener used for wrong line notifications
	 * @throws IOException in case of IO error
	 */
	public int loadData(Reader reader, ErrorListener listener) throws IOException {
		
	int i=0;
		
		try {
		BufferedReader breader = new BufferedReader(reader);
		String line="";
		while(line!=null) {
		     line = breader.readLine();
		     if(line==null) break;
		     

		   String[] parts= line.split(";");
		   
		   
		   switch(parts[0].trim()) {
		   	case "P":
		   		if(parts.length!=4) { listener.offending(line);break; }
		   		elencoPazienti.put(parts[3].trim(),new Patient(parts[1].trim(), parts[2].trim(),parts[3].trim()));
		   		i++;
		   		
		   		break;
			case "M":
				if(parts.length!=6)  { listener.offending(line);break; } 
				elencoDottori.put(Integer.parseInt(parts[1]), new Doctor(parts[2].trim(),parts[3].trim(),parts[4].trim(),Integer.parseInt(parts[1]),parts[5].trim()));
				i++;
				
				break;
				
				
			default:{ listener.offending(line);break; } 
		   }
		   
		   
		   
		    
		}
		}
		 catch (IOException e) {
			 System.out.println(e.getMessage());
		}
		
		
		return i;		
	}

		
	/**
	 * Retrieves the collection of doctors that have no patient at all.
	 * The doctors are returned sorted in alphabetical order
	 * 
	 * @return the collection of doctors' ids
	 */
	public Collection<Integer> idleDoctors(){

		
		
		List<Integer> a=elencoDottori.values().stream().filter(d->d.getPazientiAssegnati().isEmpty())
				.sorted(Comparator.comparing(Doctor::getLast)
				.thenComparing(Doctor::getFirst)).map(d->d.docID).collect(Collectors.toList());
					
		
		
		
		
		
		return a;
	}

	/**
	 * Retrieves the collection of doctors having a number of patients larger than the average.
	 * 
	 * @return  the collection of doctors' ids
	 */
	public Collection<Integer> busyDoctors(){
		
		int sum=0;
		for(Doctor d: elencoDottori.values()) {
			sum+=d.getPazientiAssegnati().size();
		}
		float media=sum/(elencoDottori.keySet().size());
		
		List<Integer> l=new ArrayList<>();
		for(Doctor d: elencoDottori.values()) {
			if(d.getPazientiAssegnati().size()>media) l.add(d.docID);
		}

		
		return l;
	}

	/**
	 * Retrieves the information about doctors and relative number of assigned patients.
	 * <p>
	 * The method returns list of strings formatted as "{@code ### : ID SURNAME NAME}" where {@code ###}
	 * represent the number of patients (printed on three characters).
	 * <p>
	 * The list is sorted by decreasing number of patients.
	 * 
	 * @return the collection of strings with information about doctors and patients count
	 */
	public Collection<String> doctorsByNumPatients(){
		
		List<Doctor> l=new ArrayList<>(elencoDottori.values());
		
		l.sort(Comparator.comparing(Doctor::getNumPatients).reversed());
		
		List<String> s=new ArrayList<>();
		
		for(Doctor d:l) {
			String tmp=String.format("%3d",  d.getNumPatients()) +" : "+d.getDocID()+" "+
								d.getLast()+" "+d.getFirst();
			s.add(tmp);
		}
		

		
		
		
		return s;
	}
	
	/**
	 * Retrieves the number of patients per (their doctor's)  speciality
	 * <p>
	 * The information is a collections of strings structured as {@code ### - SPECIALITY}
	 * where {@code SPECIALITY} is the name of the speciality and 
	 * {@code ###} is the number of patients cured by doctors with such speciality (printed on three characters).
	 * <p>
	 * The elements are sorted first by decreasing count and then by alphabetic speciality.
	 * 
	 * @return the collection of strings with speciality and patient count information.
	 */
	
	
private List<Tag> tags=new ArrayList<>();	
	private static class Tag{
		 String spec;
		int num;
		
		public Tag(String spec, int num) {
			
			this.spec = spec;
			this.num = num;
		}

		public String getSpec() {
			return spec;
		}

		public void setSpec(String spec) {
			this.spec = spec;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}
		
		
		
	}
	
	public Collection<String> countPatientsPerSpecialization(){
		int flag=0;
		for(Doctor d: elencoDottori.values()) {
			flag=0;
			for(Tag t:tags) {
				if(t.spec.equals(d.getSpecialization())) {
					t.num+=d.getNumPatients();
					flag=1;
				}
			}
			if(flag==0)
			tags.add(new Tag(d.getSpecialization(),d.getNumPatients()));
		}
		
		tags.sort(Comparator.comparing(Tag::getNum).reversed().thenComparing(Comparator.comparing(Tag::getSpec)));
		
	List<String> l=new ArrayList<>();
	for(Tag t:tags) {
		l.add(String.format("%3d", t.num)+" - "+t.spec);
	}

	
	
	

									
	
			return l;
	}
	
}
