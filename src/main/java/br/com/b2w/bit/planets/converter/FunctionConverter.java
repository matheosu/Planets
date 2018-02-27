package br.com.b2w.bit.planets.converter;

import java.util.function.Function;

public abstract class FunctionConverter<F, T> implements Converter<F, T> {

    abstract Function<F, T> convertFunction();

    @Override
    public T convert(F from) {
        return convertFunction().apply(from);
    }
}
