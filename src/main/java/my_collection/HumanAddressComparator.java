package my_collection;

import java.util.Comparator;

public class HumanAddressComparator implements Comparator<Human> {

    @Override
    public int compare(Human o1, Human o2) {
        return o1.getAddress().compareTo(o2.getAddress());
    }
}
