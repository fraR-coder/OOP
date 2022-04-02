/*--------------*-----------------------------------------------------------*\
*|   ######     | CLASS SAMPLE FOR "OBJECT ORIENTED PROGRAMMING" (04JEY)     *
*|  #######     | (c) Mar 2021, Giovanni Squillero <squillero@polito.it>     *
*|  ####   \    | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
*|   ##G   c\   | Copying and distributing this file for classroom use,      *
*|   ##     _\  | either with or without modification, are permitted without *
*|   |    _/    | royalties provided that this 9-line comment is preserved.  *
*|   |   _/     | ===> THIS FILE IS OFFERED AS-IS, WITHOUT ANY WARRANTY <=== *
\*--------------*-----------------------------------------------------------*/

import it.polito.oop.books.Book;
import it.polito.oop.books.Library;

public class TestApp {

	public static void main(String[] args) {
		Library lib = new Library();
		
		Book guide = new Book("Hitchhiker's Guide to the Galaxy", "Douglas Adams", Book.GENRES.SCIFI);
		Book.Chapter c1 = guide.addChapter("42");
		Book.Chapter c2 = guide.addChapter("42");
		Book.Chapter c3 = guide.addChapter("42");
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		
		Book.Cover cover = new Book.Cover();
		cover.color = "Blue";
		cover.type = Book.Cover.TYPE.SOFT;
		
	}

}
