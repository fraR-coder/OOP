package mountainhuts;

import java.util.Optional;

/**
 * Represents a mountain hut.
 * 
 * It is linked to a {@link Municipality}
 *
 */
public class MountainHut {
	
	private String denominazione;
	private String categoria;
	private int postiLetto;
	private Municipality comune;
	private Integer altitudine=null;
	/**
	 * Unique name of the mountain hut
	 * @return name
	 */
	public String getName() {
		return denominazione;
	}

	public MountainHut(String denominazione, String categoria, int postiLetto, Municipality comune, int altitudine) {
		
		this.denominazione = denominazione;
		this.categoria = categoria;
		this.postiLetto = postiLetto;
		this.comune = comune;
		this.altitudine = altitudine;
	}
	
	

	public MountainHut(String denominazione, String categoria, int postiLetto, Municipality comune) {
		
		this.denominazione = denominazione;
		this.categoria = categoria;
		this.postiLetto = postiLetto;
		this.comune = comune;
	}

	/**
	 * Altitude of the mountain hut.
	 * May be absent, in this case an empty {@link java.util.Optional} is returned.
	 * 
	 * @return optional containing the altitude
	 */
	public Optional<Integer> getAltitude() {
	
		Optional<Integer> o=Optional.ofNullable(altitudine);
		
		return o;
	}

	/**
	 * Category of the hut
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return categoria;
	}

	/**
	 * Number of beds places available in the mountain hut
	 * @return number of beds
	 */
	public Integer getBedsNumber() {
		return postiLetto;
	}

	/**
	 * Municipality where the hut is located
	 *  
	 * @return municipality
	 */
	public Municipality getMunicipality() {
		return comune;
	}

}
