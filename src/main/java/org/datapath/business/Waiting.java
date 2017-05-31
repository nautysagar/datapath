package org.datapath.business;

import java.util.concurrent.Callable;

/**
 * @author vkumar
 *
 */

public class Waiting implements Callable<String> {

	@Override
	public String call() throws Exception {
		Thread.sleep(20 * 1000);
		return "Waited for 20 Second!!!";
	}

}
