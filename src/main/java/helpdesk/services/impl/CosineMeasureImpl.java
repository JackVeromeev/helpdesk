package helpdesk.services.impl;

import helpdesk.services.Measure;

public class CosineMeasureImpl implements Measure {

	@Override
	public double measure(double[] vec1, double[] vec2) {
		if (vec1 == null || vec2 == null ||
			vec1.length == 0 || vec2.length == 0 ||
			vec1.length != vec2.length) {
			throw new IllegalArgumentException();
		}
		
		double n = 0;
		for (int i = 0; i < vec1.length; i++) {
			n += vec1[i] * vec2[i];
		}
		
		double d1 = 0;
		double d2 = 0;
		for (int i = 0; i < vec1.length; i++) {
			d1 += vec1[i] * vec1[i];
			d2 += vec2[i] * vec2[i];
		}
		
		return n/(Math.sqrt(d1)*Math.sqrt(d2));
	}

}
