package caching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StorageTest {

    Storage<Integer> storage;

    @BeforeEach
    void setUp() {
        storage = new Storage<>();
        storage.add(123);
        storage.add(456);
    }

    @Test
    @DisplayName("Get Number Element in Storage")
    void getNumberElementsInStorage() {
        assertEquals(2, storage.getNumberElementsInStorage());
    }

    @Test
    @DisplayName("Add element to storage")
    void ensureThatElementAddedToStorage() {
        assertEquals(123, storage.get(0));
    }

    @Test
    @DisplayName("Delete element from storage")
    void ensureThatElementDeletedFromStorage() {
        storage.delete();
        assertEquals(123, storage.getLast());
    }

    @Test
    @DisplayName("Clear storage")
    void ensureThatStorageIsCleared() {
        storage.clear();
        assertNull(storage.get(0));
    }

    @Test
    @DisplayName("Get Last Element from storage")
    void getLastShouldReturnLastElement() {
        assertEquals(456, storage.getLast());
    }

    @Test
    @DisplayName("Get Element from storage (by index)")
    void getShouldReturnElementByIndex() {
        assertEquals(123, storage.get(0));
    }
}