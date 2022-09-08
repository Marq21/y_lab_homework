package homework;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Person {
        final int id;

        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }
    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    public static void findPair(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            if (map.containsKey(target - nums[i])) {
                System.out.printf("[%d, %d]",
                        nums[map.get(target - nums[i])], nums[i]);
                return;
            }

            map.put(nums[i], i);
        }

        System.out.println("Pair not found");
    }

    public static boolean fuzzySearch(String key, String str) {

        int letterCounter = 0;
        Stack<Character> characters = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (letterCounter == key.length()) break;
            if (key.charAt(letterCounter) == str.charAt(i)) {
                characters.add(key.charAt(letterCounter));
                letterCounter++;
            }
        }

        return characters.size() == key.length();

    }

    public static void main(String[] args) {
        //Task1
        Map<String, Long> map = Arrays.stream(RAW_DATA)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.groupingBy(Person::getName, Collectors.counting()));

        for (String s : map.keySet()) {
            System.out.println("Key: " + s);
            System.out.println("Value: " + map.get(s));
        }

        //Task2
        int[] taskArray = new int[]{3, 4, 2, 7};

        findPair(taskArray, 10);

        //Task3

        System.out.println();
        Test.testFuzzySearchReturnTrue();
        Test.testFuzzySearchReturnFalse();

    }

    private static class Test {

        public static void testFuzzySearchReturnTrue() {
            if (
                    fuzzySearch("car", "ca6$$#_rtwheel") &&
                            fuzzySearch("cwhl", "cartwheel") &&
                            fuzzySearch("cwhee", "cartwheel") &&
                            fuzzySearch("cartwheel", "cartwheel")) {
                System.out.println("Test is Done!");
            } else {
                System.out.println("Test failed");
            }
        }

        public static void testFuzzySearchReturnFalse() {
            if (!fuzzySearch("cwheeel", "cartwheel") &&
                    !fuzzySearch("lw", "cartwheel")) {
                System.out.println("Test is Done!");
            } else {
                System.out.println("Test failed");
            }
        }
    }
}
