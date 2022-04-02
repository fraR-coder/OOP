package travelPortal;

class Proposal {
	String code;
	String agency;
	String destination;
	int month,dayFirst,dayLast;
	int minN, maxNP, price;
	
	public Proposal(String code, String agency, String destination, int month, int dayFirst, int dayLast, int minN,
			int maxNP, int price) {
		this.code = code;
		this.agency = agency;
		this.destination = destination;
		this.month = month;
		this.dayFirst = dayFirst;
		this.dayLast = dayLast;
		this.minN = minN;
		this.maxNP = maxNP;
		this.price = price;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getPeriod() {
		return String.format("%d:%d:%d",month,dayFirst,dayLast);
	}

	public int getMinN() {
		return minN;
	}
	public void setMinN(int minN) {
		this.minN = minN;
	}
	public int getMaxNP() {
		return maxNP;
	}
	public void setMaxNP(int maxNP) {
		this.maxNP = maxNP;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDayFirst() {
		return dayFirst;
	}
	public void setDayFirst(int dayFirst) {
		this.dayFirst = dayFirst;
	}
	public int getDayLast() {
		return dayLast;
	}
	public void setDayLast(int dayLast) {
		this.dayLast = dayLast;
	}
	
	
	
}
