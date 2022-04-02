package it.polito.po.test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import delivery.Delivery;
import delivery.DeliveryException;


public class TestR2 {
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
            d.addDish("Pizza capricciosa", "Il re della pizza", 7);
            d.addDish("Pizza diavola", "Il re della pizza", 5);
            d.addDish("Pizza marinara", "Il re della pizza", 3.5f);
            d.addDish("Spaghetti aglio olio peperoncino", "La vecchia trattoria", 6);
            d.addDish("Coniglio alla cacciatora", "La vecchia trattoria", 9);
        } catch (DeliveryException e) {
        }
    }

    @Test(expected=DeliveryException.class)
    public void testDuplicateDish() throws DeliveryException {
        d.addDish("Pizza marinara", "Il re della pizza", 3.5f);
    }
    
    @Test
    public void testGetDishesByPriceExtremes() {
        assertEquals(0,d.getDishesByPrice(0, 3).size());
        assertEquals(0,d.getDishesByPrice(10, 15).size());
    }
    
    @Test
    public void testGetDishesByPrice() {
        Map<String, List<String>> dishesMap = d.getDishesByPrice(3.5f,7);
        assertEquals(2,dishesMap.size());
        assertEquals(3,dishesMap.get("Il re della pizza").size());
        assertEquals(1,dishesMap.get("La vecchia trattoria").size());
    }
    
    @Test
    public void testGetDishesByPrice2() {
        Map<String, List<String>> dishesMap = d.getDishesByPrice(3.5f,7);
        assertNull(dishesMap.get("Il drago d'oro"));
    }
    
    @Test
    public void testGetDishesForRestaurant() {
        String[] expectedDishes = { "Pizza capricciosa", "Pizza diavola", "Pizza marinara" };
        String[] actualDishes = 
                d.getDishesForRestaurant("Il re della pizza")
                .stream().collect(Collectors.toList()).toArray(new String[3]);
        assertArrayEquals(expectedDishes, actualDishes);
    }
    
    @Test
    public void testGetDishesForRestaurantUnexistent() {
        assertEquals(0,d.getDishesForRestaurant("Il drago d'oro").size());
        assertEquals(0,d.getDishesForRestaurant("Il re del kebab").size());
    }
    
    @Test
    public void testGetDishesByCategory() throws DeliveryException {
        d.addRestaurant("Il pescato fresco", "Italian");
        d.addDish("Orata al forno", "Il pescato fresco", 15);
        d.addDish("Spigola al sale", "Il pescato fresco", 20);
        assertEquals(4,d.getDishesByCategory("Italian").size());
    }
}
