package br.com.b2w.bit.planets.api.filter;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Provider
@Priority(Priorities.HEADER_DECORATOR)
public class CharsetResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (Response.Status.OK.getStatusCode()  == responseContext.getStatusInfo().getStatusCode()) {
            MediaType mediaType = responseContext.getMediaType().withCharset(StandardCharsets.UTF_8.name());
            responseContext.getHeaders().putSingle(HttpHeaders.CONTENT_TYPE, mediaType.toString());
        }
    }
}