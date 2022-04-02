
public class Largeintarray {
	private int[] array;
	
	public Largeintarray(){
		array=new int[100];
		
	}
	public int get(int index) {
		if(index<array.length)
		return array[index];
		else {
			return 0;
		}
		
	}
	public void set(int index,int value) {
		if(array.length>index) {
			array[index]=value;
		}
		else {
			int[] array2=new int[index+1];
			for(int i=0;i<array.length;++i) {
				array2[i]=array[i];
			
			}
			array2[index]=value;
			array=array2;
		}
	}
	

}
