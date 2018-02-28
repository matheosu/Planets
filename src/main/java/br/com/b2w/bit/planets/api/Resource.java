package br.com.b2w.bit.planets.api;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;

class Resource {

    @Context
    protected UriInfo uriInfo;

    @Context
    protected Request request;

    <T> Response ok(T object) {
        if (object == null)
            throw new NotFoundException();

        if (object instanceof Collection && ((Collection<?>) object).isEmpty())
            throw new NotFoundException();

        GenericEntity<Object> entity = new GenericEntity<Object>(object) {};
        return Response.ok(entity).build();
    }

    <T> Response okWeekETag(T object, CacheControl cacheControl){
        return okETag(object, cacheControl, true);
    }

    <T> Response okETag(T object, CacheControl cacheControl){
        return okETag(object, cacheControl, false);
    }

    private <T> Response okETag(T object, CacheControl cacheControl, boolean weak) {
        if (object == null)
            throw new NotFoundException();

        if (object instanceof Collection && ((Collection<?>) object).isEmpty())
            throw new NotFoundException();

        EntityTag tag = new EntityTag(Integer.toString(object.hashCode()), weak);
        try {
            Response.ResponseBuilder builder = request.evaluatePreconditions(tag);
            if (builder != null)
                return builder.cacheControl(cacheControl).build();
        } catch (Exception e) {
//            "Error to evaluate preconditions. ETag {} Weak {}", tag, weak;
//            e.getMessage();
        }

        return Response.ok(new GenericEntity<Object>(object) {})
                .cacheControl(cacheControl)
                .tag(tag)
                .build();
    }

    Response created(String method, String... id) {
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        URI uri = builder.path(method).build((Object[]) id);
        return Response.created(uri).build();
    }


}
