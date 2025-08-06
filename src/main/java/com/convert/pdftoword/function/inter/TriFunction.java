package com.convert.pdftoword.function.inter;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface TriFunction<T1,T2,T3, R> {
    R apply(T1 var1, T2 var2, T3 var3);

    default <W> TriFunction<T1, T2, T3, W> andThen(Function<? super R, ? extends W> after) {
        Objects.requireNonNull(after);
        return (t, u, v) -> after.apply(this.apply(t, u, v));
    }
}
