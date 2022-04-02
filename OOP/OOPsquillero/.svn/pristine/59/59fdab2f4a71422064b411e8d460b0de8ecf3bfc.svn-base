/*-************************************************************************-*\
*             *  CLASS SAMPLE FOR "OBJECT ORIENTED PROGRAMMING" (04JEY)      *
*   #####     *  (!) May-2021, Giovanni Squillero <squillero@polito.it>      *
*  ######     *                                                              *
*  ###   \    *  Copying and distributing this file, either with or without  *
*   ##G  c\   *  modification, are permitted in any medium without royalty,  *
*   #     _\  *  provided that this 10-line comment is preserved.            *
*   |  _/     *                                                              *
*             *  ===> THIS FILE IS OFFERED AS-IS, WITHOUT ANY WARRANTY <===  *
\*-************************************************************************-*/

import java.util.stream.Stream;
import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.Map;

public final class MainApp {

	public static void main(String[] args) {
		Map<String, List<String>> zap = Stream.of("foo", "bar", "baz", "foo", "gargle", "baz", "baz")
				.collect(groupingBy((x) -> x));
		System.out.println(zap);
	}

}
