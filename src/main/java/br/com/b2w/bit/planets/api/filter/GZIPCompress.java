package br.com.b2w.bit.planets.api.filter;

import br.com.b2w.bit.planets.api.annotation.Compress;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

@Provider
@Compress
@Priority(Priorities.ENTITY_CODER)
public class GZIPCompress implements WriterInterceptor {

    private static final String GZIP = "gzip";

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
        context.getHeaders().add(HttpHeaders.CONTENT_ENCODING, GZIP);

        final OutputStream outputStream = context.getOutputStream();
        context.setOutputStream(new GZIPOutputStream(outputStream));
        context.proceed();
    }
}
