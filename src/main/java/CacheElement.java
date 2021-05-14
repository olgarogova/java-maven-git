public class CacheElement<T> {
    private T element;
    private int index;

    public CacheElement(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }
}