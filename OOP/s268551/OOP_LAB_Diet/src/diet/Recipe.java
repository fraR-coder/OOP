package diet;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a recipe of the diet.
 * 
 * A recipe consists of a a set of ingredients that are given amounts of raw materials.
 * The overall nutritional values of a recipe can be computed
 * on the basis of the ingredients' values and are expressed per 100g
 * 
 *
 */
public class Recipe implements NutritionalElement {
    
	String name;
	private List<Ingredient> materiePrime = new LinkedList<Ingredient>();
	private static class Ingredient {
		final NutritionalElement en;
		final double qty;
		Ingredient(NutritionalElement e, double q){
			this.en=e; this.qty=q;
		}
	}
	
	Food food;
	
	private double calories = 0.0;
	private double proteins = 0.0;
	private double carbs = 0.0;
	private double fat = 0.0;
	private double weight=0.0;
	
	

	public Recipe(String name,Food food) {
		
		this.name = name;
		this.food =food;
	}

	/**
	 * Adds a given quantity of an ingredient to the recipe.
	 * The ingredient is a raw material.
	 * 
	 * @param material the name of the raw material to be used as ingredient
	 * @param quantity the amount in grams of the raw material to be used
	 * @return the same Recipe object, it allows method chaining.
	 */
	public Recipe addIngredient(String material, double quantity) {
		
		NutritionalElement en = food.getRawMaterial(material);

		Ingredient ing = new Ingredient(en,quantity);
		materiePrime.add(ing);

		// With the compact version
		calories += en.getCalories(quantity);
		proteins += en.getProteins(quantity);
		carbs += en.getCarbs(quantity);
		fat += en.getFat(quantity);

		weight += quantity;

		return this;
	}



	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public double getCalories() {
		return calories*100/weight;
	}

	public void setCalories(double calories) {
		this.calories=calories;
	}

	@Override
	public double getProteins() {
		return proteins*100/weight;
	}

	public void setProteins(double proteins) {
		this.proteins = proteins;
	}

	@Override
	public double getCarbs() {
		return carbs*100/weight;
	}

	public void setCarbs(double carbs) {
		this.carbs = carbs;
	}

	@Override
	public double getFat() {
		return fat*100/weight;
	}

	public void setFat(double fat) {
		this.fat = fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Recipe} class it must always return {@code true}:
	 * a recipe expresses nutritional values per 100g
	 * 
	 * @return boolean indicator
	 */
	@Override
	public boolean per100g() {
		return true;
	}
	
	
	/**
	 * Returns the ingredients composing the recipe.
	 * 
	 * A string that contains all the ingredients, one per per line, 
	 * using the following format:
	 * {@code "Material : ###.#"} where <i>Material</i> is the name of the 
	 * raw material and <i>###.#</i> is the relative quantity. 
	 * 
	 * Lines are all terminated with character {@code '\n'} and the ingredients 
	 * must appear in the same order they have been added to the recipe.
	 */
	@Override
	public String toString() {
		String tag="";
		
		for(Ingredient i: materiePrime) {
			tag+= i.en.getName()+": "+ i.qty+"\n";
		}
		
		
		
		return tag;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	

	public void setName(String name) {
		this.name = name;
	}
}
