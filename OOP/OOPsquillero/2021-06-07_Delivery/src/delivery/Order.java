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

public class Order {
	String dishName[],  customerName,  restaurantName;
	int deliveryTime,  deliveryDistance;
	 int quantity[];
	 boolean assegnato=false;
	public Order(String[] dishName, String customerName, String restaurantName, int deliveryTime, int deliveryDistance,
			int[] quantity) {
		this.dishName = dishName;
		this.customerName = customerName;
		this.restaurantName = restaurantName;
		this.deliveryTime = deliveryTime;
		this.deliveryDistance = deliveryDistance;
		this.quantity = quantity;
	}
	public boolean isAssegnato() {
		return assegnato;
	}
	public void setAssegnato(boolean assegnato) {
		this.assegnato = assegnato;
	}
	 
	 

}
