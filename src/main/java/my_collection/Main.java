package my_collection;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        workWithCollection();
        sortHashMapByKey();
        sortHashMapByEntity();
        workWithUser();
    }

    public static void workWithCollection(){
        Human artur = new Human("Smirnov Artur Arsenevich", 20, "Kovernino, ulitza Akademika Krilova, dom 1, kv 678");
        Human arturArsenevich = new Human("Smirnov Artur Arsenevich", 20, "Kovernino, ulitza Akademika Krilova, dom 1, kv 678");
        Human adam = new Human("Grishin Adam Vsevolodovich", 12, "Kargopol, Teatralny proezd, dom 66, kv 615");
        Human adamVsevolodovich = new Human("Grishin Adam Vsevolodovich", 12, "Kargopol, Teatralny proezd, dom 66, kv 615");
        Human lavr = new Human("Lihachev Lavr Grigorievich", 34, "Sarov, ulitza Yasenevaya, dom 158, kv 780");
        Human lavrGrigorievich = new Human("Lihachev Lavr Grigorievich", 34, "Sarov, ulitza Yasenevaya, dom 158, kv 780");
        Human ilya = new Human("Kulagin Ilya Lukyanovich", 28, "Ostashkov, ulitza Mariinskaya, dom 40, kv 689");
        Human ivan = new Human("Aksenov Ivan Konstantinovich", 18, "Doneck, Monetchikovsky pereulok, dom 141, kv 333");
        Human velor = new Human("Ivanov Velor Michailovich", 42, "Zalari, ulitza Beringa, dom 181, kv 361");
        Human yury = new Human("Melnikov Yury Filatovich", 64, "Kornevo, ulitza Dmitry Donskogo, dom 97, kv 571");

        List<Human> humans = new ArrayList<>();
        humans.add(artur);
        humans.add(arturArsenevich);
        humans.add(adam);
        humans.add(adamVsevolodovich);
        humans.add(lavr);
        humans.add(lavrGrigorievich);
        humans.add(ilya);
        humans.add(ivan);
        humans.add(velor);
        humans.add(yury);

        Set<Human> duplicates = findDuplicates(humans);
        System.out.println("The list of duplicates:");
        System.out.println(duplicates);

        Set<Human> uniqes = deleteDuplicates(humans);
        System.out.println("The list of uniqes:");
        System.out.println(uniqes);

        List<Human> uniqesHumans = new ArrayList<>(uniqes);

        //???????????????????? ???? ??????
        uniqesHumans.sort(new HumanNameComparator());
        System.out.println("Sorted List by Name: " + uniqesHumans);

        //???????????????????? ???? ????????????????
        uniqesHumans.sort(new HumanAgeComparator());
        System.out.println("Sorted List by Age:" + uniqesHumans);

        //???????????????????? ???? ????????????
        uniqesHumans.sort(new HumanAddressComparator());
        System.out.println("Sorted List by Address" + uniqesHumans);
    }

    public static Set<Human> findDuplicates(List<Human> collection) {
        Set<Human> uniqes = new HashSet<>();
        Set<Human> duplicates = new HashSet<>();
        for (Human h: collection){
            if(!uniqes.add(h)){
                duplicates.add(h);
            }
        }
        return duplicates;
    }

    public static Set<Human> deleteDuplicates(List<Human> collection){
        return new HashSet<>(collection);
    }

    public static void workWithUser() {
        User user1 = new User("Administrator", Roles.ADMIN);
        User user2 = new User("Vasya", Roles.USER);
        User user3 = new User("Ivan", Roles.MODERATOR);

        welcomeUserMessage(user1);
        welcomeUserMessage(user2);
        welcomeUserMessage(user3);

    }

    public static void welcomeUserMessage(User user) {
        Map<Roles, String> rolesDescription = new EnumMap<>(Roles.class);
        rolesDescription.put(Roles.ADMIN, "(???????????? ??????????)");
        rolesDescription.put(Roles.USER, "(????????????????, ?????????????????? ????????????????????????)");
        rolesDescription.put(Roles.MODERATOR, "(?????????????????? ????????????????????????)");

        System.out.println("???????????????????????? " + user.getName() + " ?? ?????????? " + user.getRole() + " " + rolesDescription.get(user.getRole()));
    }

    public static void sortHashMapByKey() {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Anna", 18);
        hashMap.put("Maria", 19);
        hashMap.put("Darya", 20);
        hashMap.put("Elena", 21);
        hashMap.put("Vera",22);

        System.out.println("HashMap: " + hashMap);
        Map<String, Integer> sortedHashMap = new TreeMap<>(hashMap);
        System.out.println("HashMap sorted by key: " + sortedHashMap);
    }

    public static void sortHashMapByEntity(){
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Anna", 18);
        hashMap.put("Maria", 19);
        hashMap.put("Darya", 20);
        hashMap.put("Elena", 21);
        hashMap.put("Vera",22);

        System.out.println("HashMap: " + hashMap);

        List<Map.Entry<String,Integer>> sorted = new ArrayList<>(hashMap.entrySet());
        sorted.sort(Map.Entry.comparingByValue());

        Map<String, Integer> result = new LinkedHashMap<>();
        for(Map.Entry<String, Integer> entry: sorted){
            result.put(entry.getKey(), entry.getValue());
        }

        System.out.println("HashMap sorted by entity: " + result);
    }
}
