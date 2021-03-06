package mountainhuts;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a municipality
 *
 */
public class Municipality {
	
	private Provincia provincia;
	private String name;
	private String nomeProvincia;
	private Integer altitudine;
	private Map<String,MountainHut> elencoRifugi=new HashMap<>();
	/**
	 * Name of the municipality.
	 * 
	 * Within a region the name of a municipality is unique
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	public Municipality(String name, String provincia, Integer altitudine) {
		
		this.name = name;
		this.nomeProvincia = provincia;
		this.altitudine = altitudine;
	}

	/**
	 * Province of the municipality
	 * 
	 * @return province
	 */
	public String getProvince() {
		return nomeProvincia;
	}

	/**
	 * Altitude of the municipality
	 * 
	 * @return altitude
	 */
	public Integer getAltitude() {
		return altitudine;
	}
	
	public void addMountainHut(MountainHut r, String nome) {
		elencoRifugi.put(nome, r);
		
	}

	public Map<String, MountainHut> getElencoRifugi() {
		return elencoRifugi;
	}

	public void setElencoRifugi(Map<String, MountainHut> elencoRifugi) {
		this.elencoRifugi = elencoRifugi;
	}
	

}
