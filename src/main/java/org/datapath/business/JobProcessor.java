package org.datapath.business;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Response;

import org.datapath.exception.FailImmediatelyException;
import org.datapath.model.Message;

/**
 * @author vkumar
 *
 */

public class JobProcessor {

	private static final String author = "Vivek Kumar";
	private ThreadPoolExecutor tp;
	private BlockingQueue<Runnable> sPoolWorkQueue;

	private JobProcessor() {
		sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(128);
		tp = (ThreadPoolExecutor) new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, sPoolWorkQueue);
	}

	private static JobProcessor _instance = null;

	public static JobProcessor getInstance() {
		if (_instance == null) {
			synchronized (JobProcessor.class) {
				if (_instance == null) {
					_instance = new JobProcessor();
				}
			}
		}
		return _instance;
	}

	public Message changeThreadCount(int count) {
		if (count > 0 && count < Integer.MAX_VALUE) {
			tp.setCorePoolSize(count);
			return new Message(tp.getCorePoolSize(), author, String.valueOf(tp.getCorePoolSize()));
		}
		return new Message(tp.getCorePoolSize(), author, "FAIL");
	}

	public Message waiting() {

		try {
			Future<String> result = tp.submit(new Waiting());
			return new Message(tp.getCorePoolSize(), author, result.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new Message(tp.getCorePoolSize(), author, "FAIL");
	}

	public Message calculatePI() {
		Future<Double> result = null;
		try {
			result = tp.submit(new CalculatePI());
			return new Message(tp.getCorePoolSize(), author, String.valueOf(result.get()));

		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new Message(tp.getCorePoolSize(), author, "FAIL");
	}

	public Response failfast() {
		throw new FailImmediatelyException();
	}

}
