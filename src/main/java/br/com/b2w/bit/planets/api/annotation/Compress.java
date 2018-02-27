package br.com.b2w.bit.planets.api.annotation;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@NameBinding
@Retention(RUNTIME)
public @interface Compress {
}