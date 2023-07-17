package de.bagehorn.Haushaltsbuch.services;

import java.util.function.Predicate;
import java.util.stream.StreamSupport;

public class FilterServiceImpl<T> implements FilterService<T> {
    @Override
    public Iterable<T> select(Iterable<T> iter, Predicate<T> pred) {
        return () -> StreamSupport.stream(iter.spliterator(), false).filter(pred.negate()).iterator();
    }
}
