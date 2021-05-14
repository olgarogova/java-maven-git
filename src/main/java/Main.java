public class Main {
    public static void main(String[] args) {
        //checkCacheInteger();
        //checkCacheString();
        checkStorageInteger();
        //checkStorageString();
    }

    public static void checkCacheInteger(){
        Cache<Integer> myCache = new Cache<>(10);
        CacheElement<Integer> element1 = new CacheElement<>(0);
        CacheElement<Integer> element2 = new CacheElement<>(1);
        CacheElement<Integer> element3 = new CacheElement<>(2);
        CacheElement<Integer> element4 = new CacheElement<>(3);
        CacheElement<Integer> element5 = new CacheElement<>(4);
        myCache.add(element1, 0);
        myCache.add(element2,1);
        myCache.add(element3,2);
        myCache.add(element4,3);

        System.out.println(element1.getElement() + " exists in array: " + myCache.isPresent(element1));
        System.out.println(element2.getElement() + " exists in array: " + myCache.isPresent(element2));
        System.out.println(element4.getElement() + " exists in array: " + myCache.isPresent(element4));
        System.out.println(element5.getElement() + " exists in array: " + myCache.isPresent(element5));

        Cache<Integer> myCache2 = new Cache<>(0);
        System.out.println(element1.getElement() + " exists in array2: " + myCache2.isPresent(element1));

        System.out.println("myCache.isPresent(3) = " + myCache.isPresent(3));
        System.out.println("myCache.isPresent(10) = " + myCache.isPresent(10));

        myCache.delete(element4);
        System.out.println(element4.getElement() + " exists in array: " + myCache.isPresent(element4));

        myCache.clear();
        System.out.println("After clear: " + element2.getElement() + " exists in array: " + myCache.isPresent(element2));

    }

    public static void checkCacheString(){
        Cache<String> myCache = new Cache<>(10);
        CacheElement<String> element0 = new CacheElement<>("string0");
        CacheElement<String> element1 = new CacheElement<>("string1");
        CacheElement<String> element2 = new CacheElement<>("string2");
        CacheElement<String> element3 = new CacheElement<>("string3");
        CacheElement<String> element4 = new CacheElement<>("string4");
        myCache.add(element0, 0);
        myCache.add(element1,1);
        myCache.add(element2,2);
        myCache.add(element3,3);

        System.out.println(element1.getElement() + " exists in array: " + myCache.isPresent(element1));
        System.out.println(element2.getElement() + " exists in array: " + myCache.isPresent(element2));
        System.out.println(element3.getElement() + " exists in array: " + myCache.isPresent(element3));
        System.out.println(element4.getElement() + " exists in array: " + myCache.isPresent(element4));

        Cache<String> myCache2 = new Cache<>(0);
        System.out.println(element0.getElement() + " exists in array2: " + myCache2.isPresent(element0));
    }

    public static void checkStorageInteger() {
        Integer[] temp = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        Storage<Integer> storage1 = new Storage<>(temp);

        System.out.println("storage1.getLast() = " + storage1.getLast());
        System.out.println("storage1.get(2) = " + storage1.get(2));

        storage1.add(234);
        System.out.println("After add element 234: storage1.getLast() = " + storage1.getLast());

        //storage1.clear();
        //System.out.println("After clear: storage1.getLast() = " + storage1.getLast());
        //System.out.println("After clear: storage1.get(2) = " + storage1.get(2));

        System.out.println("Before delete: storage1.get(12) = " + storage1.get(12));
        System.out.println("Before delete: storage1.get(13) = " + storage1.get(13));
        System.out.println("Before delete: storage1.get(0) = " + storage1.get(0));
        System.out.println("Before delete: storage1.getLast() = " + storage1.getLast());

        storage1.delete();
        System.out.println("After delete: storage1.get(0) = " + storage1.get(0));
        System.out.println("After delete: storage1.get(2) = " + storage1.get(2));
        System.out.println("After delete: storage1.get(12) = " + storage1.get(12));
        System.out.println("After delete: storage1.get(13) = " + storage1.get(13));
        System.out.println("After delete: storage1.getLast() = " + storage1.getLast());

    }

    public static void checkStorageString() {
        String[] temp = {"abc", "aaa", "bbb", "ccc", "ddd", "eeee", "fff", "gggg", "hhh", "iii", "kkk", "lll", "mmm"};
        Storage<String> storage1 = new Storage<>(temp);

        System.out.println("storage1.getLast() = " + storage1.getLast());
        System.out.println("storage1.get(2) = " + storage1.get(2));

        storage1.add("nnn");
        System.out.println("After add element 234: storage1.getLast() = " + storage1.getLast());

        //storage1.clear();
        //System.out.println("After clear: storage1.getLast() = " + storage1.getLast());
        //System.out.println("After clear: storage1.get(2) = " + storage1.get(2));

        System.out.println("Before delete: storage1.get(12) = " + storage1.get(12));
        System.out.println("Before delete: storage1.get(13) = " + storage1.get(13));
        System.out.println("Before delete: storage1.get(0) = " + storage1.get(0));
        System.out.println("Before delete: storage1.getLast() = " + storage1.getLast());

        storage1.delete();
        System.out.println("After delete: storage1.get(0) = " + storage1.get(0));
        System.out.println("After delete: storage1.get(4) = " + storage1.get(4));
        System.out.println("After delete: storage1.get(12) = " + storage1.get(12));
        System.out.println("After delete: storage1.get(13) = " + storage1.get(13));
        System.out.println("After delete: storage1.getLast() = " + storage1.getLast());

    }
}
