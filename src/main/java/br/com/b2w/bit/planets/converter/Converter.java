package br.com.b2w.bit.planets.converter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @param <F> from
 * @param <T> to
 */
@FunctionalInterface
public interface Converter<F, T> {

    T convert(F from);

    default List<T> convert(Collection<F> from) {
        return from != null ? from.stream().map(this::convert).collect(Collectors.toList()) : Collections.emptyList();
    }

    default List<T> convertParallel(Collection<F> from) {
        return from != null ? from.parallelStream().map(this::convert).collect(Collectors.toList()) : Collections.emptyList();
    }

}
