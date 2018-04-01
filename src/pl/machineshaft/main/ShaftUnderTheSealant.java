package pl.machineshaft.main;

public class ShaftUnderTheSealant {
	
	private int diameter;
	private int width;
	
	public int getDiameter() {
		return diameter;
	}
	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public ShaftUnderTheSealant(int diameter, int width) {
		setDiameter(diameter);
		setWidth(width);
	}
	
	

}
