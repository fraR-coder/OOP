package it.polito.oop.paintings;

public class Painter {
	private String name;
	private int  birth,death;
	
	public Painter(String name, int birth, int death) {
		
		this.name = name;
		this.birth = birth;
		this.death = death;
	}

	 String getName() {
		return name;
	}



	 int getBirth() {
		return birth;
	}

	

	 int getDeath() {
		return death;
	}


	
	public String toString() {
		String tag=name+ " " + birth +"-";
		
		if(death>birth) {
			tag=tag+death;
		
		}
		return tag;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

