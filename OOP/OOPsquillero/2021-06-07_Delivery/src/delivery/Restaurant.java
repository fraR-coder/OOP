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
import java.util.List;

public class Restaurant implements Comparable<Restaurant> {
	String category;
	String name;
	List<Integer> ratings=new ArrayList<>();
	Long numeroOrdini=(long) 0;
	
	List<Dish> elencoPiatti=new ArrayList<>();
	 float avg;
	
	
	
	public Restaurant(String category, String name) {
		this.category = category;
		this.name = name;
	}
	
	
	public void calculateAvg() {
		float sum=0;
		for(float i:ratings) {
			sum+=i;
		}
		this.avg= sum/ratings.size();
		
	}


	public float getAvg() {
		return avg;
	}


	@Override
	public int compareTo(Restaurant o) {
		if(this.avg>o.avg)
		return 1;
		if(this.avg==o.avg)
			return 0;
		else return -1;
	}


	


	

}
