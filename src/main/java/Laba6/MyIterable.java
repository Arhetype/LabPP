package Laba6;

import java.util.Iterator;

public interface MyIterable<T> extends Iterable<T> {
    @Override
    Iterator<T> iterator();
}