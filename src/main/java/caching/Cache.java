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
        CacheElement<T> cacheElement = new CacheElement<>(element);
        for (int i = 0; i < capacity; i++){
            if (cache[i] == null) {
                cache[i] = cacheElement;
                cache[i].setIndex(index);
                return;
            }
        }
        copyArray(cache, 1, 0, capacity - 1);
        cache[capacity - 1] = cacheElement;
        cache[capacity - 1].setIndex(index);
    }

    /*
    void delete(element) метод удаления элемента из кэша.
    При удалении мы должны будем сдвинуть оставшуюся часть массива влево.
     */
    public void delete(T element) {
        for (int i = 0; i < capacity; i++) {
            if (this.isPresent(element)) {
                copyArray(cache, i, i + 1, capacity - i - 1);
            }
        }
    }

    /*
    boolean isPresent(element) метод определения есть ли искомый элемент в кэше.
    */
    public boolean isPresent(T element){
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].getElement().equals(element)) {
                return true;
            }
        }
        return false;
    }

    /*
    boolean isPresent(index) метод определения есть ли искомый элемент в кэше.
    */
    public boolean isPresent(int index){
        for (int i = 0; i < capacity; i++){
            if (cache[i] != null && cache[i].getIndex() == index) {
                return true;
            }
        }
        return false;
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
    public T get(int index){
        T value = null;
        if (this.isPresent(index)) {
            for (int i = 0; i < capacity; i++){
                if (cache[i] != null && cache[i].getIndex() == index){
                    value = cache[i].getElement();
                }
            }
            for (int i = 0; i < capacity; i++){
                if (cache[i] == null) {
                    this.add(value,index);
                    return value;
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

    private void copyArray(CacheElement<T>[] cache, int srcPos, int destPos, int length){
        System.arraycopy(cache, srcPos, cache, destPos, length);
    }

}
