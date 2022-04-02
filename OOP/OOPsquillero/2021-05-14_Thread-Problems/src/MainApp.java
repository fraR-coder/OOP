/*--------------*-----------------------------------------------------------*\
*|   ######     | CLASS SAMPLE FOR "OBJECT ORIENTED PROGRAMMING" (04JEY)     *
*|  #######     | (c) May 2021, Giovanni Squillero <squillero@polito.it>     *
*|  ####   \    | ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ *
*|   ##G   c\   | Copying and distributing this file for classroom use,      *
*|   ##     _\  | either with or without modification, are permitted without *
*|   |    _/    | royalties provided that this 9-line comment is preserved.  *
*|   |   _/     | ===> THIS FILE IS OFFERED AS-IS, WITHOUT ANY WARRANTY <=== *
\*--------------*-----------------------------------------------------------*/

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class MainApp {

	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(() -> { while(true) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("|STOP|");
				return;
			}
		}});
		
		//t.start();
		//Thread.sleep(2200);
		//t.interrupt();

		Thread t2 = new Thread(() -> { for(int u = 0; u < 3; ++u) {
			System.out.print("+");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("|STOP|");
				return;
			}
		}});
		
		t2.start();
		t2.join();
		System.out.println("Hey!");
		
		
	}
}
