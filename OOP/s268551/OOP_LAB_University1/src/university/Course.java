package university;

public class Course {
	private int num=0;
	private String name, title;
	private int cod;
	private Student list[]=new Student[100];
	private int count;
	private int[] voti=new int[100];
	private float media;
	
	

	public void setVoto(int grade) {
		this.voti[num]=grade;
		float sum=0;
		int i=0;
		while(voti[i]!=0) {
			sum+=voti[i];
			i++;
		}
		
		num++;
		this.media=sum/num;
		
	}



	
	

	public float getMedia() {
		return media;
	}






	public Course(String name, String title) {
		
		this.name = name;
		this.title = title;
		
	}

	public String getName() {
		return name;
	}

	

	public String getTitle() {
		return title;
	}

	public int getCod() {
		return cod;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public String toString() {
		return cod+","+title+","+name;
	}
	
	public void addStudent(Student s) {
		if(count>100) {
			System.err.println("too many Student for this course");
			System.exit(5);
		}
		list[count++]=s;
		
		
		
	}
	

	
	
	public Student[] getList() {
		return list;
	}

	

	public String toString(Course c) {
		return c.cod+","+c.title+","+c.name;
	}
	

}
