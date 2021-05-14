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
         ВАЖНО добавление всегда происходит в конец массива.
         Если мы выходим за длину массива, то необходимо удалить самый первый
         элемент в массиве, сдвинуть весь массив влево и добавить новый элемент в конец массива.
         */
    public void add(CacheElement<T> element, int index){
        if (cache[capacity - 1] != null){
            cache[capacity - 1] = element;
            if (capacity - 1 - 1 >= 0) System.arraycopy(cache, 1, cache, 0, capacity - 1 - 1);
        } else {
            for (int i = 0; i < capacity; i++){
                if (cache[i] == null) {
                    cache[i] = element;
                    break;
                }
            }
        }
    }

    /*
    void delete(element) метод удаления элемента из кэша.
    При удалении мы должны будем сдвинуть оставшуюся часть массива влево.
     */
    public void delete(CacheElement<T> element){
        for (int i = 0; i < capacity; i++) {
            if (cache[i] != null && cache[i].equals(element)){
                cache[i] = cache[i + 1];
                cache[i + 1] = null;
                System.out.println("Element " + element.getElement() + " was deleted");
            }
        }
    }

    /*
    boolean isPresent(element) метод определения есть ли искомый элемент в кэше.
    */
    public boolean isPresent(CacheElement<T> element){
        if (this.capacity == 0){
            return false;
        } else {
            for (int i = 0; i < capacity; i++) {
                if (cache[i] != null && cache[i].equals(element)) {
                    return true;
                }
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
    При нахождении элемента в кэше его необходимо поместить в конец массива,
    с учетом сдвига остальных элементов влево.
    */
    public CacheElement<T> get(int index){
        for (int i = 0; i < capacity; i++){
            cache[i] = null;
        }
        if (cache[index] != null) {
            cache[capacity - 1] = cache[index];
            if (capacity - 1 - index + 1 >= 0)
                System.arraycopy(cache, index + 1 - 1, cache, index + 1, capacity - 1 - index + 1);
        }
        return cache[index];
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
