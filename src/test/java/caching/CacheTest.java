package caching;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    Cache<Integer> cache;
    Integer element0;
    Integer element1;

    @BeforeEach
    void setUp() {
        cache = new Cache<>(10);
        element0 = 123;
        element1 = 456;
        cache.add(element0, 0);
        cache.add(element1, 1);
    }

    @Test
    @DisplayName("Capacity should be 10")
    void getCapacity() {
        assertEquals(10, cache.getCapacity());
    }

    @Test
    @DisplayName("Check that element added to cache")
    void add() {
        assertEquals(123, cache.get(0));
    }

    @Test
    @DisplayName("Check that element deleted from cache")
    void delete() {
        cache.delete(element0);
        assertFalse(cache.isPresent(element0));
    }

    @Test
    @DisplayName("Check that element is present in cache (by index)")
    void isPresent() {
        assertTrue(cache.isPresent(0));
    }

    @Test
    @DisplayName("Check that element is present in cache (by element)")
    void testIsPresent() {
        assertTrue(cache.isPresent(element0));
    }

    @Test
    @DisplayName("Check element getting")
    void get() {
        assertSame(element0, cache.get(0));
    }

    @Test
    @DisplayName("Check that cache is cleaned")
    void clear() {
        cache.clear();
        assertFalse(cache.isPresent(element0));
    }
}