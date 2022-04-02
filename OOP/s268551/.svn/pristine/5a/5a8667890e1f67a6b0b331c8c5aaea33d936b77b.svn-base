package clinic;

import java.util.ArrayList;
import java.util.List;

public class Doctor {
	String first;
	String last;
	String ssn;
	int docID;
	String specialization;
	List<Patient> pazientiAssegnati=new ArrayList<>();
	
	public Doctor(String first, String last, String ssn, int docID, String specialization) {
		this.first = first;
		this.last = last;
		this.ssn = ssn;
		this.docID = docID;
		this.specialization = specialization;
	}
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public int getDocID() {
		return docID;
	}
	public void setDocID(int docID) {
		this.docID = docID;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	@Override
	public String toString() {
		return last+" "+first+" ("+ssn+") ["+docID+"]: "+specialization;
	}
	public void assegnaPaziente(Patient p) {
		pazientiAssegnati.add(p);
	}
	public List<Patient> getPazientiAssegnati() {
		return pazientiAssegnati;
	}
	public void setPazientiAssegnati(List<Patient> pazientiAssegnati) {
		this.pazientiAssegnati = pazientiAssegnati;
	}
	public int getNumPatients() {
		return this.getPazientiAssegnati().size();	}
	
}
