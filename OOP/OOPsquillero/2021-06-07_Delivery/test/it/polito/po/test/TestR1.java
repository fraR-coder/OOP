package it.polito.po.test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import delivery.Delivery;
import delivery.DeliveryException;



public class TestR1 {
    Delivery d;
    
    @Before
    public void setUp() {
        d = new Delivery();
        try {
			d.addCategory("Chinese");
	        d.addCategory("Italian");
	        d.addCategory("Pizza");
	        d.addRestaurant("La muraglia cinese", "Chinese");
	        d.addRestaurant("Il drago d'oro", "Chinese");
	        d.addRestaurant("Il re della pizza", "Pizza");
	        d.addRestaurant("La vecchia trattoria", "Italian");
		} catch (DeliveryException e) {
		}
    }

    @Test
    public void testGetCategory() throws DeliveryException {
    	List<String> categories = d.getCategories();
    	assertEquals(3,categories.size());
    	assertTrue(categories.contains("Chinese"));
    	assertTrue(categories.contains("Italian"));
    	assertTrue(categories.contains("Pizza"));
    }
    
    @Test(expected=DeliveryException.class)
    public void testAddDuplicateCategory() throws DeliveryException {
    	d.addCategory("Italian");
    }
    
    @Test(expected=DeliveryException.class)
    public void testAddRestaurantUndefinedCategory() throws DeliveryException {
    	d.addRestaurant("Il Re del Kebab","Kebab");
    }
    
    @Test
    public void testGetRestaurantsForCategory() {
    	List<String> list1 = d.getRestaurantsForCategory("Chinese");
    	assertEquals(2, list1.size());
    	assertTrue(list1.contains("La muraglia cinese"));
    	assertTrue(list1.contains("Il drago d'oro"));
    	List<String> list2 = d.getRestaurantsForCategory("Italian");
    	assertEquals(1, list2.size());
    	assertTrue(list2.contains("La vecchia trattoria"));
    	assertFalse(list2.contains("Il drago d'oro"));
    }
    
    @Test
    public void testGetRestaurantsForCategoryNonExistent() {
    	assertEquals(0, d.getRestaurantsForCategory("Kebab").size());
    }
}
