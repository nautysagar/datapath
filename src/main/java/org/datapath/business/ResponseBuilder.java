package org.datapath.business;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.datapath.model.Message;
import org.datapath.resource.ResourceAPI;

import javax.ws.rs.core.UriInfo;

public class ResponseBuilder {

	String author = "Vivek Kumar";

	public Response createResponseforAll(Message m, UriInfo info) {
		Response r = null;
		if (m.getMessage().equals("FAIL"))
			return createResponseFail(m, info);

		switch (info.getPath().split("/")[0]) {

		case "calculatePI":
			getURLForSelf(info, m);
			getURLForFAIL(info, m);
			getURLForWAIT(info, m);
			getURLForUpdate(info, m);
			r = createResponse(info, m);
			break;
		case "wait":
			getURLForSelf(info, m);
			getURLForFAIL(info, m);
			getURLForPI(info, m);
			getURLForUpdate(info, m);
			r = createResponseOK(info, m);
			break;
		// case "fail":
		// getURLForSelf(info, m);
		// // getURLForFAIL(info,m);
		// getURLForPI(info, m);
		// getURLForUpdate(info, m);
		// r = createResponseOK(info, m);
		// break;
		case "update":
			getURLForSelf(info, m);
			getURLForFAIL(info, m);
			getURLForPI(info, m);
			getURLForWAIT(info, m);
			r = createResponse(info, m);
			break;
		default:
			r = createResponseFail(m, info);
			break;

		}
		return r;
	}

	public Response createResponse(UriInfo info, Message m) {

		URI uri = info.getAbsolutePathBuilder().path(ResourceAPI.class).build();
		return Response.created(uri).entity(m).build();

	}

	public Response createResponseOK(UriInfo info, Message m) {

		URI uri = info.getAbsolutePathBuilder().path(ResourceAPI.class).build();
		return Response.ok(uri).entity(m).build();

	}

	private Response createResponseFail(Message m, UriInfo info) {
		m.setMessage("FAILED");
		return Response.status(Status.EXPECTATION_FAILED).entity(m).build();
	}

	private void getURLForSelf(UriInfo info, Message m) {
		String link = info.getAbsolutePathBuilder().path(ResourceAPI.class).build().toString();
		m.addlinks(link, "self");
	}

	private void getURLForPI(UriInfo info, Message m) {
		String link = info.getBaseUriBuilder().path(ResourceAPI.class, "calculatePI").build().toString();
		m.addlinks(link, "CALCULATE PI");
	}

	private void getURLForFAIL(UriInfo info, Message m) {
		String link = info.getBaseUriBuilder().path(ResourceAPI.class, "failImmediately").build().toString();
		m.addlinks(link, "FAIL IMMEDIATELY");
	}

	private void getURLForWAIT(UriInfo info, Message m) {
		String link = info.getBaseUriBuilder().path(ResourceAPI.class, "waitforSecond").build().toString();
		m.addlinks(link, "WAIT FOR 20 SECOND");
	}

	private void getURLForUpdate(UriInfo info, Message m) {
		String link = info.getBaseUriBuilder().path(ResourceAPI.class, "changeThreadCount")
				.resolveTemplate("value", m.getThreadcount()).build().toString();
		m.addlinks(link, "UPDATE THREAD COUNT");
	}
}
