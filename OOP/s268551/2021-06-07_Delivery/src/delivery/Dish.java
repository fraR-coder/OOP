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

public class Dish implements Comparable<Dish> {
	
	String name, restaurantName;
	float price;
	
	public Dish(String name, String restaurantName, float price) {
		this.name = name;
		this.restaurantName = restaurantName;
		this.price = price;
	}

	@Override
	public int compareTo(Dish o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
}
