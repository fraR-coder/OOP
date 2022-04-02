package it.polito.oop.paintings;

public class Painting {
	private String title;
	private Painter author;
	private Period period;
	
	private int year;
	
	public Painting(String title,int year) {
	
		this.title = title;
		this.year=year;
	}

	public String getTitle() {
		return title;
	}

	

	public Painter getAuthor() {
		return author;
	}

	public void setAuthor(Painter author) {
		if(author.getBirth()>year) {
			System.err.println(author+ " was not born yet");
			System.exit(1);
		}
		if(author.getDeath()<year && author.getDeath()>0) {
			System.err.println(author+ " was already dead");
			System.exit(1);
		}
		this.author = author;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}


	public String toString() {
		return title +" "+ year+ "by "+ author;
	}

}
