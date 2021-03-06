package diet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Represents an order in the take-away system
 */
public class Order {
	
	Takeaway takeway;
	User user;
	String restaurantName;
	String orario="";
	Restaurant restaurant;
	Map<String,Componenti> orderedMenu=new HashMap<>();
	OrderStatus status=OrderStatus.ORDERED;
	PaymentMethod payment=PaymentMethod.CASH;
	
	private List<Componenti> componentiOrdine = new LinkedList<>();
	private static class Componenti {
		final Menu m;
		private int qty;
		public Componenti(Menu m, int qty) {
			this.m = m;
			this.qty = qty;
		}
		@Override
		public String toString() {
			 return "\t" + m.getName() + "->" + qty + "\n";
		}
		
		public void setQuantity(int quantity) {
			this.qty=quantity;
		}
		
	}
 
	public Order(User user, String restaurantName, int h, int m,Takeaway takeaway) {
		
		this.user = user;
		this.restaurantName = restaurantName;
		this.takeway=takeaway;
		restaurant=takeaway.elencoRestaurant.get(restaurantName);
		restaurant.elencoOrdini.add(this);
		orario=String.format("%02d:%02d",h,m);
		
		orario=restaurant.checkHours(orario);
		
	}

	/**
	 * Defines the possible order status
	 */
	public enum OrderStatus {
		ORDERED, READY, DELIVERED;
	}
	/**
	 * Defines the possible valid payment methods
	 */
	public enum PaymentMethod {
		PAID, CASH, CARD;
	}
		
	/**
	 * Total order price
	 * @return order price
	 */
	public double Price() {
		return -1.0;
	}
	
	/**
	 * define payment method
	 * 
	 * @param method payment method
	 */
	public void setPaymentMethod(PaymentMethod method) {
		this.payment=method;
	}
	
	/**
	 * get payment method
	 * 
	 * @return payment method
	 */
	public PaymentMethod getPaymentMethod() {
		return payment;
	}
	
	/**
	 * change order status
	 * @param newStatus order status
	 */
	public void setStatus(OrderStatus newStatus) {
		this.status=newStatus;
	}
	
	/**
	 * get current order status
	 * @return order status
	 */
	public OrderStatus getStatus(){
		return status;
	}
	
	/**
	 * Add a new menu with the relative order to the order.
	 * The menu must be defined in the {@link Food} object
	 * associated the restaurant that created the order.
	 * 
	 * @param menu     name of the menu
	 * @param quantity quantity of the menu
	 * @return this order to enable method chaining
	 */
	public Order addMenus(String menu, int quantity) {
		
		Menu m=restaurant.elencoMenu.get(menu);
		if(orderedMenu.containsKey(menu)) {
			Componenti c= orderedMenu.get(menu);
			c.setQuantity(quantity);
			return this;
		}
		orderedMenu.put(menu, new Componenti(m, quantity));
		

		return this;
	}

	
	
	
	@Override
	public String toString() {
		String tag=restaurantName + ", "+user.getFirstName()+ " "+
				user.getLastName()+ " : (" +orario + "):\n";
		for(Componenti c:orderedMenu.values()) {
			
			tag+=c.toString();
		}
		
		return tag;
				
	}

	 User getUser() {
		return user;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public String getOrario() {
		return orario;
	}
	
	/**
	 * Converts to a string as:
	 * <pre>
	 * RESTAURANT_NAME, USER_FIRST_NAME USER_LAST_NAME : DELIVERY(HH:MM):
	 * 	MENU_NAME_1->MENU_QUANTITY_1
	 * 	...
	 * 	MENU_NAME_k->MENU_QUANTITY_k
	 * </pre>
	 * @param h 
	 */
	
	
	
	
}
