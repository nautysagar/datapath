package org.datapath.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.datapath.model.ErrorMessage;

@Provider
public class FailExceptionMapper implements ExceptionMapper<FailImmediatelyException> {

	@Override
	public Response toResponse(FailImmediatelyException ex) {
		ErrorMessage msg = new ErrorMessage("Failing Immediately", 404, "http://datapath.io");
		return Response.status(Status.NOT_FOUND).entity(msg).build();
				
	}

}
