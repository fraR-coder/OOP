package example;

import rentacar.Agency;
import rentacar.AgencyException;

public final class MyOwnTestApp {

	public static void main(String[] args) throws AgencyException {
		Agency hertz = new Agency();
		
		hertz.definePoints(0, .7, 2.3, 4.1, 7.2, 9.9);
		System.out.println(hertz.getPointsOfCategory('B'));
		
		hertz.registerUser("Jake Blues", "Chicago");
		hertz.registerUser("Elwood Blues", "Chicago");
		hertz.registerUser("Richard Hammond", "Solihull");
		hertz.registerUser("Jeremy Clarkson", "Doncaster");
		hertz.registerUser("AAAA Clarkson", "Aoncaster");
		hertz.registerUser("BBBBeremy Clarkson", "Aoncaster");
		hertz.registerUser("Zeremy Clarkson", "Solihull");

		System.out.println(hertz.getUserInfo());

		hertz.addCar("Smart", "Smart", 2008, "Automatic", "White", 'B', 2);
		hertz.addCar("Fiat", "Tipo", 2015, "automatic", "grey", 'E', 5);
		hertz.addCar("Fiat", "500", 2016, "manual", "grey", 'A', 4);
		hertz.addVan("Volkswagen", "Transporter", 2016, "automatic", "black", 'F', 8, 4250);
		hertz.addCar("BMW", "X5", 2018, "automtic", "blue", 'E', 5);
		hertz.addCar("Fiat", "Panda", 2015, "manual", "red", 'B', 4);
		
		
		
	}

}
