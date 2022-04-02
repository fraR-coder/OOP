/*--------------*-----------------------------------------------------------*\
*|   ######     | CLASS SAMPLE FOR "OBJECT ORIENTED PROGRAMMING" (04JEY)     *
*|  #######     | (c) Jun 2021, Giovanni Squillero <squillero@polito.it>     *
*|  ####   \    | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
*|   ##G   c\   | Copying and distributing this file for classroom use,      *
*|   ##     _\  | either with or without modification, are permitted without *
*|   |    _/    | royalties provided that this 9-line comment is preserved.  *
*|   |   _/     | ===> THIS FILE IS OFFERED AS-IS, WITHOUT ANY WARRANTY <=== *
\*--------------*-----------------------------------------------------------*/

package delivery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Delivery {
	
	List<String> elencoCategorie=new ArrayList<>();
	List<Restaurant> elencoRistoranti=new ArrayList<Restaurant>();
	List<Order> elencoOrdini=new ArrayList<>();
	Map<String,Order> ordineR=new HashMap<>();
	
	public Delivery() {
		
	}

	// R1

	public void addCategory(String category) throws DeliveryException {
		if(elencoCategorie.contains(category)) 
			throw new DeliveryException();
		else elencoCategorie.add(category);
	}
	
	public ArrayList<String> getCategories(){
		return (ArrayList<String>) this.elencoCategorie;
	}
	
	public void addRestaurant(String name, String category) throws DeliveryException {
		if(!elencoCategorie.contains(category))
			throw new DeliveryException();
		else elencoRistoranti.add(new Restaurant(category, name));
	}
	
	
	public Collection<Restaurant> getRestaurantsForCategory(String category){
		
		List <Restaurant> l=new ArrayList<Restaurant>();
		
		if(!elencoCategorie.contains(category)) return l;
		
		for(Restaurant r:elencoRistoranti) {
			if(r.category.equals(category))
				l.add(r);
		}
		
		l.sort(null);
		return l;
		
		
		
	}
		
	
	// R2
	public void addDish(String name, String restaurantName, float price) {
		for(Restaurant r: elencoRistoranti) {
			if(r.name.equals(restaurantName)) {
				r.elencoPiatti.add(new Dish(name, restaurantName, price));
			}
		}
	}

	public Map<String,List<String>> getDishesByPrice(float minprice, float maxprice){
		
		Map<String,List<String>> m=new HashMap<>();
		
		for(Restaurant r: elencoRistoranti) {
			String name=r.name;
			List<String> l=new ArrayList<String>();
			for(Dish d:r.elencoPiatti) {
				if(d.price<=maxprice && d.price>=minprice) {
					l.add(d.name);
				}
			}
			if(l.size()>0) {
			m.put(name, l);
			}
		}
		
		return m;
		}
	
	
	public ArrayList<String> getDishesForRestaurant(String string) {
		List<String> l=new ArrayList<String>();
		for(Restaurant r:elencoRistoranti) {
			if(r.name.equals(string)) {
				for(Dish d:r.elencoPiatti) {
					l.add(d.name);
				}
			}
		}
		
		l.sort(null);
		
		return (ArrayList<String>) l;
	}
	
	public List<String> getDishesByCategory(String category){
		Set<String> l=new HashSet<String>();

		for(Restaurant r:elencoRistoranti) {
			if(r.category.equals(category)) {
				l.addAll(r.elencoPiatti.stream().map(e->e.name).collect(Collectors.toList()));
			}
		}
		
		List<String> a=new ArrayList<>(l);
		return a;
		
	}
	
	// R3

	public int addOrder(String[] dishNames, int quantity[], String customerName, String restaurantName, int deliveryTime, int deliveryDistance) {
			Order o=new Order(dishNames, customerName, restaurantName, deliveryTime, deliveryDistance, quantity);
			elencoOrdini.add(o);
			ordineR.put(restaurantName, o);
			
		
		
		return elencoOrdini.size();
	}

	public ArrayList<Integer> scheduleDelivery(int deliveryTime, int maxDistance, int maxOrders) {
		ArrayList<Integer> l=new ArrayList<>();
		for(int i=0;i<maxOrders;i++) {
			if(!elencoOrdini.get(i).isAssegnato() && elencoOrdini.get(i).deliveryTime==deliveryTime && elencoOrdini.get(i).deliveryDistance<=maxDistance) {
				elencoOrdini.get(i).setAssegnato(true);
				l.add(i+1);
			}
		}
		
		return l;
	}
	
	public int getPendingOrders() {
		int n=0;
		for(Order o:elencoOrdini) {
			if(!o.isAssegnato())n++;
		}
		
		return n;
		
	}
	

	

	/**
	 * The method scheduleDelivery(int deliveryTime, int maxDistance, int maxOrders)
	 * enables to schedule the deliveries. Delivero has several bellboys, some using
	 * bicycle, others motorbikes. To optimize the delivery based on the
	 * transportation this method returns the order numbers of the first maxOrders
	 * (following the orders arrival time) scheduled to be delivered at deliveryTime
	 * whose deliveryDistance is lower or equal that maxDistance. Once returned by
	 * the method the orders must be marked as assigned so that they will not be
	 * considered if the method is called again. The method returns an empty list if
	 * there are no orders (not yet assigned) that meet the requirements.
	 **/
	
	// R4
	
	public void setRatingForRestaurant(String restaurantName, int rating) {
		for(Restaurant r:elencoRistoranti) {
			if(r.name.equals(restaurantName)) {
				r.ratings.add(rating);
			}
		}
	}
	public List<String> restaurantsAverageRating(){
		List<Restaurant> l=new ArrayList<>(elencoRistoranti);
		l.stream().filter(e->e.ratings.size()>0).collect(Collectors.toList());
		l.sort(Comparator.comparing(Restaurant ::getAvg).reversed());
			
			return l.stream().map(e->e.name).collect(Collectors.toList());
		}
	
	/**
	 * returns the ordered list of restaurant.
	 * 
	 * The restaurant must be ordered by decreasing average rating. The average
	 * rating of a restaurant is the sum of all rating divided by the number of
	 * ratings.
	 * 
	 * @return ordered list of restaurants
	 */
	
	// R5

}

	
