package br.com.b2w.bit.planets.api.mapper;

import br.com.b2w.bit.planets.dto.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;
import java.util.stream.Collectors;

import static br.com.b2w.bit.planets.util.Strings.DEFAULT_BREAK_LINE;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException exception) {
        Response.Status internalServerError = Response.Status.INTERNAL_SERVER_ERROR;
        String title = internalServerError.getReasonPhrase();
        int serverErrorCode = internalServerError.getStatusCode();

        ErrorMessage errorMessage = new ErrorMessage(serverErrorCode, title,
                exception.getMessage(),
                Arrays.stream(exception.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining(DEFAULT_BREAK_LINE)));

        return Response.status(serverErrorCode)
                .entity(errorMessage)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
