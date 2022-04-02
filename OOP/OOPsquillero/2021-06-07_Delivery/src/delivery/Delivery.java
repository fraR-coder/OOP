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
	
	
	public List<String> getRestaurantsForCategory(String category){
		
		List <Restaurant> l=new ArrayList<Restaurant>();
		
		if(!elencoCategorie.contains(category)) return l.stream().map(e->e.name).collect(Collectors.toList());
		
		for(Restaurant r:elencoRistoranti) {
			if(r.category.equals(category))
				l.add(r);
		}
		
		l.sort(null);
		return l.stream().map(e->e.name).collect(Collectors.toList());
		
		
		
	}
		
	
	// R2
	public void addDish(String name, String restaurantName, float price) throws DeliveryException {
		for(Restaurant r: elencoRistoranti) {
			if(r.name.equals(restaurantName)) {
				if(r.elencoPiatti.stream().map(e->e.name).collect(Collectors.toList()).contains(name)) {
					throw new DeliveryException();
				}
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
			for(Restaurant r:elencoRistoranti) {
				if(r.name.equals(restaurantName)) {
					r.numeroOrdini++;
					break;
				}
			}
			
		
		
		return elencoOrdini.size();
	}

	public ArrayList<Integer> scheduleDelivery(int deliveryTime, int maxDistance, int maxOrders) {
		ArrayList<Integer> l=new ArrayList<>();
		for(int i=0;i<maxOrders && i<elencoOrdini.size();i++) {
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
				r.calculateAvg();
			}
			
		}
	}
	public List<String> restaurantsAverageRating(){
		List<Restaurant> l=new ArrayList<>(elencoRistoranti);
		List<Restaurant>c=l.stream().filter(e->e.ratings.size()!=0).collect(Collectors.toList());
		c.sort(Comparator.comparing(Restaurant ::getAvg).reversed());
			
			return c.stream().map(e->e.name).collect(Collectors.toList());
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
	public Map<String,Long>ordersPerCategory() {
		
		Map<String,Long> m=new HashMap<>();
		for(String c:elencoCategorie) {
			for(Restaurant r:elencoRistoranti) {
				if(r.category.equals(c)) {
					if(m.containsKey(c)) {
						Long n=m.get(c);
						m.replace(c,n+r.numeroOrdini);
					}
					else {
						m.put(c,r.numeroOrdini);
					}
				}
			}
			
		}
		
		
		
		return m;	
	}
	
	
	public String bestRestaurant() {
		float max=0;
		Restaurant o=null;
		for(Restaurant r:elencoRistoranti) {
			if(r.getAvg()>max) {
				max=r.getAvg();
				o=r;
			}
		}
		
		return o.name;
		
		
	}

}

	
