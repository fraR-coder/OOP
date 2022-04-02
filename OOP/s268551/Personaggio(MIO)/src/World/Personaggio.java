package World;

public class Personaggio {
	
	public enum Tipo { 
		ACQUA,FUOCO,TERRA,ARIA
	}

	
	public String nome;
	private Arma arma;
	boolean vivo;
	private Tipo tipo ;
	int forza;
	

	
	
	public int getForza() {
		return forza;
	}




	public void setForza(int forza) {
		this.forza = forza;
	}




	public Personaggio(String nome,Tipo tipo,Arma arma,int forza) {
		
		this.nome = nome;
		this.vivo=true;
		this.tipo=tipo;
		this.arma=arma;
		this.forza=forza;
	
		
	}




	public Arma getArma() {
		return arma;
	}




	public void setArma(Arma arma) {
		this.arma = arma;
	}




	@Override
	public String toString() {
		return "nome=" + nome + arma +"\n";
	}




	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	
	
	
	

	



}
