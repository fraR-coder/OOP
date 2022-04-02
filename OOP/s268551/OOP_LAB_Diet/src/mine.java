import static org.junit.Assert.assertNotNull;

import diet.Food;
import diet.Menu;
import diet.Order;
import diet.Recipe;
import diet.Restaurant;
import diet.Takeaway;
import diet.User;

public class mine {

	public static void main(String[] args) {
		
	
		Food foods = new Food();
		
        Takeaway takeaway = new Takeaway();
        
        
        //Defining materials used by restaurant r1 - Napoli
        foods.defineRawMaterial("Pasta", 350, 12, 72.2, 1.5);
        foods.defineRawMaterial("Oil", 900, 0, 0, 100);
        foods.defineRawMaterial("Nutella", 480, 6.8, 56, 31);
        foods.defineRawMaterial("Eggs", 120, 6.8, 56, 31);
        foods.defineRawMaterial("Flour", 100, 6.8, 56, 31);
        foods.defineRawMaterial("Sausage", 500, 15.8, 20, 4.1);
        foods.defineRawMaterial("Tomato sauce", 120, 6.8, 56, 31);
        foods.defineRawMaterial("Minced meat", 450, 6.8, 56, 31);
        foods.defineRawMaterial("Mozzarella", 250, 6.8, 56, 31);
        foods.defineRawMaterial("Olives", 60, 6.8, 56, 31);
        foods.defineRawMaterial("Pesto", 120, 6.8, 56, 20);
        foods.defineRawMaterial("Onion", 40, 3, 20.3, 1);
        foods.defineProduct("Crackers", 111, 2.6, 17.2, 3.5);
        foods.defineProduct("Beer 0.5l", 40, 0.5, 0.2, 0.05);
        foods.defineProduct("Grissini", 20, 0.5, 0.2, 0.05);
        
        Restaurant r1 = new Restaurant("Napoli",foods);
        r1.setHours("08:00", "14:00", "19:00", "03:00");
        takeaway.addRestaurant(r1);
        User u1 = takeaway.registerUser("Ralph", "Fiennes", "r.fiennes@gmail.com", "333413493");
        
        Order o1 = takeaway.createOrder(u1, "Napoli", 19, 30);//r1
        
     Recipe recipe1 = foods.createRecipe("Pasta and Nutella");
     foods.createRecipe("Pasta al Ragu").addIngredient("Pasta", 350)
		.addIngredient("Onion", 100)
		.addIngredient("Garlic", 40)
		.addIngredient("Tomato sauce", 250)
		.addIngredient("Red wine", 50)
		.addIngredient("Carrots", 150)
		.addIngredient("Bacon", 200)
		.addIngredient("Minced meat", 400);
     
     Menu r4_menu2 = r1.createMenu("M2");
     assertNotNull("Missing recipe",r4_menu2);
     r4_menu2.addRecipe("Pizza 1", 350).addProduct("Beer 0.5l");
     
        assertNotNull("Missing recipe",recipe1);
        recipe1.addIngredient("Pasta", 70).
          addIngredient("Nutella", 30);
        Menu r1_menu1 = r1.createMenu("M1");
        r1_menu1.addRecipe("Pasta and Nutella", 50).
   	 addProduct("Crackers");
        o1.addMenus("M1", 1).addMenus("M1", 2);
        o1.addMenus("M2", 2);
        String a =o1.toString();
	}

}
