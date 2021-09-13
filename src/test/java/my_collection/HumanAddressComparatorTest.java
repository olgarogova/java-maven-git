package my_collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HumanAddressComparatorTest {

    Human human1;
    Human human2;
    Human human3;
    Human human4;
    Human human5;
    List<Human> humans;
    List<Human> sortedHumans;

    @BeforeEach
    void setUp(){
        human1 = new Human("Smirnov Artur Arsenevich", 20, "Kovernino, ulitza Akademika Krilova, dom 1, kv 678");
        human2 = new Human("Kulagin Ilya Lukyanovich", 28, "Ostashkov, ulitza Mariinskaya, dom 40, kv 689");
        human3 = new Human("Aksenov Ivan Konstantinovich", 18, "Doneck, Monetchikovsky pereulok, dom 141, kv 333");
        human4 = new Human("Ivanov Velor Michailovich", 42, "Zalari, ulitza Beringa, dom 181, kv 361");
        human5 = new Human("Melnikov Yury Filatovich", 64, "Kornevo, ulitza Dmitry Donskogo, dom 97, kv 571");

        humans = new ArrayList<>();
        humans.add(human1);
        humans.add(human2);
        humans.add(human3);
        humans.add(human4);
        humans.add(human5);
    }

    @Test
    @DisplayName("Compare by Address")
    void ensureThatAddressComparatorCorrects(){
        humans.sort(new HumanAddressComparator());
        assertEquals(human3, humans.get(0));
        assertEquals(human5, humans.get(1));
        assertEquals(human1, humans.get(2));
        assertEquals(human2, humans.get(3));
        assertEquals(human4, humans.get(4));
    }
}