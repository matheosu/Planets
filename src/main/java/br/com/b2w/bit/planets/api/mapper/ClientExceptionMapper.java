package br.com.b2w.bit.planets.api.mapper;


import br.com.b2w.bit.planets.dto.ErrorMessage;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ClientExceptionMapper implements ExceptionMapper<ClientErrorException> {

    @Override
    public Response toResponse(ClientErrorException exception) {
        final int status = exception.getResponse().getStatus();
        String title = exception.getResponse().getStatusInfo().getReasonPhrase();
        String errorMessage = exception.getMessage();

        return Response.status(status)
                .entity(new ErrorMessage(status, title, errorMessage))
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}
