package org.datapath.business;

import java.util.concurrent.Callable;

/**
 * @author vkumar
 *
 */

public class CalculatePI implements Callable<Double> {

	@Override
	public Double call() throws Exception {
		int count = 999999999;
		double pi = 0;
		double denominator = 1;

		for (int x = 0; x < count; x++) {

			if (x % 2 == 0) {
				pi = pi + (1 / denominator);
			} else {
				pi = pi - (1 / denominator);
			}
			denominator = denominator + 2;
		}
		return pi * 4;

	}

}
