package rentacar;

public class Vehicle {
	String manufacturer;
	String model;
	int year;
	String gear;
	String color;
	char category;
	int seats;
	int id;
	
	public enum VehicleStatus {
		AVAILABLE,
		TAKEN
	}

	public Vehicle(int id,String manufacturer, String model, int year, String gear, String color, char category, int seats) {
		this.manufacturer = manufacturer;
		this.model = model;
		this.year = year;
		this.gear = gear;
		this.color = color;
		this.category = category;
		this.seats = seats;
		this.id=id;
	}
	
	public String getInfo() {
		return model+" : "+year+" : "+color;
	}
	
	
}
