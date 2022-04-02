package diet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents the main class in the
 * take-away system.
 * 
 * It allows adding restaurant, users, and creating orders.
 *
 */
public class Takeaway {
	
	public Map<String,Restaurant> elencoRestaurant = new HashMap<>();
	public List<User> elencoUsers = new LinkedList<>();

	/**
	 * Adds a new restaurant to the take-away system
	 * 
	 * @param r the restaurant to be added
	 */
	public void addRestaurant(Restaurant r) {
		elencoRestaurant.put(r.getName(),r);
	}
	
	/**
	 * Returns the collections of restaurants
	 * 
	 * @return collection of added restaurants
	 */
	public Collection<String> restaurants() {
		
		return elencoRestaurant.keySet();
	}
	
	/**
	 * Define a new user
	 * 
	 * @param firstName first name of the user
	 * @param lastName  last name of the user
	 * @param email     email
	 * @param phoneNumber telephone number
	 * @return
	 */
	public User registerUser(String firstName, String lastName, String email, String phoneNumber) {
		User u=new User(firstName, lastName, email, phoneNumber);
		elencoUsers.add(u);
		return u ;
	}
	
	/**
	 * Gets the collection of registered users
	 * 
	 * @return the collection of users
	 */
	public Collection<User> users(){
		
		elencoUsers.sort(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName));

		
		
		return elencoUsers;
	}
	
	



	/**
	 * Create a new order by a user to a given restaurant.
	 * 
	 * The order is initially empty and is characterized
	 * by a desired delivery time. 
	 * 
	 * @param user				user object
	 * @param restaurantName	restaurant name
	 * @param h					delivery time hour
	 * @param m					delivery time minutes
	 * @return
	 */
	public Order createOrder(User user, String restaurantName, int h, int m) {
		Order o=new Order(user, restaurantName, h, m,this);
		
		return o;
	}
	
	/**
	 * Retrieves the collection of restaurant that are open
	 * at the given time.
	 * 
	 * @param time time to check open
	 * 
	 * @return collection of restaurants
	 */
	public Collection<Restaurant> openedRestaurants(String time){
		
		List<Restaurant> aperti=new ArrayList<Restaurant>() {};
		for(Restaurant r: elencoRestaurant.values()) {
			if (r.isOpen(time)) aperti.add(r);
		}
		
		aperti.sort(Comparator.comparing(Restaurant::getName));
		
		return aperti;
	}

	
	
}
