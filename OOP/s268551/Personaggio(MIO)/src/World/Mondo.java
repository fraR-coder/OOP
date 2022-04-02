package World;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import World.Personaggio.Tipo;

public class Mondo{
	
	List<Personaggio> elencoPersonaggi=new ArrayList<>();
	
	
	
	public Personaggio addPersonaggio(String nome,Tipo tipo,String nomearma,int forza) {
		Personaggio p=new Personaggio(nome, tipo, new Arma(nomearma), forza);
		elencoPersonaggi.add(p);
		return p;

	}
	
	public Collection<Personaggio> ordinaElenco(){
		elencoPersonaggi.sort(new Comparator<Personaggio>() {

			@Override
			public int compare(Personaggio o1, Personaggio o2) {
				if(o1.nome.compareTo(o2.nome)>0)return 1;
				if(o1.nome.compareTo(o2.nome)<0)return -1;
				else {
					return o1.getArma().getNome().compareTo(o2.getArma().getNome());
				}
				
			}

			

			
			
		});
		
		return elencoPersonaggi;
		
	}
	
	public Collection<Personaggio> ordinaElencoLambda(){
		elencoPersonaggi.sort((o1,o2)->o1.getNome().compareTo(o2.getNome()));
		
		return elencoPersonaggi;
		
	}

	public Collection<Personaggio> ordinaElencoReversed(){
		elencoPersonaggi.sort(Comparator.comparing(Personaggio::getNome).reversed().thenComparing(Personaggio::getForza));
		
		return elencoPersonaggi;
		
	}
	@Override
	public String toString() {
		return "Mondo [elencoPersonaggi=" + elencoPersonaggi + "]";
	}

	
}
