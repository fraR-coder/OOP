package it.polito.oop.vaccination;

public class IntervallAge {
	
	private int minValue;
	private int maxValue;
	
	public IntervallAge(int minValue, int maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	
	public IntervallAge(String range) {
		String a= range.substring(1, range.length()-1);
		String[] values = a.split(",");
		minValue = Integer.parseInt(values[0]);
		if(values[1].equals("+")) maxValue=Integer.MAX_VALUE; 
		else maxValue = Integer.parseInt(values[1]);
		//if(minValue>maxValue) throw new IllegalArgumentException("Range should have min value lower than max value");
	}
	
	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public String toString() {
		if(maxValue==Integer.MAX_VALUE) return "[" + minValue + "," + "+)";
		return "[" + minValue + "," + maxValue + ")";
	}
	
	
	//public final static Range DEFAULT = new Range("0-"+Integer.MAX_VALUE);
	
	

}
