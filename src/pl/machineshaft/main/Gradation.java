package pl.machineshaft.main;

import java.util.ArrayList;
import java.util.Iterator;


public class Gradation {
	
	public static OutputShaftEnd outputShaftEnd;
	public static ShaftUnderTheSealant shaftUnderTheSealant;
	public static ShaftUnderTheBearing shaftUnderTheBearing;


	public static OutputShaftEnd chooseOutputShaftEnd(double[] cucumber) {
		ArrayList<OutputShaftEnd> outputShaftEnds = new ArrayList<>();
		outputShaftEnds.add(new OutputShaftEnd(10, 20, 23));
		outputShaftEnds.add(new OutputShaftEnd(11, 20, 23));
		outputShaftEnds.add(new OutputShaftEnd(12, 25, 30));
		outputShaftEnds.add(new OutputShaftEnd(14, 25, 30));
		outputShaftEnds.add(new OutputShaftEnd(16, 28, 40));
		outputShaftEnds.add(new OutputShaftEnd(18, 28, 40));
		outputShaftEnds.add(new OutputShaftEnd(19, 28, 40));
		outputShaftEnds.add(new OutputShaftEnd(20, 36, 50));
		outputShaftEnds.add(new OutputShaftEnd(22, 36, 50));
		outputShaftEnds.add(new OutputShaftEnd(24, 36, 50));

		Iterator<OutputShaftEnd> outputShaftEndsIterator = outputShaftEnds.iterator();
		
		boolean found = false;
		
		while (outputShaftEndsIterator.hasNext() && found == false) {
			
			outputShaftEnd = outputShaftEndsIterator.next();
			int diameter = outputShaftEnd.getDiameter();
			int shortEnd = outputShaftEnd.getShortEnd();
				
			if (diameter>=2*cucumber[cucumber.length-2] && diameter>=2*cucumber[cucumber.length-2-shortEnd/2]) {
			
				System.out.println(diameter);
				System.out.println("ogórek koniec - se/2 " + 2*cucumber[cucumber.length-2-shortEnd/2]);
				System.out.println("ogórek koniec " + 2*cucumber[cucumber.length-2]);
				found = true;
			}
		}
		
		return outputShaftEnd;
		
	}
	
	public static ShaftUnderTheSealant chooseShaftUnderTheSealant(double[] cucumber, OutputShaftEnd outputShaftEnd) {
		ArrayList<ShaftUnderTheSealant> shaftUnderTheSealants = new ArrayList<>();
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(20, 10));
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(22, 10));
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(24, 10));
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(25, 10));
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(26, 10));
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(28, 10));
		shaftUnderTheSealants.add(new ShaftUnderTheSealant(30, 10));
		
		Iterator<ShaftUnderTheSealant> shaftUnderTheSealantIterator = shaftUnderTheSealants.iterator();
		
		boolean found = false;
		
		while (shaftUnderTheSealantIterator.hasNext() && found == false) {
			shaftUnderTheSealant = shaftUnderTheSealantIterator.next();
			int diameter = shaftUnderTheSealant.getDiameter();
			int lenght = shaftUnderTheSealant.getWidth() * 3;
			
			if (diameter >= 2*cucumber[cucumber.length-2-outputShaftEnd.getShortEnd()/2] && diameter >= 2*cucumber[cucumber.length-2-outputShaftEnd.getShortEnd()/2-lenght]) {
				System.out.println("uszcelniacz " + diameter);
				found = true;
			}
			
		}
		return shaftUnderTheSealant;
		
	}
	
	public static ShaftUnderTheBearing chooseShaftUnderTheBearing(double[] cucumber, OutputShaftEnd outputShaftEnd, ShaftUnderTheSealant shaftUnderTheSealant) {
		ArrayList<ShaftUnderTheBearing> shaftUnderTheBearings = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			shaftUnderTheBearings.add(new ShaftUnderTheBearing(20+i*5, Math.round((20+i))/3));
		}
		Iterator<ShaftUnderTheBearing> shaftUnderTheBearingIterator = shaftUnderTheBearings.iterator();
		
		boolean found = false;
		
		while (shaftUnderTheBearingIterator.hasNext() && found == false) {
			shaftUnderTheBearing = shaftUnderTheBearingIterator.next();
			int diameter = shaftUnderTheBearing.getDiameter();
			int lenght = outputShaftEnd.getShortEnd()/2 + 3*shaftUnderTheSealant.getWidth();
			
			if (diameter > shaftUnderTheSealant.getDiameter() && diameter >= 2*cucumber[cucumber.length-2-lenght] && diameter>=2*cucumber[cucumber.length-2-lenght-shaftUnderTheBearing.getWidth()]) {
				System.out.println("czop pod ³o¿ysko: " + shaftUnderTheBearing.getDiameter());
				found = true;
			}
		}
	return shaftUnderTheBearing;	
	}

}
