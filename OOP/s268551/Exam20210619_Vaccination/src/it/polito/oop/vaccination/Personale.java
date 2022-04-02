package it.polito.oop.vaccination;

public class Personale {
	int doctors,  nNurses, others;

	public Personale(int doctors, int nNurses, int others) {
		
		this.doctors = doctors;
		this.nNurses = nNurses;
		this.others = others;
	}
	
	public int calcolaCapacita() {
		return Math.min(doctors*10, Math.min(nNurses*12, others*20));
	}

	public int getDoctors() {
		return doctors;
	}

	public void setDoctors(int doctors) {
		this.doctors = doctors;
	}

	public int getnNurses() {
		return nNurses;
	}

	public void setnNurses(int nNurses) {
		this.nNurses = nNurses;
	}

	public int getOthers() {
		return others;
	}

	public void setOthers(int others) {
		this.others = others;
	}
	
	
	
}
