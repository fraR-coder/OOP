package example;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import delivery.Delivery;
import delivery.DeliveryException;

public class TestApp {
@Test
public void test() throws DeliveryException {
	
	Delivery d = new Delivery();
	//R1
	
	try{
		d.addCategory("Traditional");
		d.addCategory("Fast Food");
		d.addCategory("Chinese");
		d.addCategory("Chinese");
	    fail("Duplicated category not detected");
	} catch(Exception ex){} //ok

	ArrayList<String> c = d.getCategories();
	assertEquals(3,c.size());
	
	try {
		d.addRestaurant("Sol levante", "Chinese");
		d.addRestaurant("La trattoria", "Traditional");
		d.addRestaurant("Japps", "Japanese");
	    fail("Unknown category not detected");
	} catch(Exception ex){} //ok
	d.getRestaurantsForCategory("Chinese").stream().forEach(r -> System.out.println (r));
	
	//R2
	try {
		d.addDish("Involtini primavera", "Sol levante", (float) 10.1);
		d.addDish("Ravioli", "Sol levante", (float) 20.1);
		d.addDish("Involtini primavera", "Sol levante", (float) 0.1);
	    fail("Duplicated dish not detected");
	} catch(Exception ex){} //ok
	
	assertEquals(2,d.getDishesForRestaurant("Sol levante").size());
	
	//R3
	String[] dishNames = { "Involtini primavera" , "Ravioli" };
	int[] quantities1 = { 2 , 3 };
	int[] quantities2 = { 3 , 5 };
    int o1 = d.addOrder(dishNames, quantities1, "Customer1", "Sol levante", 10, 3);
    int o2 = d.addOrder(dishNames, quantities2, "Customer2", "Sol levante", 10, 4);
    assertEquals(1,o1);
    assertEquals(2,o2);
	assertEquals(2,d.scheduleDelivery(10, 5, 2).size());
	
	//R4
	d.setRatingForRestaurant("Sol levante", 5);
	d.setRatingForRestaurant("Sol levante", 3);
	d.setRatingForRestaurant("La trattoria", 3);
	assertEquals("Sol levante",d.restaurantsAverageRating().get(0));
	
	//R5
	assertEquals(new Long(2),d.ordersPerCategory().get("Chinese"));
	
}
}
