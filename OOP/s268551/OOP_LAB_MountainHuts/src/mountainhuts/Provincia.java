package mountainhuts;

import java.util.HashMap;
import java.util.Map;

public class Provincia {
	private String name;
	private Region regione;
	private Map<String,Municipality> elencoComuni=new HashMap<>();
	
	public Provincia(String name,Region regione) {
		
		this.name = name;
		this.regione=regione;
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addMunicipality(Municipality c, String nome) {
		elencoComuni.put(nome, c);
		
	}
}
