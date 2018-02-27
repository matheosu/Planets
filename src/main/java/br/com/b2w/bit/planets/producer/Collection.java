package br.com.b2w.bit.planets.producer;


import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Qualifier
@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Collection {

    @Nonbinding String value() default "";
    
}
