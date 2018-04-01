package pl.machineshaft.main;

public class BendingMoments {

	// zamienic na InitialData id
	public double[] calculateZY(InitialData id) {
		double[] planeZY = new double[id.getLenght() + 1];
		double rBZ = ((double) id.getForce1() * id.getLenght2() - id.getTransverseForce2() * id.getLenght())
				/ id.getLenght1();
		double rAZ = id.getForce1() - rBZ - id.getTransverseForce2();

		System.out.println("rAZ=" + rAZ);
		System.out.println("rBZ=" + rBZ);

		for (int i = 0; i < planeZY.length; i++) {

			if (i <= id.getLenght2()) {
				planeZY[i] = -rAZ * i;
			} else if ((i > id.getLenght2()) && (i <= id.getLenght1())) {
				planeZY[i] = -rAZ * i + id.getForce1() * (i - id.getLenght2());
			} else if (i > id.getLenght1()) {
				planeZY[i] = -rAZ * i + id.getForce1() * (i - id.getLenght2()) - rBZ * (i - id.getLenght1());
			}
		}
		return planeZY;
	}

	public double[] calculateXY(InitialData id) {
		double[] planeZY = new double[id.getLenght() + 1];
		double rBX = ((double) id.getTransverseForce1() * id.getLenght2()
				- id.getLongitudinalForce1() * id.getDiameter1() / 2 - id.getForce2() * id.getLenght())
				/ id.getLenght1();
		double rAX = id.getTransverseForce1() - rBX - id.getForce2();

		System.out.println("rAX=" + rAX);
		System.out.println("rBX=" + rBX);

		for (int i = 0; i < planeZY.length; i++) {
			// TO RE-DO
			if (i <= id.getLenght2()) {
				planeZY[i] = -rAX * i;
			} else if ((i > id.getLenght2()) && (i <= id.getLenght1())) {
				planeZY[i] = -rAX * i + id.getTransverseForce1() * (i - id.getLenght2())
						+ id.getLongitudinalForce1() * id.getDiameter1() / 2;
			} else if (i > id.getLenght1()) {
				planeZY[i] = -rAX * i + id.getTransverseForce1() * (i - id.getLenght2())
						+ id.getLongitudinalForce1() * id.getDiameter1() / 2 - rBX * (i - id.getLenght1());
				;
			}
		}

		return planeZY;
	}

	public double[] calculateResultantBendingMoment(double[] planeZY, double[] planeXY) {
		double[] resultantBendingMoment = new double[planeXY.length];

		for (int i = 0; i < resultantBendingMoment.length; i++) {
			resultantBendingMoment[i] = Math.sqrt(planeXY[i] * planeXY[i] + planeZY[i] * planeZY[i]);
		}
		return resultantBendingMoment;
	}

	public double[] calculateTorque(InitialData id) {
		double[] torque = new double[id.getLenght() + 1];

		if (id.getForce1() * id.getDiameter1() == id.getForce2() * id.getDiameter2()) {

			for (int i = 0; i < id.getLenght2() - 1; i++) {
				torque[i] = 0.0;
			}
			for (int i = id.getLenght2(); i < id.getLenght(); i++) {
				torque[i] = id.getForce1() * id.getDiameter1() / 2;
			}
		} else if (id.getForce1() * id.getDiameter1() != id.getForce2() * id.getDiameter2()) {
			System.err.println("Nieprawid³owa kinfiguracja parametrów F1, d1, F2, d2.");
		}
		return torque;

	}

	public double[] calculateReducedTorque(double[] torque, Material material) {
		double[] reducedTorque = new double[torque.length];
		int kGo = material.getKGo();
		int kSj = material.getKSj();

		for (int i = 0; i < reducedTorque.length; i++) {
			reducedTorque[i] = torque[i] * kGo / (2 * kSj);
		}
		return reducedTorque;
	}

	public double[] calculateReplacementMoment(double[] resultantBendingMoment, double[] reducedTorque) {
		double[] replacementMoment = new double[reducedTorque.length];

		for (int i = 0; i < replacementMoment.length; i++) {
			replacementMoment[i] = Math
					.sqrt(resultantBendingMoment[i] * resultantBendingMoment[i] + reducedTorque[i] * reducedTorque[i]);
		}
		return replacementMoment;
	}

	public double[] calculateCalculationMoment(double[] replacementMoment, InitialData id) {

		double[] calculationMoment = new double[replacementMoment.length];

		for (int i = 0; i < id.getLenght2(); i++) {
			calculationMoment[i] = replacementMoment[i];
		}
		for (int i = id.getLenght1(); i < id.getLenght(); i++) {
			calculationMoment[i] = replacementMoment[i];
		}
		for (int i = id.getLenght2(); i < id.getLenght1(); i++) {
			calculationMoment[i] = replacementMoment[id.getLenght2()]
					+ (replacementMoment[id.getLenght1()] - replacementMoment[id.getLenght2()]) * (i - id.getLenght2())
							/ (id.getLenght1() - id.getLenght2());
		}

		return calculationMoment;
	}

	public double[] calculateCucumber(double[] calculationMoment, InitialData id) {
		double[] cucumber = new double[calculationMoment.length];

		for (int i = 0; i < calculationMoment.length; i++) {
			cucumber[i] = 0.5 * 1000 * Math.cbrt(10 * calculationMoment[i] / id.getMaterial().getKGo());
		}
		return cucumber;
	}
}
