package org.datapath.test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import org.datapath.model.Message;
import org.datapath.resource.ResourceAPI;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatapathAPITest extends JerseyTest {

	@Override
	protected Application configure() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return new ResourceConfig(ResourceAPI.class);
	}

	@Test
	public void testcalculatePI() {
		Message response = target("calculatePI").request().get(Message.class);
		Assert.assertTrue("3.1415926545880506".equals(response.getMessage()));
	}

	@Test
	public void testwait() {
		Message response = target("wait").request().get(Message.class);
		Assert.assertTrue("Waited for 20 Second!!!".equals(response.getMessage()));
	}

	@Test
	public void testfail() {
		target("fail").request().get();

	}

	@Test
	public void testupdate() {
		Message response = target("/update/10").request().put(Entity.json(1), Message.class);
		Assert.assertTrue("10".equals(response.getMessage()));
	}

}
