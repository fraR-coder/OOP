package it.polito.oop.paintings;

public class Period {
	private String name;
	private int beginning,end;
	
	public Period(String name,int beginning,int end){
		this.name=name;
		this.beginning=beginning;
		this.end=end;
		
	}
	public String getName()
	{
		return this.name;
	}
	public int getBeginning() {
		return beginning;
	}
	public void setBeginning(int beginning) {
		this.beginning = beginning;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() { // per rappresentare oggetto come stringa
		if(beginning%100==0 && end==beginning+100) {
			return name+" " + (beginning/100+1)+ "th century";
		}
			else {
				return name+" " + beginning +"-" +end;
			}
	}
	
}
