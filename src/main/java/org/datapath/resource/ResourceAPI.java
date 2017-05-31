package org.datapath.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.datapath.business.JobProcessor;
import org.datapath.business.ResponseBuilder;

@Path("/")
public class ResourceAPI {

	private static JobProcessor process;

	public ResourceAPI() {
		process = JobProcessor.getInstance();
	}

	@GET
	@Path("/calculatePI")
	@Produces(MediaType.APPLICATION_JSON)
	public Response calculatePI(@Context UriInfo info) {
		return new ResponseBuilder().createResponseforAll(process.calculatePI(), info);
	}

	@GET
	@Path("/wait")
	@Produces(MediaType.APPLICATION_JSON)
	public Response waitforSecond(@Context UriInfo info) {
		return new ResponseBuilder().createResponseforAll(process.waiting(), info);
	}

	@GET
	@Path("/fail")
	@Produces(MediaType.APPLICATION_JSON)
	public Response failImmediately() {
		return process.failfast();
	}

	@PUT
	@Path("/update/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changeThreadCount(@PathParam("value") String value, @Context UriInfo info) {
		return new ResponseBuilder().createResponseforAll(process.changeThreadCount(Integer.parseInt(value)), info);
	}

}
