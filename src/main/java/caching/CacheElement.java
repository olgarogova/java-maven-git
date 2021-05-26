package caching;

import java.util.Objects;

public class CacheElement<T> {
    private T element;
    private int index;

    public CacheElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "CacheElement{" +
                "element=" + element +
                ", index=" + index +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CacheElement)) return false;
        CacheElement<?> that = (CacheElement<?>) o;
        return getIndex() == that.getIndex() &&
                Objects.equals(getElement(), that.getElement());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElement(), getIndex());
    }

}