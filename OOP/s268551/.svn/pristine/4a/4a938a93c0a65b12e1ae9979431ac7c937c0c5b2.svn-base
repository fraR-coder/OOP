package university;

 public class Student {
	private int id;
	private String name,surname;
	private Course listc[]= new Course[25];
	private int count ;
	private boolean mark=false;
	private float avg;
	private int voti[]= new int[100];
	private int num;
	private float punteggio;
	private boolean selected=false;
	
	
	
	
	public Student(int id, String name, String surname) {
		
		this.id = id;
		this.name = name;
		this.surname = surname;
		
	}

	
	
	public boolean isMark() {
		return mark;
	}



	public boolean isSelected() {
		return selected;
	}



	public void setSelected(boolean selected) {
		this.selected = selected;
	}



	public void setMark(boolean mark) {
		this.mark = mark;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String toString() {
		String tag=id +" "+ name+" "+surname;
		return tag;
	}
	
	public String nomeCompleto() {
		String tag=name+" "+surname;
		return tag;
	}

	public void addCourse(Course c) {
		if(count>25) {
			System.err.println("too many courses for this student");
			System.exit(4);
		}
		listc[count++]=c;
		
		
	}

	public Course[] getListc() {
		return listc;
	}

	
	public void setAvg() {
		float sum=0;
		for(int i=0;i<num;i++) {
			sum+=voti[i];
		}
		this.avg=sum/(num);
	}
	
	public float getAvg() {
		
		return this.avg;
	}



	public int getNumEx() {
		return num;
	}



	public void addVote(int vote,Course c) {
		this.mark=true;
		voti[num++]=vote;
		this.setAvg();
		c.setVoto(vote);
	}
	
	public float getPunteggio() {
		return punteggio;
	}



	public int getNcourses() {
		return this.count;
	}
	
public void calcola() {
		
		int nC=this.getNcourses();
		int nE=this.getNumEx();
		
		this.punteggio=10*(nE/nC)+this.avg;
		
		
	}


}
