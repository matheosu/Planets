package br.com.b2w.bit.planets.producer;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.ws.rs.core.CacheControl;

public class CacheControlProducer {

    @Produces
    public CacheControl create(InjectionPoint ip) {
        CacheControlConfig annotation = ip.getAnnotated().getAnnotation(CacheControlConfig.class);
        if (annotation != null) {
            CacheControl cc = new CacheControl();
            cc.setMaxAge(annotation.maxAge());
            cc.setMustRevalidate(annotation.mustRevalidate());
            cc.setNoCache(annotation.noCache());
            cc.setNoStore(annotation.noStore());
            cc.setNoTransform(annotation.noTransform());
            cc.setPrivate(annotation.isPrivate());
            cc.setProxyRevalidate(annotation.proxyRevalidate());
            cc.setSMaxAge(annotation.sMaxAge());
            return cc;
        }

        return null;
    }
}
