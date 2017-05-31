package org.datapath.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.datapath.model.ErrorMessage;

@Provider
public class GenericExceptionHandler implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {

		ErrorMessage msg = new ErrorMessage(ex.getMessage(), 500, "http://datapath.io");
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(msg).build();

	}

}
