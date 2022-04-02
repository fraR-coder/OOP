package diet;

public class Prodotti implements NutritionalElement {
	
	 private  String name; 
	 private  double total_calories;
	 private  double proteins;
	 private  double carbs;
	 private  double fat;
	 
	public Prodotti(String name, double total_calories, double proteins, double carbs, double fat) {
		
		this.name = name;
		this.total_calories = total_calories;
		this.proteins = proteins;
		this.carbs = carbs;
		this.fat = fat;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		
		
	}

	@Override
	public double getCalories() {
		return total_calories;
	}

	public void setCalories(double total_calories) {
		this.total_calories = total_calories;
	}

	@Override
	public double getProteins() {
		return proteins;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	@Override
	public double getCarbs() {
		return carbs;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	@Override
	public double getFat() {
		return fat;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	@Override
	public boolean per100g() {
		// TODO Auto-generated method stub
		return false;
	}

	

	 
}
