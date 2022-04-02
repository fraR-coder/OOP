package World;

public class Arma {
	private String nome;

	public Arma(String nome) {
		
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return " " + nome + "]";
	}
	
	
	

}
