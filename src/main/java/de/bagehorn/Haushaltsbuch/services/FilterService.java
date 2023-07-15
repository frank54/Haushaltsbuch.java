package de.bagehorn.Haushaltsbuch.services;

import java.util.function.Predicate;

public interface FilterService<T> {

    Iterable<T> select(Iterable<T> iter, Predicate<T> pred);

}
