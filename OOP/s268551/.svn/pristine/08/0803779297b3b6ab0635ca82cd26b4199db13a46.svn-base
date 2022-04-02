package diet;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a complete menu.
 * 
 * It can be made up of both packaged products and servings of given recipes.
 *
 */
public class Menu implements NutritionalElement {
	
	Food food;
	
	private String name;
	private double calories = 0.0;
	private double proteins = 0.0;
	private double carbs = 0.0;
	private double fat = 0.0;
	
	private List<Componenti> componentiMenu = new LinkedList<>();
	private static class Componenti {
		final NutritionalElement ne;
		final double qty;
		public Componenti(NutritionalElement ne, double qty) {
			this.ne = ne;
			this.qty = qty;
		}
		
		
	}
	
	
	public Menu(String name,Food food) {
		
		this.name = name;
		this.food=food;
	}

	/**
	 * Adds a given serving size of a recipe.
	 * 
	 * The recipe is a name of a recipe defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 * @return the same Menu to allow method chaining
	 */
	public Menu addRecipe(String recipe, double quantity) {
		
		NutritionalElement ric=food.getRecipe(recipe);
		
		componentiMenu.add(new Componenti(ric, quantity));
		Recipe t=(Recipe) ric;
	
		calories += t.getCalories(quantity);
		proteins += t.getProteins(quantity);
		carbs += t.getCarbs(quantity);
		fat += t.getFat(quantity);
		
		
		
		return this;
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the
	 * {@Link Food} in which this menu has been defined.
	 * 
	 * @param product the name of the product to be used as ingredient
	 * @return the same Menu to allow method chaining
	 */
	public Menu addProduct(String product) {
		
		NutritionalElement prod=food.getProduct(product);
		
		componentiMenu.add(new Componenti(prod,1));
		calories += prod.getCalories();
		proteins += prod.getProteins();
		carbs += prod.getCarbs();
		fat += prod.getFat();
		
		return this;
	}

	/**
	 * Name of the menu
	 */
	@Override
	public String getName() {
		return name ;
	}

	/**
	 * Total KCal in the menu
	 */
	@Override
	public double getCalories() {
		return calories;
	}

	/**
	 * Total proteins in the menu
	 */
	@Override
	public double getProteins() {
		return proteins;
	}

	/**
	 * Total carbs in the menu
	 */
	@Override
	public double getCarbs() {
		return carbs;
	}

	/**
	 * Total fats in the menu
	 */
	@Override
	public double getFat() {
		return fat;
	}

	/**
	 * Indicates whether the nutritional values returned by the other methods
	 * refer to a conventional 100g quantity of nutritional element,
	 * or to a unit of element.
	 * 
	 * For the {@link Menu} class it must always return {@code false}:
	 * nutritional values are provided for the whole menu.
	 * 
	 * @return boolean 	indicator
	 */
	@Override
	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
