package caching;

import java.util.Arrays;

public class Storage<T> {
    private T[] storage;
    private Cache<T> cache;
    private int storageSize = 10;
    private int numberElementsInStorage = 0;

    /*
    дефолтный конструктор, в котором создается наш массив типа Т, а так же объект кэша.
     */
    @SuppressWarnings("unchecked")
    public Storage() {
        this.storage = (T[]) new Object[storageSize];
        this.cache = new Cache<>(10);
    }

    /*
    конструктор должен принимать на вход массив элементов типа Т
    и сразу же заполнять наш массив. (длина массива может превышать начальную
    длину нашего массива и это надо учесть)
     */
    @SuppressWarnings("unchecked")
    public Storage(T[] tempStorage) {
        this.storage = (T[]) new Object[tempStorage.length];
        System.arraycopy(tempStorage, 0, this.storage, 0, tempStorage.length);
        this.cache = new Cache<>(10);
    }

    public void setNumberElementsInStorage(int numberElementsInStorage) {
        this.numberElementsInStorage = numberElementsInStorage;
    }

    public int getNumberElementsInStorage() {
        for (T t : storage) {
            if (t != null) {
                numberElementsInStorage = numberElementsInStorage + 1;
            }
        }
        return numberElementsInStorage;
    }

    public int getStorageSize() {
        return storage.length;
    }

    public boolean checkIfArrayEmpty(T[] storage){
        boolean empty = true;
        for (T element : storage) {
            if (element != null) {
                empty = false;
                break;
            }
        }
        return empty;
    }

    /*
    Реализовать метод добавления элемента в массив
    void add(element)
    Если мы достигли предела длины массива, то мы должны увеличить емкость нашего хранилища в 1.5 раза.
     */
    public void add(T element) {
        if (storage[storage.length - 1] != null) {
            int storageSize2 = storage.length + storage.length / 2;
            storage = Arrays.copyOf(storage, storageSize2);
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = element;
                break;
            }
        }
    }

    /*
    Реализовать метод удаления последнего элемента из массива
    void delete()
    Тут мы будем использовать наш класс caching.Cache.
    Сначала проверяем есть ли наш объект в кэше.
    Если есть, то удаляем его оттуда.
    После удаляем объект из массива.
     */
    public void delete () {
        int number = getNumberElementsInStorage();
        if (number > 0) {
            T element = storage [number - 1];
            if (cache.isPresent(element)) {
                cache.delete(element);
            }
            storage[number - 1] = null;
        }
    }

    /*
    Реализовать метод удаления всех элементов из массива.
    void clear()
    Тут мы будем использовать наш класс caching.Cache.
    Удаляем все из кэша.
     */
    public void clear() {
        cache.clear();
        Arrays.fill(storage, null);
    }

    /*
    Реализовать метод получения последнего элемента из массива
    element getLast()
    Нужно учитывать, что размер массива может быть к примеру 15,
    а заполнено всего 7 элементов, следовательно должен вернуться элемент под индексом 6.
     */
    public T getLast () {
        if (checkIfArrayEmpty(storage)) {
            System.out.println("Array is empty");
            return null;
        }
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                return storage[i - 1];
            }
        }
        return storage[storage.length - 1];
    }

    /*
    Реализовать метод получения элемента из массива по индексу
    element get(int index)
    Тут мы будем использовать наш класс caching.Cache.
    Сначала мы должны проверить есть ли в кеше такой объект.
    Если есть, то возвращаем его, не идем в наш массив, давайте представим,
    что это очень долгая и сложная операция и будет логичнее использовать кэш.
    Если объекта не оказалось в кэше, то мы берем объект из нашего массива, добавляем его в кэш и возвращаем.
     */
    @SuppressWarnings("unchecked")
    public T get(int index){
        if (cache.get(index) == null) {
            CacheElement<T> element = new CacheElement<>(storage[index]);
            cache.add((T)element, index);
            return storage[index];
        }
        return cache.get(index);

    }
}
