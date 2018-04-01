package pl.machineshaft.main;

public class OutputShaftEnd {
	
	private int diameter;
	private int shortEnd;
	private int longEnd;

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public int getShortEnd() {
		return shortEnd;
	}

	public void setShortEnd(int shortEnd) {
		this.shortEnd = shortEnd;
	}

	public int getLongEnd() {
		return longEnd;
	}

	public void setLongEnd(int longEnd) {
		this.longEnd = longEnd;
	}

	public OutputShaftEnd(int diameter, int shortEnd, int longEnd) {
		setDiameter(diameter);
		setShortEnd(shortEnd);
		setLongEnd(longEnd);
	}
	
	

}
