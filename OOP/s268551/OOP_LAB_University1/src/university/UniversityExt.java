package university;

import java.util.logging.Logger;

/**
 * This class is an extended version of the {@Link University} class.
 * 
 *
 */
public class UniversityExt extends University {
	
	private final static Logger logger = Logger.getLogger("University");

	public UniversityExt(String name) {
		super(name);
		// Example of logging
		logger.info("Creating extended university object");
		
	
	}
	
	public int enroll(String first, String last){
		int id=super.enroll(first,last);
		logger.info("New student enrolled: "+id+" "+first+" "+last+"\n");
		return id;
	}
	
	public int activate(String title, String teacher){
		int cod=super.activate(title,teacher);
		logger.info("New course activated: "+cod+", "+title+" "+teacher+"\n");
		return cod;
	}
	
	public void register(int studentID, int courseCode){
		super.register(studentID, courseCode);
		logger.info("student: "+studentID+" signed up for course "+courseCode+"\n");
	}
	

	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		for(Student a:list) {
			if(a.getId()==studentId) {
				
				Course corsi[]=a.getListc();
				for(Course b:corsi) {
					if(b.getCod()==courseID) {
						a.addVote(grade,b);
						break;
					}
				}
				break;
			}
		}
		logger.info("Student "+studentId+" took an exam in course "+courseID+" with grade"+grade+"\n");
		
	}

	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		
		for(Student a:list) {
			if(a.getId()==studentId) {
				if(a.isMark()) {
					String s=String.valueOf(a.getAvg());					
					return "Student "+String.valueOf(a.getId())+": " +s;
				}
				return "Student "+String.valueOf(a.getId())+" hasn't taken any exams";
			}
		}
		return "student not found";
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		Course y=null;
		for(Course c:listC) {
			y=c;
			if(c.getCod()==courseId) {
				if(c.getMedia()==0)
				return "No student has taken the exam in "+String.valueOf(y.getTitle());
				
				return "The average for the course"+String.valueOf(c.getTitle())+
						" is: "+String.valueOf(c.getMedia());
				
			}
		}
		
		
		return "no course found with id="+ String.valueOf(courseId);
	}
	
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info of the best three students.
	 */
	public String topThreeStudents() {
		Student best[]=new Student[3];
		String tag="";
		int i=0;
		int nstud=0;
		float tmp=0;
		float max=0;
		int pos=0;
		int k=0;
		while(list[i]!=null) {
			if(list[i].isMark()) {
				list[i].calcola();
					
			}
			
			i++;
			
		}
		nstud=i;
	for(int j=0;j<nstud && k<3;j++) {
		max=0;
		pos=j;
		for(i=0;i<nstud;i++) {
			if(!list[i].isSelected()) {
			tmp=list[i].getPunteggio();
				if(tmp>max) {
				max=tmp;
				pos=i;
				}
			}
			
		}
		if(max!=0) {
		list[pos].setSelected(true);
		best[k++]=list[pos];
		tag+=classifica(list[pos]);
		}
		
		
	}
	
	
	return tag;
	}
	
	static String classifica(Student s) {
		if(s==null) return "non assegnato";
		
		
		return s.nomeCompleto()+" : "+String.valueOf(s.getPunteggio())+"\n";
	}
	
	
	
	
}

	

