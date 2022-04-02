package diet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import diet.Order.OrderStatus;

/**
 * Represents a restaurant in the take-away system
 *
 */
public class Restaurant implements Comparable<Restaurant>{
	
	private final static int dim=8;
	Food food;
	String name;
	
	List<Order> elencoOrdini=new ArrayList<>();
	String orari[]=new String[dim];
	Map<String,Menu> elencoMenu=new HashMap<>();
	
	
	
	/**
	 * Constructor for a new restaurant.
	 * 
	 * Materials and recipes are taken from
	 * the food object provided as argument.
	 * 
	 * @param name	unique name for the restaurant
	 * @param food	reference food object
	 */
	public Restaurant(String name, Food food) {
		// TODO: implement constructor
		this.name=name;
		this.food=food;
		
	}
	
	/**
	 * gets the name of the restaurant
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Define opening hours.
	 * 
	 * The opening hours are considered in pairs.
	 * Each pair has the initial time and the final time
	 * of opening intervals.
	 * 
	 * for a restaurant opened from 8:15 until 14:00 and from 19:00 until 00:00, 
	 * is thoud be called as {@code setHours("08:15", "14:00", "19:00", "00:00")}.
	 * 
	 * @param hm a list of opening hours
	 */
	public void setHours(String ... hm) {
		
		this.orari=hm;
	}
	

	public Menu getMenu(String name) {
		return null;
	}
	
	/**
	 * Creates a new menu
	 * 
	 * @param name name of the menu
	 * 
	 * @return the newly created menu
	 */
	public Menu createMenu(String name) {
		Menu m=new Menu(name, food);
		elencoMenu.put(name,m);
	
		return m;
	}

	/**
	 * Find all orders for this restaurant with 
	 * the given status.
	 * 
	 * The output is a string formatted as:
	 * <pre>
	 * Napoli, Judi Dench : (19:00):
	 * 	M6->1
	 * Napoli, Ralph Fiennes : (19:00):
	 * 	M1->2
	 * 	M6->1
	 * </pre>
	 * 
	 * The orders are sorted by name of restaurant, name of the user, and delivery time.
	 * 
	 * @param status the status of the searched orders
	 * 
	 * @return the description of orders satisfying the criterion
	 */
	public String ordersWithStatus(OrderStatus status) {
		String tag="";
		
		elencoOrdini.sort(Comparator.comparing(Order::getRestaurantName)
			    .thenComparing(Order::getUser)
			    .thenComparing(Order::getOrario));
		
		/*elencoOrdini.sort(new Comparator<Order>() {
							@Override
							public int compare(Order o1,Order o2) {
								
								if(o1.getRestaurantName().compareTo(o2.getRestaurantName())>0) return 1;
								if(o1.getRestaurantName().compareTo(o2.getRestaurantName())<0) return -1;
								
								
								
								if(o1.getUser().getFirstName().compareTo(o2.getUser().getFirstName())>0) return 1;
								if(o1.getUser().getFirstName().compareTo(o2.getUser().getFirstName())<0) return -1;
								
								if(o1.orario.compareTo(o2.orario)>0)return 1;
								if(o1.orario.compareTo(o2.orario)<0)return -1;
								
									
								return 1;
							}
		});
		*/
		for(Order o:elencoOrdini) {
			if(o.status==status)
			tag+=o.toString();
		}
		
		
		return tag;
	}

	

	public String checkHours(String orario) {
		// TODO Auto-generated method stub
		
			int n=orari.length;
			if(orario.compareTo(orari[n-1])>0 ) {
				if(orario.compareTo(orari[0])<0) {
			
				orario=orari[0];
				return orario;
			}
			}
		if(orario.compareTo(orari[0])<0) {
			
			orario=orari[0];
			return orario;
		
		}
			
		for(int i=1;i<n-1;i=i+2) {
			if(orario.compareTo(orari[i])>0 && orario.compareTo(orari[i+1])<0) {
				orario=orari[i+1];
				return orario;
			}
		}
		return orario;
		
		
	}
	
	public boolean isOpen(String orario) {
		int n=orari.length;
	
		for(int i=0;i<n-1;i=i+2) {
			if(orario.compareTo(orari[i])>=0 && orario.compareTo(orari[i+1])<0) {
				return true;
			}
		
		
		}
		return false;
	
	}

	@Override
	public int compareTo(Restaurant o) {
		return this.getName().compareTo(o.getName());
	}
}
