package com.wxf.behavioral.responsibility_chain;

import java.util.Objects;

/*
 *
 * @author weixf
 * @date 2022-08-23
 */
@FunctionalInterface
public interface NewProcessor<T> {
    void process(T param);

    default NewProcessor<T> andThen(NewProcessor<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> {
            process(t);
            after.process(t);
        };
    }
}
