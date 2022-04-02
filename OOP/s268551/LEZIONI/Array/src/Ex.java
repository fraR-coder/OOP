
public class Ex {

	public static void main(String[] args) {
		int[] array;
		
		array=new int[12];
		
		for(int t=0;t<array.length;++t) {
			array[t]=t*2 +1;
			
		}
		System.out.println(array);
		
		
		for(int element:array) {
			System.out.println(element);
		}
		
		Largeintarray baz=new Largeintarray();
		baz.set(1000, 42);
		System.out.println(baz.get(1000));
		

	}

}
