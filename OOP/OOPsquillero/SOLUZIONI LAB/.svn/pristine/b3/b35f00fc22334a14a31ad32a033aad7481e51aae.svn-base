package university;

 class Student {
	
	private static final String SEPARATOR = " ";
	private int ID;
	private String first;
	private String last;
	
	private Course[] courses;
	
	public Student(int id, String first, String last) {
		this.ID = id;
		this.first = first;
		this.last = last;
		courses = new Course[University.MAX_COURSES_PER_STUDENT];
	}
	
	public String toString(){
		return ID + SEPARATOR + first + SEPARATOR + last;
		// TODO: probably to be optimized with StringBuffer
//		return (new StringBuffer()).append(ID).append(SEPARATOR).
//				append(first).append(SEPARATOR).
//				append(last).toString();
	}
	
	public void enroll(Course c){
		for(int i=0; i< courses.length; ++i){
			if( courses[i] == null){
				courses[i] = c;
				break;
			}
		}
	}

	public String courses() {
		StringBuffer result = new StringBuffer();
		for(Course c : courses){
			if(c!=null){
				result.append(c).append("\n");
			}
		}
		return result.toString();
	}

}
