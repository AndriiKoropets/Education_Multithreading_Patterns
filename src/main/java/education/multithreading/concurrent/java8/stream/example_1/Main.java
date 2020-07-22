package education.multithreading.concurrent.java8.stream.example_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class Main {
    public void printGroup(List<People> people) {
        Set<Group> groups = new HashSet<>();
        for (People p : people) {
            if (p.getAge() >= 65) {
                groups.add(p.getGroup());
            }
        }

        List<Group> sorted = new ArrayList<>(groups);
        Collections.sort(sorted, new Comparator<Group>() {
            @Override
            public int compare(Group a, Group b) {
                return Integer.compare(a.getSize(), b.getSize());
            }
        });

        for (Group g : sorted) {
            System.out.println(g.getName());
        }

    }

    private void printGroupsStreams(List<People> people) {
        people.stream()
                .filter(p -> p.getAge() >= 65)
                .map(People::getGroup)
                .distinct()
                .sorted(Comparator.comparing(Group::getSize))
                .map(Group::getName)
                .forEach(System.out::println);
    }

    private void printGroupsStreams_Streams(List<People> people) {
        Stream<People> s1 = people.stream();
        Stream<People> s2 = s1.filter(p -> p.getAge() >= 65);
        Stream<Group> s3 = s2.map(People::getGroup);
        Stream<Group> s4 = s3.distinct();
        Stream<Group> s5 = s4.sorted(Comparator.comparing(Group::getSize));
        Stream<String> s6 = s5.map(Group::getName);
        s6.forEach(System.out::println);
    }
}
