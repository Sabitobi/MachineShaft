package pl.machineshaft.main;

public class InitialData {

	private int force1;
	private int longitudinalForce1;
	private int transverseForce1;
	private int force2;
	private int transverseForce2;

	private int lenght;
	private int lenght1;
	private int lenght2;

	private int diameter1;
	private int diameter2;

	private Material material;

	public int getForce1() {
		return force1;
	}

	public void setForce1(int force1) {
		this.force1 = force1;
	}

	public int getLongitudinalForce1() {
		return longitudinalForce1;
	}

	public void setLongitudinalForce1(int longitudinalForce1) {
		this.longitudinalForce1 = longitudinalForce1;
	}

	public int getTransverseForce1() {
		return transverseForce1;
	}

	public void setTransverseForce1(int transverseForce1) {
		this.transverseForce1 = transverseForce1;
	}

	public int getForce2() {
		return force2;
	}

	public void setForce2(int force2) {
		this.force2 = force2;
	}

	public int getTransverseForce2() {
		return transverseForce2;
	}

	public void setTransverseForce2(int transverseForce2) {
		this.transverseForce2 = transverseForce2;
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public int getLenght1() {
		return lenght1;
	}

	public void setLenght1(int lenght1) {
		this.lenght1 = lenght1;
	}

	public int getLenght2() {
		return lenght2;
	}

	public void setLenght2(int lenght2) {
		this.lenght2 = lenght2;
	}

	public int getDiameter1() {
		return diameter1;
	}

	public void setDiameter1(int diameter1) {
		this.diameter1 = diameter1;
	}

	public int getDiameter2() {
		return diameter2;
	}

	public void setDiameter2(int diameter2) {
		this.diameter2 = diameter2;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public InitialData(int force1, int longitudinalForce1, int transverseForce1, int force2, int transverseForce2,
			int lenght, int lenght1, int lenght2, int diameter1, int diameter2, Material material) {
		super();
		this.force1 = force1;
		this.longitudinalForce1 = longitudinalForce1;
		this.transverseForce1 = transverseForce1;
		this.force2 = force2;
		this.transverseForce2 = transverseForce2;
		this.lenght = lenght;
		this.lenght1 = lenght1;
		this.lenght2 = lenght2;
		this.diameter1 = diameter1;
		this.diameter2 = diameter2;
		this.material = material;
	}

}
