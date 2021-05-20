package caching;

public class Cache<T> {
    private CacheElement<T>[] cache;
    private int capacity;

    @SuppressWarnings("unchecked")
    public Cache(int capacity){
        this.cache = new CacheElement[capacity];
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    /*
         void add(element, index) метод добавления элемента в кэш (наш массив).
         ВАЖНО добавление всегда происходит в конец массива (в первый null).
         Если мы выходим за длину массива, то необходимо удалить самый первый
         элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива.
         */
    public void add(T element, int index){
        CacheElement<T> tCacheElement = new CacheElement<>(element);
        boolean elementAdded = false;
        for (int i = 0; i < capacity; i++){
            if (cache[i] == null) {
                cache[i] = tCacheElement;
                elementAdded = true;
                break;
            }
        }
        if (!elementAdded) {
            copyArray(cache);
            cache[capacity - 1] = tCacheElement;
        }
    }

    private void copyArray(CacheElement<T>[] cache){
        System.arraycopy(cache, 1, cache, 0, capacity - 1);
    }

    /*
    void delete(element) метод удаления элемента из кэша.
    При удалении мы должны будем сдвинуть оставшуюся часть массива влево.
     */
    public void delete(T element) {
        for (int i = 0; i < capacity; i++) {
            if (this.isPresent(element)) {
                copyArray(cache);
            }
        }
    }

    /*
    boolean isPresent(element) метод определения есть ли искомый элемент в кэше.
    */
    public boolean isPresent(T element){
        CacheElement<T> tCacheElement = new CacheElement<>(element);
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].equals(tCacheElement)) {
                return true;
            }
        }
        return false;
    }

    /*
    boolean isPresent(index) метод определения есть ли искомый элемент в кэше.
    */
    public boolean isPresent(int index){
        return index < this.capacity && cache[index] != null;
    }

    @SuppressWarnings("unchecked")
    public T getElement(int index){
        return (T)cache[index];
    }

    /*
    element get(index) метод получения объекта из кэша.
    При нахождении элемента в кэше его необходимо поместить в конец массива (в первый null),
    с учетом сдвига остальных элементов влево.
    */
    @SuppressWarnings("unchecked")
    public T get(int index){
        if (this.isPresent(index)) {
            for (int i = 0; i < capacity; i++){
                if (cache[i] == null) {
                    this.add((T)cache[index],i);
                    return cache[i - 1].getElement();
                }
            }
        }
        return null;
    }

    /*
     void clear() метод очистки всего кэша.
    */
    public void clear() {
        for (int i = 0; i < capacity; i++){
            cache[i] = null;
        }
    }
}
