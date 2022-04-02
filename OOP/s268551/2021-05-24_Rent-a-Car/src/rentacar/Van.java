package rentacar;

public class Van extends Vehicle {
	int limit;

	public Van(int id,String manufacturer, String model, int year, String gear, String color, char category, int seats,int limit) {
		super(id, manufacturer, model, year, gear, color, category, seats);
		this.limit=limit;
	}
}
