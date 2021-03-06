/*--------------*-----------------------------------------------------------*\
*|   ######     | CLASS SAMPLE FOR "OBJECT ORIENTED PROGRAMMING" (04JEY)     *
*|  #######     | (c) Apr 2021, Giovanni Squillero <squillero@polito.it>     *
*|  ####   \    | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
*|   ##G   c\   | Copying and distributing this file for classroom use,      *
*|   ##     _\  | either with or without modification, are permitted without *
*|   |    _/    | royalties provided that this 9-line comment is preserved.  *
*|   |   _/     | ===> THIS FILE IS OFFERED AS-IS, WITHOUT ANY WARRANTY <=== *
\*--------------*-----------------------------------------------------------*/

package it.polito.oop.bst;

public class Musician implements Comparable<Musician> {
	private String name;

	public Musician(String name) {
		super();
		this.name = name;
	}

	@Override
	public int compareTo(Musician o) {
		return name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return name;
	}

}
