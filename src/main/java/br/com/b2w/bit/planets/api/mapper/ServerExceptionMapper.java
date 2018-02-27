package br.com.b2w.bit.planets.api.mapper;

import br.com.b2w.bit.planets.dto.ErrorMessage;

import javax.ws.rs.ServerErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.stream.Collectors;

import static br.com.b2w.bit.planets.util.Strings.DEFAULT_BREAK_LINE;

@Provider
public class ServerExceptionMapper implements ExceptionMapper<ServerErrorException> {

    @Override
    public Response toResponse(ServerErrorException exception) {
        final int status = exception.getResponse().getStatus();
        String msg = exception.getMessage();
        String title = exception.getResponse().getStatusInfo().getReasonPhrase();
        final ErrorMessage errorMessage = new ErrorMessage(status, title, msg,
                Arrays.stream(exception.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining(DEFAULT_BREAK_LINE)));

        return Response.status(status)
                       .entity(errorMessage)
                       .type(MediaType.APPLICATION_JSON_TYPE)
                       .build();
    }
}
