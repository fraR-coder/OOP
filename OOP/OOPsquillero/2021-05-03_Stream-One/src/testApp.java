/*--------------*-----------------------------------------------------------*\
*|   ######     | CLASS SAMPLE FOR "OBJECT ORIENTED PROGRAMMING" (04JEY)     *
*|  #######     | (c) May 2021, Giovanni Squillero <squillero@polito.it>     *
*|  ####   \    | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
*|   ##G   c\   | Copying and distributing this file for classroom use,      *
*|   ##     _\  | either with or without modification, are permitted without *
*|   |    _/    | royalties provided that this 9-line comment is preserved.  *
*|   |   _/     | ===> THIS FILE IS OFFERED AS-IS, WITHOUT ANY WARRANTY <=== *
\*--------------*-----------------------------------------------------------*/

import java.util.stream.Stream;

public final class testApp {

	public static void main(String[] args) {
		
	}
	
	void oldExample() {
		// Stream.generate(() ->
		// Math.random()).limit(10).sorted().forEach(System.out::println);
		// Stream.of("John", "Paul", "Geroge", "Ringo").filter((x) -> x.equals("Stu")).forEach(System.out::println);

		Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);

		Stream.generate(() -> Math.random())
			.limit(10)
			.map((n) -> "I said: " + n)
			.forEach(System.out::println);

		Stream.of("John", "Paul", "Geroge", "Ringo").map(String::length).forEach(System.out::println);
	}

}
