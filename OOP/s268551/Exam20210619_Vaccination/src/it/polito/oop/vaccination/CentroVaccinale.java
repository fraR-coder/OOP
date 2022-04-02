package it.polito.oop.vaccination;

public class CentroVaccinale {
	
	String name;
	Personale personale=null;
	int capacita;
	public int capVaccinazione;

	public CentroVaccinale(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return  name ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Personale getPersonale() {
		return personale;
	}

	public void setPersonale(Personale personale) {
		this.personale = personale;
	}

	public int getCapacita() {
		return capacita;
	}

	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}
	@Override
	public boolean equals(Object obj) {
		CentroVaccinale other = (CentroVaccinale) obj;
		if(name.equals(other.name))
			return true;
		return false;
	}
	

}
