public class Nemico {
	String tipo;
	int forza;
	boolean vivo;
	
	void aggiorna_nemico(String t, int f) {
		tipo=t;
		forza=f;
		vivo=true;
		
	}
	void stat(Nemico n) {
		System.out.println("Nemico:\nforza:"+n.forza);
	}
}
