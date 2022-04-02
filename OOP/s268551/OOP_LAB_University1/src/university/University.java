package university;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	private String name;
	private String rname, rsurname;
	 Student[] list= new Student[1000] ;
	private int id=10000;
	private int count,i,cod=10;
	 Course[] listC=new Course[50];
	

	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		this.name=name;
		
	}
	
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		
		return name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first
	 * @param last
	 */
	public void setRector(String first, String last){
		this.rname=first;
		rsurname=last;
		
	}
	
	/**
	 * Retrieves the rector of the university
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		String cname=rname+" "+ rsurname;
		return cname;
	}
	
	/**
	 * Enrol a student in the university
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		
		if(count>1000) {
			System.err.println("max number student reached");
			System.exit(1);
		}
		
		Student s=new Student(id++,first,last);
		list[count++]=s;
		
		
		
		
		return id-1;
	}
	
	/**
	 * Retrieves the information for a given student
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		
		for(Student x: list) {
			if(x.getId()==id) {
				return x.toString();
			}
		}
		System.err.println("id not found");
		System.exit(2);
		return null;
	}
	
	/**
	 * Activates a new course with the given teacher
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		if(i>50) {
			System.err.println("too many courses");
			System.exit(3);
		}
		listC[i]=new Course(teacher,title);
		listC[i].setCod(cod++);
		i++;
		
		return cod-1;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		for(Course x:listC) {
			if(x.getCod()==code) {
				return x.toString();
				
			}
		}
		return null;
	}
	
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
	public void register(int studentID, int courseCode){
	Student x;
	
		for(Student a:list) {
			if(a.getId()==studentID) {
				
				for(Course b:listC) {
					if(b.getCod()==courseCode) {
						a.addCourse(b);
						b.addStudent(a);
						break;
					}
					
				}
				break;
		
			}
			
		}
	}
	
	/**
	 * Retrieve a list of attendees
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		String t="";
		for(Course x:listC) {
			if(x.getCod()==courseCode) {
					Student[] studenti=x.getList();
					for(Student s:studenti) {
						if(s==null) break;
						t=t+s.toString()+"\n";
						
						
						
					}
				return t;
			}
		}
		return null;
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		String t="";
		for(Student x:list) {
			if(x.getId()==studentID) {
					Course[] corsi=x.getListc();
					for(Course c:corsi) {
						if(c==null) break;
						t=t+c.toString()+"\n";
						
						
						
					}
				return t;
			}
		}
		return null;
	}
	
}
